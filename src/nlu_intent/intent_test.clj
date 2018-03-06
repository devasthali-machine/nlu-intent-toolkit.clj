(ns nlu-intent.intent-test
  (:use nlu-intent.intent :as intent)

  (:import [com.google.cloud.dialogflow.v2beta1
            SessionName
            SessionsClient
            QueryInput
            TextInput
            DetectIntentRequest]))

(let [req  (intent-request "nlu-agent" "where is Porcupine Tree playing right now??")
      resp (send-intent-request req)]
  resp)
