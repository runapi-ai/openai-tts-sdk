# frozen_string_literal: true

module RunApi
  module OpenaiTts
    # OpenAI TTS speech generation client.
    class Client < RunApi::Core::Client
      attr_reader :text_to_speech

      def initialize(api_key: nil, **options)
        super
        @text_to_speech = Resources::TextToSpeech.new(http)
      end
    end
  end
end
