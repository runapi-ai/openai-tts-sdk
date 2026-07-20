package ai.runapi.openaitts.resources;

import ai.runapi.core.ClientOptions;
import ai.runapi.core.RequestOptions;
import ai.runapi.core.http.HttpTransport;
import ai.runapi.openaitts.types.TextToSpeechParams;
import ai.runapi.openaitts.types.TextToSpeechResponse;

/** Text To Speech operations. */
public final class TextToSpeechResource extends OpenaittsResource {
  /** API endpoint path for text to speech operations. */
  public static final String ENDPOINT = "/api/v1/openai_tts/text_to_speech";

  /** Creates a resource bound to the supplied transport and client options. */
  public TextToSpeechResource(HttpTransport transport, ClientOptions options) {
    super(transport, options, ENDPOINT);
  }

  /** Runs text to speech and returns the response. */
  public TextToSpeechResponse run(TextToSpeechParams params) {
    return run(params, RequestOptions.none());
  }

  /** Runs text to speech with per-request options and returns the response. */
  public TextToSpeechResponse run(TextToSpeechParams params, RequestOptions options) {
    return runSync(params.action(), params.toMap(), options, TextToSpeechResponse.class);
  }
}
