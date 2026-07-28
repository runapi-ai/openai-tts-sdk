import { beforeEach, describe, expect, it, vi } from 'vitest';
import type { HttpClient } from '@runapi.ai/core';
import { TextToSpeech } from '../../src/resources/text-to-speech';

describe('OpenAI TTS resources', () => {
  const mockHttp: HttpClient = { request: vi.fn() };

  beforeEach(() => vi.clearAllMocks());

  it('posts text-to-speech params and decodes managed audio', async () => {
    vi.mocked(mockHttp.request).mockResolvedValueOnce({
      id: 'task_1',
      status: 'completed',
      audios: [{ url: 'https://runapi.ai/rails/active_storage/audio.mp3', format: 'mp3', mime_type: 'audio/mpeg', size_bytes: 128 }],
      billing: { reservation: { amount_cents: 2 }, settlement: { charged_amount_cents: 2, amount_micro_cents: 2_000_000 }, refund: null },
    });
    const resource = new TextToSpeech(mockHttp);

    const result = await resource.run({ model: 'tts-1', text: 'Hello from RunAPI' });

    expect(mockHttp.request).toHaveBeenCalledWith('POST', '/api/v1/openai_tts/text_to_speech', {
      body: { model: 'tts-1', text: 'Hello from RunAPI' },
    });
    expect(result.audios[0]?.mime_type).toBe('audio/mpeg');
    expect(result.billing.reservation?.amount_cents).toBe(2);
  });
});
