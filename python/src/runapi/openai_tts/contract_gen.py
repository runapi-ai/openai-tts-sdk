CONTRACT = {
    "text-to-speech": {
        "models": ["tts-1", "tts-1-hd"],
        "fields_by_model": {
            "tts-1": {
                "text": {
                    "required": True,
                    "max": 4096,
                    "length": True
                }
            },
            "tts-1-hd": {
                "text": {
                    "required": True,
                    "max": 4096,
                    "length": True
                }
            }
        }
    }
}
