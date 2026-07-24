"""OpenAI TTS response models."""

from runapi.core import BaseModel, TaskResponse, optional, required


class Audio(BaseModel):
    """A RunAPI-managed MP3 audio result."""

    url = required(str)
    format = required(str)
    mime_type = required(str)
    size_bytes = required(int)


class TextToSpeechResponse(TaskResponse):
    """Completed synchronous text-to-speech response."""

    id = required(str)
    status = required(str)
    audios = required([lambda: Audio])
    error = optional(str)
