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

// Run generates speech synchronously.
func (r *TextToSpeech) Run(ctx context.Context, params TextToSpeechParams, opts ...option.RequestOption) (*TextToSpeechResponse, error) {
	requestOptions, _ := option.ResolveRequestOptions(opts...)
	body := core.CompactParams(params)
	if err := core.ValidateParams(contractSchema["text-to-speech"], body); err != nil {
		return nil, err
	}
	return core.PostJSON[TextToSpeechResponse](ctx, r.http, textToSpeechPath, body, requestOptions)
}
