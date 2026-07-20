"""OpenAI TTS text-to-speech resource."""

from __future__ import annotations

from typing import Any, Optional

from runapi.core import RequestOptions, Resource

from ..contract_gen import CONTRACT
from ..types import TextToSpeechResponse


class TextToSpeech(Resource):
    """Generate a RunAPI-managed MP3 from text."""

    ENDPOINT = "/api/v1/openai_tts/text_to_speech"
    RESPONSE_CLASS = TextToSpeechResponse

    def run(self, options: Optional[RequestOptions] = None, **params: Any) -> Any:
        compacted = self._compact_params(params)
        self._validate_contract(CONTRACT["text-to-speech"], compacted)
        return self._request("post", self.ENDPOINT, body=compacted, options=options)
