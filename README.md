<p align="center">
  <a href="https://runapi.ai"><img src="https://runapi.ai/icon.svg" height="56" alt="RunAPI"></a>
</p>

<h3 align="center"><a href="https://github.com/runapi-ai/openai-tts-sdk">OpenAI TTS API SDK for RunAPI</a></h3>

<p align="center">OpenAI TTS API SDKs for JavaScript, Python, Ruby, Go, Java, and PHP on RunAPI.</p>

<div align="center">

[![npm](https://img.shields.io/npm/v/@runapi.ai/openai-tts)](https://www.npmjs.com/package/@runapi.ai/openai-tts)
[![PyPI](https://img.shields.io/pypi/v/runapi-openai-tts)](https://pypi.org/project/runapi-openai-tts/)
[![RubyGems](https://img.shields.io/gem/v/runapi-openai-tts)](https://rubygems.org/gems/runapi-openai-tts)
[![Go Reference](https://pkg.go.dev/badge/github.com/runapi-ai/openai-tts-sdk/go.svg)](https://pkg.go.dev/github.com/runapi-ai/openai-tts-sdk/go)
[![Maven Central](https://img.shields.io/maven-central/v/ai.runapi/runapi-openai-tts)](https://central.sonatype.com/artifact/ai.runapi/runapi-openai-tts)
[![License](https://img.shields.io/github/license/runapi-ai/openai-tts-sdk)](https://github.com/runapi-ai/openai-tts-sdk/blob/main/LICENSE)

</div>

Generate MP3 speech from text with `tts-1` or `tts-1-hd`. RunAPI validates and stores each audio result before returning it.

## Install

```bash
npm install @runapi.ai/openai-tts
pip install runapi-openai-tts
gem install runapi-openai-tts
go get github.com/runapi-ai/openai-tts-sdk/go@latest
```

For Java, install `ai.runapi:runapi-openai-tts:0.1.0`. The PHP package is released from the split repository at https://github.com/runapi-ai/openai-tts-php.

## Quick start

```typescript
import { OpenaiTtsClient } from '@runapi.ai/openai-tts';

const client = new OpenaiTtsClient();
const result = await client.textToSpeech.run({ model: 'tts-1', text: 'Hello from RunAPI' });
console.log(result.audios[0].url);
```

## Variants

- [tts-1](https://runapi.ai/models/openai-tts/tts-1)
- [tts-1-hd](https://runapi.ai/models/openai-tts/tts-1-hd)

The returned URL points to RunAPI-managed storage. See the [OpenAI TTS model page](https://runapi.ai/models/openai-tts) for current pricing, limits, and usage details.

## License

Licensed under the Apache License, Version 2.0.
