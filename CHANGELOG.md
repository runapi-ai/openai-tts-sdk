# Changelog

## [go/v0.2.1](https://github.com/runapi-ai/openai-tts-sdk/releases/tag/go%2Fv0.2.1), [java/v0.1.2](https://github.com/runapi-ai/openai-tts-sdk/releases/tag/java%2Fv0.1.2) - 2026-09-28

### Added
- Return usage.cost as a float USD amount on completed async Task query and webhook envelopes.

### Removed
- Remove the public Task billing object from Task envelopes.
  Migration: Read usage.cost on completed Task envelopes. Create, processing, and failed envelopes omit usage.


## [python/v0.3.0](https://github.com/runapi-ai/openai-tts-sdk/releases/tag/python%2Fv0.3.0) - 2026-09-04

### Added
- Automatically follow accepted Task results for speech generation.

## [go/v0.2.0](https://github.com/runapi-ai/openai-tts-sdk/releases/tag/go%2Fv0.2.0) - 2026-09-04

### Added
- Add Create, Subscribe, and automatic Run support for speech generation when a Task is accepted.

## [ruby/v0.2.0](https://github.com/runapi-ai/openai-tts-sdk/releases/tag/ruby%2Fv0.2.0) - 2026-09-04

### Added
- Add run and subscribe support for hybrid Task responses.


## [ruby/v0.1.3](https://github.com/runapi-ai/openai-tts-sdk/releases/tag/ruby%2Fv0.1.3) - 2026-08-18

### Changed
- Allow Ruby clients to install the core SDK release that adds persistent Files and multipart Uploads alongside this model SDK.


## [js/v0.1.2](https://github.com/runapi-ai/openai-tts-sdk/releases/tag/js%2Fv0.1.2), [ruby/v0.1.2](https://github.com/runapi-ai/openai-tts-sdk/releases/tag/ruby%2Fv0.1.2), [go/v0.1.2](https://github.com/runapi-ai/openai-tts-sdk/releases/tag/go%2Fv0.1.2), [python/v0.2.1](https://github.com/runapi-ai/openai-tts-sdk/releases/tag/python%2Fv0.2.1) - 2026-07-28

### Fixed
- Validate the required model before sending text-to-speech requests.


## [java/v0.1.1](https://github.com/runapi-ai/openai-tts-sdk/releases/tag/java%2Fv0.1.1) - 2026-07-28

### Added
- Decode typed Task Billing Facts on synchronous text-to-speech responses.

## [go/v0.1.1](https://github.com/runapi-ai/openai-tts-sdk/releases/tag/go%2Fv0.1.1) - 2026-07-28

### Added
- Expose persisted billing facts on task responses.

## [js/v0.1.1](https://github.com/runapi-ai/openai-tts-sdk/releases/tag/js%2Fv0.1.1), [ruby/v0.1.1](https://github.com/runapi-ai/openai-tts-sdk/releases/tag/ruby%2Fv0.1.1) - 2026-07-28

### Added
- Type task billing facts on text-to-speech responses.


## [python/v0.2.0](https://github.com/runapi-ai/openai-tts-sdk/releases/tag/python%2Fv0.2.0) - 2026-07-24

### Added
- Expose shared Files, Account, and Pricing resources plus typed Task Billing Facts through the Provider Client.


## [js/v0.1.0](https://github.com/runapi-ai/openai-tts-sdk/releases/tag/js%2Fv0.1.0), [ruby/v0.1.0](https://github.com/runapi-ai/openai-tts-sdk/releases/tag/ruby%2Fv0.1.0), [go/v0.1.0](https://github.com/runapi-ai/openai-tts-sdk/releases/tag/go%2Fv0.1.0), [python/v0.1.0](https://github.com/runapi-ai/openai-tts-sdk/releases/tag/python%2Fv0.1.0), [java/v0.1.0](https://github.com/runapi-ai/openai-tts-sdk/releases/tag/java%2Fv0.1.0) - 2026-07-20

### Added
- Add synchronous text-to-speech clients with typed managed MP3 responses.
- Support tts-1 and tts-1-hd with model and text inputs.
