export const contract = {
  "text-to-speech": {
    "models": [
      "tts-1",
      "tts-1-hd"
    ],
    "fields_by_model": {
      "tts-1": {
        "model": {
          "required": true
        },
        "text": {
          "required": true,
          "max": 4096,
          "length": true
        }
      },
      "tts-1-hd": {
        "model": {
          "required": true
        },
        "text": {
          "required": true,
          "max": 4096,
          "length": true
        }
      }
    }
  }
} as const;
