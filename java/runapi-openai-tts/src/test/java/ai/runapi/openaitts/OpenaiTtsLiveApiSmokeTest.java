    package ai.runapi.openaitts;

    import static org.junit.jupiter.api.Assertions.assertEquals;
    import static org.junit.jupiter.api.Assertions.assertNotNull;
    import static org.junit.jupiter.api.Assumptions.assumeTrue;

        import ai.runapi.core.errors.TaskFailedException;
    import ai.runapi.core.RequestOptions;
    import ai.runapi.core.json.Json;
    import ai.runapi.openaitts.types.TextToSpeechResponse;
    import ai.runapi.openaitts.types.TextToSpeechModel;
import ai.runapi.openaitts.types.TextToSpeechParams;
import ai.runapi.openaitts.types.TextToSpeechResponse;
    import com.fasterxml.jackson.databind.node.ObjectNode;
    import java.nio.charset.StandardCharsets;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.time.Duration;
    import org.junit.jupiter.api.Test;

    class OpenaiTtsLiveApiSmokeTest {
      @Test
      void primaryResourceRunAgainstLiveRunApi() throws Exception {
        assumeTrue("true".equals(System.getenv("RUNAPI_JAVA_LIVE_OPENAI_TTS_SMOKE")));

        String baseUrl = requireEnv("RUNAPI_BASE_URL");
        String apiKey = requireEnv("RUNAPI_API_KEY");

        Path outputPath = Paths.get(System.getenv().getOrDefault("RUNAPI_JAVA_LIVE_OPENAI_TTS_OUTPUT", "build/live-openai-tts-smoke-result.json"));
        Files.createDirectories(outputPath.getParent());
        try (OpenaiTtsClient client = OpenaiTtsClient.builder().apiKey(apiKey).baseUrl(baseUrl).build()) {
          ObjectNode result = Json.mapper().createObjectNode();
          result.put("action", "openai-tts/text-to-speech");
          result.put("result_field", "audios");

          try {
      TextToSpeechResponse response = client.textToSpeech().run(
              TextToSpeechParams.builder()
                  .model(TextToSpeechModel.TTS_1)
                  .text("sample")
                  .build()
      );

          assertEquals("completed", response.getStatus().value());
            assertNotNull(response.getAudios());
            result.put("id", response.getId());
            result.put("status", response.getStatus().value());
            Files.write(outputPath, Json.mapper().writerWithDefaultPrettyPrinter().writeValueAsString(result).getBytes(StandardCharsets.UTF_8));
          } catch (TaskFailedException failure) {
            result.put("status", "failed");
            result.put("exception", failure.getClass().getSimpleName());
            result.put("message", failure.getMessage());

            Files.write(outputPath, Json.mapper().writerWithDefaultPrettyPrinter().writeValueAsString(result).getBytes(StandardCharsets.UTF_8));
            throw failure;
          } catch (RuntimeException failure) {
            result.put("status", "error");
            result.put("exception", failure.getClass().getSimpleName());
            result.put("message", failure.getMessage());
            Files.write(outputPath, Json.mapper().writerWithDefaultPrettyPrinter().writeValueAsString(result).getBytes(StandardCharsets.UTF_8));
            throw failure;
          }
        }
      }

      private static String callbackUrl(String modelSlug) {
        String base = requireEnv("RUNAPI_CALLBACK_URL");
        String normalized = base.endsWith("/") ? base.substring(0, base.length() - 1) : base;
        return normalized + "/java-live-smoke/" + modelSlug + "/" + System.currentTimeMillis();
      }

      private static String requireEnv(String name) {
        String value = System.getenv(name);
        if (value == null || value.trim().isEmpty()) {
          throw new IllegalStateException(name + " is required");
        }
        return value;
      }
    }
