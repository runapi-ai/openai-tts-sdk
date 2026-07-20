package ai.runapi.openaitts.types;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Parameters for text to speech operations. */
public final class TextToSpeechParams {
  private final String model;
  private final String text;

  private TextToSpeechParams(Builder builder) {
    this.model = builder.model;
    this.text = OpenaittsParamUtils.requireNonBlank(builder.text, "text");
  }

  /** Creates a new TextToSpeechParams builder. */
  public static Builder builder() {
    return new Builder();
  }

  /** Returns the RunAPI action key for this request. */
  public String action() {
    return "openai-tts/text-to-speech";
  }

  /** Converts these parameters to the JSON request body shape. */
  public Map<String, Object> toMap() {
    Map<String, Object> raw = new LinkedHashMap<String, Object>();
    raw.put("model", OpenaittsParamUtils.wireValue(model));
    raw.put("text", OpenaittsParamUtils.wireValue(text));
    return OpenaittsParamUtils.compact(raw);
  }



  /** Builder for {@link TextToSpeechParams}. */
  public static final class Builder {
    private String model;
    private String text;

    private Builder() {}

    /** Sets the model slug using a typed model value. */
    public Builder model(TextToSpeechModel value) {
      this.model = java.util.Objects.requireNonNull(value, "model").value();
      return this;
    }

    /** Sets the model slug using a string value. */
    public Builder model(String value) {
      this.model = OpenaittsParamUtils.requireNonBlankTrim(value, "model");
      return this;
    }


    /** Sets the line text. */
    public Builder text(String value) {
      this.text = OpenaittsParamUtils.requireNonBlank(value, "text");
      return this;
    }

    /** Builds immutable text to speech parameters. */
    public TextToSpeechParams build() {
      return new TextToSpeechParams(this);
    }
  }
}
