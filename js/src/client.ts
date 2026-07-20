import { BaseClient, type ClientOptions } from '@runapi.ai/core';
import { TextToSpeech } from './resources/text-to-speech';

/** OpenAI TTS speech generation client. */
export class OpenaiTtsClient extends BaseClient {
  /** Generate speech from text. */
  public readonly textToSpeech: TextToSpeech;

  constructor(options: ClientOptions = {}) {
    super(options);
    this.textToSpeech = new TextToSpeech(this.http);
  }
}
