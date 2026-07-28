package ai.runapi.openaitts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ai.runapi.core.RequestOptions;
import ai.runapi.core.errors.ValidationException;
import ai.runapi.core.http.HttpRequest;
import ai.runapi.core.http.HttpResponse;
import ai.runapi.core.http.HttpTransport;
import ai.runapi.core.http.JsonRequestBody;
import ai.runapi.core.json.Json;
import ai.runapi.openaitts.types.TextToSpeechResponse;
import ai.runapi.openaitts.types.TextToSpeechResponse;
import ai.runapi.openaitts.types.TextToSpeechModel;
import ai.runapi.openaitts.types.TextToSpeechParams;
import ai.runapi.openaitts.types.TextToSpeechResponse;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.ByteArrayOutputStream;
import java.time.Duration;
import java.util.Collections;
import org.junit.jupiter.api.Test;

class OpenaiTtsClientTest {
  @Test
  void builderCreatesClientAndUniversalResources() {
    OpenaiTtsClient client = OpenaiTtsClient.builder().apiKey("sk-test").build();

    assertNotNull(client.textToSpeech());
    assertNotNull(client.files());
    assertNotNull(client.account());
  }

  @Test
  void openValueClassesSerializeAsScalarStrings() throws Exception {
    String json = Json.mapper().writeValueAsString(new TextToSpeechModel("tts-1"));

    assertEquals("\"tts-1\"", json);
    assertEquals(new TextToSpeechModel("tts-1"), Json.mapper().readValue(json, TextToSpeechModel.class));
  }

  @Test
  void runSendsExpectedRequestShape() throws Exception {
    CapturingTransport transport = new CapturingTransport("{\"id\":\"sync_123\",\"status\":\"completed\",\"audios\":[{\"url\":\"https://file.runapi.ai/generated.mp3\",\"format\":\"mp3\",\"mime_type\":\"audio/mpeg\",\"size_bytes\":128}],\"billing\":{\"reservation\":{\"amount_cents\":12}},\"custom\":\"kept\"}");
    OpenaiTtsClient client = OpenaiTtsClient.builder().apiKey("sk-test").transport(transport).build();

    client.textToSpeech().run(
        TextToSpeechParams.builder()
            .model(TextToSpeechModel.TTS_1)
            .text("sample")
            .build()
    );

    assertEquals("POST", transport.request.getMethod().name());
    assertEquals("/api/v1/openai_tts/text_to_speech", transport.request.getPath());
    JsonNode body = bodyJson(transport.request);
    assertNotNull(body);

  }

  @Test
  void runDecodesResponseAndExtraFields() {
    CapturingTransport transport = new CapturingTransport("{\"id\":\"sync_123\",\"status\":\"completed\",\"audios\":[{\"url\":\"https://file.runapi.ai/generated.mp3\",\"format\":\"mp3\",\"mime_type\":\"audio/mpeg\",\"size_bytes\":128}],\"billing\":{\"reservation\":{\"amount_cents\":12}},\"custom\":\"kept\"}");
    OpenaiTtsClient client = OpenaiTtsClient.builder().apiKey("sk-test").transport(transport).build();

    TextToSpeechResponse response = client.textToSpeech().run(
        TextToSpeechParams.builder()
            .model(TextToSpeechModel.TTS_1)
            .text("sample")
            .build()
    );

    assertEquals("POST", transport.request.getMethod().name());
    assertEquals("/api/v1/openai_tts/text_to_speech", transport.request.getPath());
    assertNotNull(response.getAudios());
    assertEquals("completed", response.getStatus().value());
    assertEquals("audio/mpeg", response.getAudios().get(0).getMimeType());
    assertEquals(Long.valueOf(128), response.getAudios().get(0).getSizeBytes());
    assertEquals(Long.valueOf(12), response.getBilling().getReservation().getAmountCents());
    assertEquals("kept", response.extraFields().get("custom").asText());
  }

    @Test
    void coversTexttospeechResourceMethods() {
      CapturingTransport transport = new CapturingTransport("{\"id\":\"sync_text_to_speech\",\"status\":\"completed\",\"audios\":[{\"url\":\"https://file.runapi.ai/generated.mp3\",\"format\":\"mp3\",\"mime_type\":\"audio/mpeg\",\"size_bytes\":128}],\"billing\":{\"reservation\":{\"amount_cents\":12}}}");
      OpenaiTtsClient client = OpenaiTtsClient.builder().apiKey("sk-test").transport(transport).build();

      TextToSpeechResponse response = client.textToSpeech().run(
              TextToSpeechParams.builder()
                  .model(TextToSpeechModel.TTS_1)
                  .text("sample")
                  .build()
      );
      assertNotNull(response);
      assertEquals(Long.valueOf(12), response.getBilling().getReservation().getAmountCents());

      CapturingTransport transportWithOptions = new CapturingTransport("{\"id\":\"sync_text_to_speech_options\",\"status\":\"completed\",\"audios\":[{\"url\":\"https://file.runapi.ai/generated.mp3\",\"format\":\"mp3\",\"mime_type\":\"audio/mpeg\",\"size_bytes\":128}],\"billing\":{\"reservation\":{\"amount_cents\":12}}}");
      OpenaiTtsClient clientWithOptions = OpenaiTtsClient.builder().apiKey("sk-test").transport(transportWithOptions).build();
      assertNotNull(clientWithOptions.textToSpeech().run(
              TextToSpeechParams.builder()
                  .model(TextToSpeechModel.TTS_1)
                  .text("sample")
                  .build(),
          RequestOptions.none()));
    }

  private static JsonNode bodyJson(HttpRequest request) throws Exception {
    JsonRequestBody body = (JsonRequestBody) request.getBody();
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    body.writeTo(out);
    return Json.mapper().readTree(out.toByteArray());
  }

  private static final class CapturingTransport implements HttpTransport {
    private final String body;
    private HttpRequest request;

    private CapturingTransport(String body) {
      this.body = body;
    }

    public HttpResponse send(HttpRequest request) {
      this.request = request;
      return new HttpResponse(200, body, Collections.<String, java.util.List<String>>emptyMap());
    }

    public void close() {}
  }

  private static final class SequenceTransport implements HttpTransport {
    private final String[] responses;
    private int calls;

    private SequenceTransport(String... responses) {
      this.responses = responses;
    }

    public HttpResponse send(HttpRequest request) {
      String response = responses[Math.min(calls, responses.length - 1)];
      calls++;
      return new HttpResponse(200, response, Collections.<String, java.util.List<String>>emptyMap());
    }

    public void close() {}
  }
}
