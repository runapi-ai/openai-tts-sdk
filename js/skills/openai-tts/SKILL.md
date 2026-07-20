---
name: openai-tts
description: Generate MP3 speech with OpenAI TTS through RunAPI. Use for one-off speech generation or application integration. Prefer the RunAPI CLI for one-off requests and the target-language SDK for production integration.
documentation: https://runapi.ai/models/openai-tts.md
provider_page: https://runapi.ai/providers/openai.md
catalog: https://runapi.ai/models.md
metadata:
  openclaw:
    homepage: https://runapi.ai/models/openai-tts
    requires:
      bins:
      - runapi
    install:
    - kind: brew
      formula: runapi-ai/tap/runapi
      bins:
      - runapi
    envVars:
    - name: RUNAPI_API_KEY
      required: false
      description: Optional RunAPI API key; prefer environment auth or saved CLI config.
---

# OpenAI TTS on RunAPI

Generate synchronous MP3 speech with `tts-1` or `tts-1-hd`. Use the target-language SDK for application integration and the `runapi` CLI for one-off speech generation or manual verification.

## Critical: Integration Runtime

- Integration work (app, backend, worker, library, Rails service, Node service, Go service, webhook pipeline, or production codebase) uses the **SDK integration path** for the target language.
- One-off generation, manual smoke tests, debugging, or user-requested CLI runs use the **CLI path** with the `runapi` binary. For full CLI-specific agent guidance, see https://github.com/runapi-ai/cli-skill.
- Never shell out to the `runapi` CLI as the production runtime integration layer.

## SDK integration path

When integrating OpenAI TTS into an application or production workflow, check the current SDK package and official usage. Confirm the install command, synchronous `run` method, request fields, managed audio response, and error classes before using CLI help or raw HTTP examples. Use a RunAPI SDK package:

- JavaScript / TypeScript: `@runapi.ai/openai-tts`
- Python: `runapi-openai-tts`
- Ruby: `runapi-openai-tts`
- Go: `github.com/runapi-ai/openai-tts-sdk/go`
- Java: `ai.runapi:runapi-openai-tts`
- PHP: `runapi-ai/openai-tts`

The synchronous `text_to_speech` resource accepts `model` and `text` and returns a completed response with RunAPI-managed `audios`.

## CLI path

The `runapi` binary is the one-off and manual testing runtime dependency. For full CLI-specific agent guidance, see https://github.com/runapi-ai/cli-skill. Run `runapi auth status` first. Prefer `RUNAPI_API_KEY` or import a token with `printf '%s' "$RUNAPI_API_KEY" | runapi auth import-token --token -`.

Inspect current fields before creating a request:

```shell
runapi openai-tts --help
runapi openai-tts text-to-speech --help
runapi openai-tts text-to-speech --input-file request.json
```

The request file contains only `model` and `text`. The command returns one completed response; do not add `--async` or call `runapi wait`.

## Variants

- `tts-1`: https://runapi.ai/models/openai-tts/tts-1.md
- `tts-1-hd`: https://runapi.ai/models/openai-tts/tts-1-hd.md

## Result handling

The response contains `id`, `status`, and one `audios` entry with `url`, `format`, `mime_type`, and `size_bytes`. The URL points to RunAPI-managed storage. Keep API keys in `RUNAPI_API_KEY` or saved RunAPI config.

## References

- Model overview: https://runapi.ai/models/openai-tts.md
- tts-1: https://runapi.ai/models/openai-tts/tts-1.md
- tts-1-hd: https://runapi.ai/models/openai-tts/tts-1-hd.md
- Provider page: https://runapi.ai/providers/openai.md
- Full catalog: https://runapi.ai/models.md
