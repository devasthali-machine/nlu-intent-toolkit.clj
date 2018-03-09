(ns nlu-intent.intent
  (:gen-class)
  (:import [com.google.cloud.dialogflow.v2beta1
            SessionName
            SessionsClient
            QueryInput
            TextInput
            DetectIntentRequest
            SessionsSettings]
           [com.google.api.gax.core FixedCredentialsProvider]
           [java.io File ByteArrayInputStream]
           [com.google.auth.oauth2 GoogleCredentials]
           [java.nio.file Files Paths]))

(defn cloud-creds [{creds-file :creds-file}]
  (let [credsFileName       creds-file
        ff                  (new File credsFileName)
        credsFile           (Files/readAllBytes (Paths/get (.toURI ff)))
        stream              (new ByteArrayInputStream credsFile)
        credsStream         (GoogleCredentials/fromStream stream)
        credsProvider       (FixedCredentialsProvider/create credsStream)]
    credsProvider))

(defn get-cloud-creds [{creds-file :creds-file}]
  (.build
    (.setCredentialsProvider (SessionsSettings/newBuilder) (cloud-creds {:creds-file creds-file}))))

(defn intent-request [{nlu-agent :nlu-agent utterance :utterance creds-file :creds-file}]
  (let [id           (.toString (java.util.UUID/randomUUID))
        session-name (. SessionName (of nlu-agent id))
        textIn       (TextInput/newBuilder)
        utt          (.setLanguageCode (.setText textIn utterance) "en-US")
        queryIn      (.setText (QueryInput/newBuilder) utt)
        req          (DetectIntentRequest/newBuilder)
        request      (.build (.setQueryInput (.setSession req (.toString session-name)) queryIn))]
    request))

(defn send-intent-request [{nlu-agent :nlu-agent utterance :utterance creds-file :creds-file}]
  (let [session      (if (nil? creds-file)
                       (SessionsClient/create)
                       (SessionsClient/create (get-cloud-creds {:creds-file creds-file})))
        response     (.detectIntent session (intent-request {:nlu-agent nlu-agent :utterance utterance}))]
    response))
