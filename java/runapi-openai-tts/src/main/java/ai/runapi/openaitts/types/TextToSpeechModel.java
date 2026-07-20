package ai.runapi.openaitts.types;

import com.fasterxml.jackson.annotation.JsonCreator;

/** Model slug for text to speech operations. */
public final class TextToSpeechModel extends OpenaittsValue {
  /** tts-1 model slug. */
  public static final TextToSpeechModel TTS_1 = new TextToSpeechModel("tts-1");
  /** tts-1-hd model slug. */
  public static final TextToSpeechModel TTS_1_HD = new TextToSpeechModel("tts-1-hd");

  /** Creates a model value from a literal model slug. */
  @JsonCreator
  public TextToSpeechModel(String value) {
    super(value);
  }
}
