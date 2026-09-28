package ai.runapi.openaitts.types;

import ai.runapi.core.polling.TaskStatus;
import ai.runapi.core.polling.TaskUsage;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Response for text to speech operations. */
public class TextToSpeechResponse {
  @JsonProperty("id")
  private String id;

  @JsonProperty("error")
  private String error;

  @JsonProperty("status")
  private String status;

  @JsonProperty("audios")
  private List<Audio> audios;

  @JsonProperty("usage")
  private TaskUsage usage;

  private final Map<String, JsonNode> extraFields = new LinkedHashMap<String, JsonNode>();

  /** Returns the response ID. */
  public String getId() {
    return id;
  }

  /** Returns the error message, if the request failed. */
  public String getError() {
    return error;
  }

  /** Returns the completed response status. */
  public TaskStatus getStatus() {
    return new TaskStatus(status == null ? "" : status);
  }

  /** Returns the generated audio results, when present. */
  public List<Audio> getAudios() {
    return audios == null ? null : Collections.unmodifiableList(audios);
  }

  /** Settled cost in USD. Present only on completed Task envelopes. */
  public TaskUsage getUsage() {
    return usage;
  }

  /** Returns unrecognized response fields preserved from the API response. */
  @JsonAnyGetter
  public Map<String, JsonNode> extraFields() {
    return Collections.unmodifiableMap(extraFields);
  }

  @JsonAnySetter
  void putExtraField(String name, JsonNode value) {
    extraFields.put(name, value);
  }
}
