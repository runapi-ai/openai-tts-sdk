package ai.runapi.openaitts.types;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/** Media result URL. */
public final class Audio {
  @JsonProperty("url")
  private String url;

  @JsonProperty("format")
  private String format;

  @JsonProperty("mime_type")
  private String mimeType;

  @JsonProperty("size_bytes")
  private Long sizeBytes;

  private final Map<String, JsonNode> extraFields = new LinkedHashMap<String, JsonNode>();

  /** Returns the media URL. */
  public String getUrl() {
    return url;
  }

  /** Returns the audio format. */
  public String getFormat() {
    return format;
  }

  /** Returns the audio MIME type. */
  public String getMimeType() {
    return mimeType;
  }

  /** Returns the audio size in bytes. */
  public Long getSizeBytes() {
    return sizeBytes;
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
