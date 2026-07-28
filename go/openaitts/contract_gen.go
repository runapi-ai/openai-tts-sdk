package openaitts

var contractSchema = map[string]any{"text-to-speech": map[string]any{"models": []any{"tts-1", "tts-1-hd"}, "fields_by_model": map[string]any{"tts-1": map[string]any{"model": map[string]any{"required": true}, "text": map[string]any{"required": true, "max": 4096, "length": true}}, "tts-1-hd": map[string]any{"model": map[string]any{"required": true}, "text": map[string]any{"required": true, "max": 4096, "length": true}}}}}
