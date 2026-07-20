# frozen_string_literal: true

module RunApi
  module OpenaiTts
    CONTRACT = {
      "text-to-speech" => {
        "models" => ["tts-1", "tts-1-hd"],
        "fields_by_model" => {
          "tts-1" => {
            "text" => {
              "required" => true,
              "max" => 4096,
              "length" => true
            }
          },
          "tts-1-hd" => {
            "text" => {
              "required" => true,
              "max" => 4096,
              "length" => true
            }
          }
        }
      }
    }.freeze
  end
end
