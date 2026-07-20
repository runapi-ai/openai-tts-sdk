# frozen_string_literal: true

require "spec_helper"

RSpec.describe RunApi::OpenaiTts::Client do
  before { allow(ConnectionPool).to receive(:new).and_return(instance_double(ConnectionPool)) }
  after { RunApi.api_key = nil }

  it "exposes text_to_speech" do
    client = described_class.new(api_key: "test-key")
    expect(client.text_to_speech).to be_a(RunApi::OpenaiTts::Resources::TextToSpeech)
  end
end
