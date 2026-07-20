# frozen_string_literal: true

require "runapi/core"
require_relative "openai_tts/types"
require_relative "openai_tts/contract_gen"
require_relative "openai_tts/resources/text_to_speech"
require_relative "openai_tts/client"

module RunApi
  module OpenaiTts
    AuthenticationError = RunApi::Core::AuthenticationError
    RateLimitError = RunApi::Core::RateLimitError
    InsufficientCreditsError = RunApi::Core::InsufficientCreditsError
    NotFoundError = RunApi::Core::NotFoundError
    ValidationError = RunApi::Core::ValidationError
  end
end
