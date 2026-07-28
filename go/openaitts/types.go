// Package openaitts provides OpenAI TTS speech generation through RunAPI.
package openaitts

import "github.com/runapi-ai/core-sdk/go/core"

// TextToSpeechParams configures synchronous speech generation.
type TextToSpeechParams struct {
	Model string `json:"model" help:"required; model slug"`
	Text  string `json:"text" help:"required; text to synthesize, max 4096 characters"`
}

// Audio describes a RunAPI-managed MP3 result.
type Audio struct {
	URL       string `json:"url"`
	Format    string `json:"format"`
	MIMEType  string `json:"mime_type"`
	SizeBytes int64  `json:"size_bytes"`
}

// TextToSpeechResponse is the completed synchronous speech result.
type TextToSpeechResponse struct {
	core.TaskBillingFacts
	ID     string  `json:"id"`
	Status string  `json:"status"`
	Audios []Audio `json:"audios"`
	Error  string  `json:"error,omitempty"`
}
