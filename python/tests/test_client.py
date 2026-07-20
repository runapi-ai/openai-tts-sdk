import pytest

from runapi.core import config
from runapi.core.errors import ValidationError
from runapi.openai_tts import OpenaiTtsClient
from runapi.openai_tts.types import TextToSpeechResponse


class FakeHttp:
    def __init__(self, *responses):
        self._responses = list(responses)
        self.calls = []

    def request(self, method, path, body=None, options=None):
        self.calls.append((method, path, body))
        return self._responses.pop(0)


@pytest.fixture(autouse=True)
def reset_config(monkeypatch):
    monkeypatch.delenv("RUNAPI_API_KEY", raising=False)
    monkeypatch.setattr(config, "api_key", None)


def test_run_posts_params_and_decodes_managed_audio():
    fake = FakeHttp({"id": "task_1", "status": "completed", "audios": [{"url": "https://runapi.ai/audio.mp3", "format": "mp3", "mime_type": "audio/mpeg", "size_bytes": 128}]})
    client = OpenaiTtsClient(api_key="k", http_client=fake)

    result = client.text_to_speech.run(model="tts-1", text="Hello")

    assert fake.calls == [("post", "/api/v1/openai_tts/text_to_speech", {"model": "tts-1", "text": "Hello"})]
    assert isinstance(result, TextToSpeechResponse)
    assert result.audios[0].mime_type == "audio/mpeg"


def test_run_requires_text():
    client = OpenaiTtsClient(api_key="k", http_client=FakeHttp())
    with pytest.raises(ValidationError, match="text is required"):
        client.text_to_speech.run(model="tts-1")
