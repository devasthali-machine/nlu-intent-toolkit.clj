(defproject nlu-intent-clj "0.1.0-SNAPSHOT"
  :description "figure out intent"
  :url "http://upd.com/upd"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/core.async "0.3.443"]
                 [com.google.cloud/google-cloud-dialogflow "0.37.0-alpha"]
                 ]
  :user {:repository [["clojars" "http://clojars.org/repo/"]
               ["sonatype" "https://oss.sonatype.org/content/repositories/releases/"]
               ["mvncentral" "http://central.maven.org/maven2/"]]}

  :main ^:skip-aot nlu-intent.intent
  )
