# frozen_string_literal: true

require "spec_helper"

RSpec.describe RunApi::OpenaiTts::Resources::TextToSpeech do
  let(:http) { instance_double(RunApi::Core::HttpClient) }
  let(:resource) { described_class.new(http) }
  let(:endpoint) { "/api/v1/openai_tts/text_to_speech" }

  it "POSTs params and decodes managed audio" do
    params = {model: "tts-1", text: "Hello"}
    expect(http).to receive(:request).with(:post, endpoint, body: params)
      .and_return("id" => "task_1", "status" => "completed", "audios" => [{"url" => "https://runapi.ai/audio.mp3", "format" => "mp3", "mime_type" => "audio/mpeg", "size_bytes" => 128}])

    result = resource.run(**params)
    expect(result).to be_a(RunApi::OpenaiTts::Types::TextToSpeechResponse)
    expect(result.audios.first.mime_type).to eq("audio/mpeg")
  end
end
