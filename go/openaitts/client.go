package openaitts

import (
	"context"

	"github.com/runapi-ai/core-sdk/go/base"
	"github.com/runapi-ai/core-sdk/go/core"
	"github.com/runapi-ai/core-sdk/go/option"
)

const textToSpeechPath = "/api/v1/openai_tts/text_to_speech"

// Client provides OpenAI TTS speech generation.
type Client struct {
	base.Base
	TextToSpeech *TextToSpeech
}

// NewClient creates an OpenAI TTS client with the given options.
func NewClient(opts ...option.ClientOption) (*Client, error) {
	resolved, err := option.ResolveClientOptions(opts...)
	if err != nil {
		return nil, err
	}
	httpClient, err := core.NewHTTPClient(resolved)
	if err != nil {
		return nil, err
	}
	return NewClientWithHTTP(httpClient), nil
}

// NewClientWithHTTP creates an OpenAI TTS client with a pre-configured transport.
func NewClientWithHTTP(httpClient core.HTTPClient) *Client {
	return &Client{Base: base.New(httpClient), TextToSpeech: &TextToSpeech{http: httpClient}}
}

// TextToSpeech generates a RunAPI-managed MP3 from text.
type TextToSpeech struct{ http core.HTTPClient }

// Create submits speech generation and returns either its terminal response or
// an accepted Task that can be resumed through Subscribe.
func (r *TextToSpeech) Create(ctx context.Context, params TextToSpeechParams, opts ...option.RequestOption) (*core.HybridCreateResponse[TextToSpeechResponse], error) {
	requestOptions, _ := option.ResolveRequestOptions(opts...)
	body := core.CompactParams(params)
	if err := core.ValidateParams(contractSchema["text-to-speech"], body); err != nil {
		return nil, err
	}
	return core.CreateHybrid[TextToSpeechResponse](ctx, r.http, textToSpeechPath, body, requestOptions)
}

// Subscribe follows an accepted speech-generation Task to its terminal response.
func (r *TextToSpeech) Subscribe(ctx context.Context, acceptance *core.HybridAcceptance, opts ...option.RequestOption) (*TextToSpeechResponse, error) {
	requestOptions, pollingOptions := option.ResolveRequestOptions(opts...)
	return core.SubscribeHybrid[TextToSpeechResponse](ctx, r.http, acceptance, requestOptions, pollingOptions)
}

// Run returns the same speech response whether the endpoint completes directly
// or first returns 202 Accepted.
func (r *TextToSpeech) Run(ctx context.Context, params TextToSpeechParams, opts ...option.RequestOption) (*TextToSpeechResponse, error) {
	requestOptions, pollingOptions := option.ResolveRequestOptions(opts...)
	body := core.CompactParams(params)
	if err := core.ValidateParams(contractSchema["text-to-speech"], body); err != nil {
		return nil, err
	}
	return core.RunHybrid[TextToSpeechResponse](ctx, r.http, textToSpeechPath, body, requestOptions, pollingOptions)
}
