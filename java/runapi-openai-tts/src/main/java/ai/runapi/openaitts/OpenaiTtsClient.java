package ai.runapi.openaitts;

import ai.runapi.core.BaseClient;
import ai.runapi.core.ClientOptions;
import ai.runapi.core.http.HttpTransport;
import java.net.URI;
import ai.runapi.openaitts.resources.TextToSpeechResource;

/** OpenaiTts model-family Java SDK client. */
public final class OpenaiTtsClient extends BaseClient {
  private final TextToSpeechResource textToSpeech;

  private OpenaiTtsClient(ClientOptions options) {
    super(options);
    this.textToSpeech = new TextToSpeechResource(transport(), options());
  }

  /** Creates a new OpenaiTtsClient builder. */
  public static Builder builder() {
    return new Builder();
  }

  /** Text To Speech operations. */
  public TextToSpeechResource textToSpeech() {
    return textToSpeech;
  }

  /** Builder for {@link OpenaiTtsClient}. */
  public static final class Builder extends BaseClient.Builder<Builder> {
    private Builder() {}

    /** Sets the API key. If omitted, the SDK reads {@code RUNAPI_API_KEY}. */
    @Override
    public Builder apiKey(String value) {
      return super.apiKey(value);
    }

    /** Sets the RunAPI base URL. If omitted, the SDK reads {@code RUNAPI_BASE_URL}. */
    @Override
    public Builder baseUrl(String value) {
      return super.baseUrl(value);
    }

    /** Sets the RunAPI base URL from a URI. */
    @Override
    public Builder baseUrl(URI value) {
      return super.baseUrl(value);
    }

    /** Sets a custom HTTP transport. User-provided transports are not closed by SDK clients. */
    @Override
    public Builder transport(HttpTransport value) {
      return super.transport(value);
    }

    /** Builds an immutable OpenaiTtsClient. */
    @Override
    public OpenaiTtsClient build() {
      return new OpenaiTtsClient(options.build());
    }
  }
}
