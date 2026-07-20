"""OpenAI TTS client for RunAPI."""

from runapi.core import (
    AuthenticationError,
    InsufficientCreditsError,
    NotFoundError,
    RateLimitError,
    ValidationError,
)

from .client import OpenaiTtsClient

__all__ = [
    "OpenaiTtsClient",
    "AuthenticationError",
    "RateLimitError",
    "InsufficientCreditsError",
    "NotFoundError",
    "ValidationError",
]
