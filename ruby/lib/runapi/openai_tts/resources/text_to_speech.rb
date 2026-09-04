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
          run_hybrid(ENDPOINT, body: params, options: options, response_class: RESPONSE_CLASS)
        end

        def subscribe(options: nil, **params)
          params = compact_params(params)
          validate_contract!(CONTRACT["text-to-speech"], params)
          subscribe_hybrid(ENDPOINT, body: params, options: options, response_class: RESPONSE_CLASS)
        end
      end
    end
  end
end
