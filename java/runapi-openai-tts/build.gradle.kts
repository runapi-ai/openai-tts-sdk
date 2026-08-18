plugins {
  `java-library`
  `maven-publish`
}

extra["runapiSlug"] = "openai-tts"

description = "RunAPI OpenAI TTS Java SDK for OpenAI TTS workflows."

java {
  withSourcesJar()
  withJavadocJar()
}

dependencies {
  api("ai.runapi:runapi-core:0.5.0")

  testImplementation(platform("org.junit:junit-bom:5.10.3"))
  testImplementation("org.junit.jupiter:junit-jupiter")
}

publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      from(components["java"])
      artifactId = "runapi-openai-tts"
      pom {
        name = "RunAPI OpenAI TTS Java SDK"
        description = "RunAPI OpenAI TTS Java SDK for OpenAI TTS workflows."
        url = "https://runapi.ai/models/openai-tts"
        licenses {
          license {
            name = "Apache License, Version 2.0"
            url = "https://www.apache.org/licenses/LICENSE-2.0"
          }
        }
        developers {
          developer {
            id = "runapi"
            name = "RunAPI"
            email = "contact@runapi.ai"
          }
        }
        scm {
          url = "https://github.com/runapi-ai/openai-tts-sdk"
          connection = "scm:git:https://github.com/runapi-ai/openai-tts-sdk.git"
          developerConnection = "scm:git:ssh://git@github.com/runapi-ai/openai-tts-sdk.git"
        }
      }
    }
  }
}
