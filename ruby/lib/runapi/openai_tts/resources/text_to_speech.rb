# frozen_string_literal: true

module RunApi
  module OpenaiTts
    module Resources
      # Generates RunAPI-managed MP3 speech from text.
      class TextToSpeech
        include RunApi::Core::ResourceHelpers

        ENDPOINT = "/api/v1/openai_tts/text_to_speech"
        RESPONSE_CLASS = Types::TextToSpeechResponse

        def initialize(http)
          @http = http
        end

        def run(options: nil, **params)
          params = compact_params(params)
          validate_contract!(CONTRACT["text-to-speech"], params)
          request(:post, ENDPOINT, body: params, options: options)
        end
      end
    end
  end
end
