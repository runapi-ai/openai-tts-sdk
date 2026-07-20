# frozen_string_literal: true

module RunApi
  module OpenaiTts
    module Types
      # A RunAPI-managed MP3 audio result.
      class Audio < RunApi::Core::BaseModel
        required :url, String
        required :format, String
        required :mime_type, String
        required :size_bytes, Integer
      end

      # Completed synchronous text-to-speech response.
      class TextToSpeechResponse < RunApi::Core::BaseModel
        required :id, String
        required :status, String
        required :audios, [-> { Audio }]
        optional :error, String
      end
    end
  end
end
