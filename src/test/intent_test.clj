(ns nlu-intent.intent-test
  (:use nlu-intent.intent)

  (:import [com.google.cloud.dialogflow.v2beta1
            SessionName
            SessionsClient
            QueryInput
            TextInput
            DetectIntentRequest]))

(send-intent-request
 {:nlu-agent  "nlu-agent"
  :utterance  "where is Porcupine Tree playing right now?"
  :creds-file "src/nlu_intent/creds.json"})