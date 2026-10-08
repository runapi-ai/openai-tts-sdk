import type { HttpClient, RequestOptions } from '@runapi.ai/core';
import { compactParams } from '@runapi.ai/core';
import type { TextToSpeechParams, TextToSpeechResponse } from '../types';

const ENDPOINT = '/api/v1/openai_tts/text_to_speech';

/** Generate a RunAPI-managed MP3 from text. */
export class TextToSpeech {
  constructor(private readonly http: HttpClient) {}

  /** Generate speech synchronously. */
  async run(params: TextToSpeechParams, options?: RequestOptions): Promise<TextToSpeechResponse> {
    const body = compactParams(params);
    return this.http.request<TextToSpeechResponse>('POST', ENDPOINT, { body, ...options });
  }
}
