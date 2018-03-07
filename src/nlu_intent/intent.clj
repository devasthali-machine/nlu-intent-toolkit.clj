(ns nlu-intent.intent
  (:gen-class)
  (:import [com.google.cloud.dialogflow.v2beta1
            SessionName
            SessionsClient
            QueryInput
            TextInput
            DetectIntentRequest]))

(defn intent-request [{nlu-agent :nlu-agent utterance :utterance}]
  (let [id           (.toString (java.util.UUID/randomUUID))
        session-name (. SessionName (of nlu-agent id))
        textIn       (TextInput/newBuilder)
        utt          (.setLanguageCode (.setText textIn utterance) "en-US")
        queryIn      (.setText (QueryInput/newBuilder) utt)
        req          (DetectIntentRequest/newBuilder)
        request      (.build (.setQueryInput (.setSession req (.toString session-name)) queryIn))]
    request))

(defn send-intent-request [{nlu-agent :nlu-agent utterance :utterance}]
  (let [session  (SessionsClient/create)
        response (.detectIntent session (intent-request {:nlu-agent nlu-agent :utterance utterance}))]
    response))
