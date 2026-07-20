/** Parameters for OpenAI text-to-speech generation. */
export interface TextToSpeechParams {
  /** Model slug accepted by the OpenAI TTS catalog. */
  model: string;
  /** Text to synthesize. */
  text: string;
}

/** A RunAPI-managed MP3 audio result. */
export interface Audio {
  url: string;
  format: string;
  mime_type: string;
  size_bytes: number;
}

/** Result of a synchronous text-to-speech request. */
export interface TextToSpeechResponse {
  id: string;
  status: 'completed';
  audios: Audio[];
  error?: string;
  [key: string]: unknown;
}
