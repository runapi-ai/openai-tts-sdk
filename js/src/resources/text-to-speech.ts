import type { ActionSchema, HttpClient, RequestOptions } from '@runapi.ai/core';
import { compactParams, validateParams } from '@runapi.ai/core';
import { contract } from '../contract_gen';
import type { TextToSpeechParams, TextToSpeechResponse } from '../types';

const ENDPOINT = '/api/v1/openai_tts/text_to_speech';

/** Generate a RunAPI-managed MP3 from text. */
export class TextToSpeech {
  constructor(private readonly http: HttpClient) {}

  /** Generate speech synchronously. */
  async run(params: TextToSpeechParams, options?: RequestOptions): Promise<TextToSpeechResponse> {
    const body = compactParams(params);
    validateParams(contract['text-to-speech'] as ActionSchema, body as Record<string, unknown>);
    return this.http.request<TextToSpeechResponse>('POST', ENDPOINT, { body, ...options });
  }
}
