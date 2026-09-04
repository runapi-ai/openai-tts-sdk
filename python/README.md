# OpenAI TTS Python SDK for RunAPI

Install `runapi-openai-tts`, create `OpenaiTtsClient`, and call `client.text_to_speech.run(model="tts-1", text="Hello")`.

`run()` returns the same typed terminal result whether the request completes directly or is accepted for background processing. Call `subscribe(location)` to resume an accepted request from its opaque Task Result URL.

Model details and pricing: https://runapi.ai/models/openai-tts

Licensed under the Apache License, Version 2.0.
