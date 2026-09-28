package openaitts

import (
	"context"
	"encoding/json"
	"net/http"
	"net/http/httptest"
	"testing"
	"time"

	"github.com/runapi-ai/core-sdk/go/core"
	"github.com/runapi-ai/core-sdk/go/option"
)

type stubHTTPClient struct {
	method   string
	path     string
	body     any
	response json.RawMessage
}

func (s *stubHTTPClient) Request(_ context.Context, method, path string, opts *core.HTTPRequestOptions) (json.RawMessage, error) {
	s.method = method
	s.path = path
	if opts != nil {
		s.body = opts.Body
	}
	return s.response, nil
}

func (s *stubHTTPClient) RequestWithResponse(ctx context.Context, method, path string, opts *core.HTTPRequestOptions) (*core.HTTPResponse, error) {
	payload, err := s.Request(ctx, method, path, opts)
	if err != nil {
		return nil, err
	}
	return &core.HTTPResponse{Body: payload, StatusCode: http.StatusOK, Header: make(http.Header)}, nil
}

func TestTextToSpeechRunFollowsAcceptedTaskLocation(t *testing.T) {
	requests := 0
	server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		requests++
		switch requests {
		case 1:
			w.Header().Set("Location", "/api/v1/tasks/tts_task")
			w.Header().Set("Retry-After", "0")
			w.WriteHeader(http.StatusAccepted)
			_, _ = w.Write([]byte(`{"id":"tts_task","status":"processing"}`))
		case 2:
			_, _ = w.Write([]byte(`{"id":"tts_task","status":"completed","response":{"status":200,"content_type":"application/json","headers":{},"body":{"id":"tts_task","status":"completed","audios":[{"url":"https://runapi.ai/audio.mp3","format":"mp3","mime_type":"audio/mpeg","size_bytes":128}]}}}`))
		default:
			t.Fatalf("unexpected request %d", requests)
		}
	}))
	defer server.Close()

	client, err := NewClient(option.WithAPIKey("test-key"), option.WithBaseURL(server.URL))
	if err != nil {
		t.Fatal(err)
	}
	result, err := client.TextToSpeech.Run(context.Background(), TextToSpeechParams{Model: "tts-1", Text: "Hello"}, option.WithPollInterval(time.Millisecond))
	if err != nil {
		t.Fatal(err)
	}
	if len(result.Audios) != 1 || result.Audios[0].MIMEType != "audio/mpeg" {
		t.Fatalf("unexpected terminal result: %#v", result)
	}
}

func TestTextToSpeechRun(t *testing.T) {
	stub := &stubHTTPClient{response: json.RawMessage(`{"id":"task_1","status":"completed", "usage": {"cost": 0.05},"audios":[{"url":"https://runapi.ai/audio.mp3","format":"mp3","mime_type":"audio/mpeg","size_bytes":128}]}`)}
	client := NewClientWithHTTP(stub)
	response, err := client.TextToSpeech.Run(context.Background(), TextToSpeechParams{Model: "tts-1", Text: "Hello"})
	if err != nil {
		t.Fatal(err)
	}
	if stub.method != "POST" || stub.path != textToSpeechPath {
		t.Fatalf("unexpected request: %s %s", stub.method, stub.path)
	}
	body := stub.body.(map[string]any)
	if body["model"] != "tts-1" || body["text"] != "Hello" {
		t.Fatalf("unexpected body: %v", body)
	}
	if len(response.Audios) != 1 || response.Audios[0].MIMEType != "audio/mpeg" {
		t.Fatalf("unexpected response: %+v", response)
	}
}

func TestTextToSpeechResponseAcceptsLegacyNullBilling(t *testing.T) {
	var response TextToSpeechResponse
	if err := json.Unmarshal([]byte(`{"id":"task_1","status":"completed","usage":{"cost":0.05}}`), &response); err != nil {
		t.Fatal(err)
	}
}
