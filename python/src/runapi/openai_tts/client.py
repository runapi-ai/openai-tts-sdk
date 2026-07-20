"""OpenAI TTS client."""

from __future__ import annotations

from typing import Any, Optional

from runapi.core import ClientOptions, HttpClient, resolve_api_key

from .resources.text_to_speech import TextToSpeech


class OpenaiTtsClient:
    """OpenAI TTS speech generation client."""

    def __init__(self, api_key: Optional[str] = None, **options: Any) -> None:
        resolved_api_key = resolve_api_key(api_key)
        client_options = ClientOptions(api_key=resolved_api_key, **options)
        http = client_options.http_client or HttpClient(client_options)
        self.text_to_speech = TextToSpeech(http)
