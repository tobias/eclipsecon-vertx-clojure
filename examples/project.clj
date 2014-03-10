(defproject examples "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.6.0-beta2"]
                 [org.clojure/clojurescript "0.0-2156"]
                 [io.vertx/clojure-api "1.0.0-SNAPSHOT"]
                 [compojure "1.1.6"]
                 [ring-vertx-adapter "0.1.0-SNAPSHOT"]]
  :java-source-paths ["java-src"]
  :plugins [[lein-vertx "0.3.0-SNAPSHOT"]
            [lein-cljsbuild "1.0.2"]]
  :vertx {:main examples.core/init}
  :cljsbuild {:builds [{:source-paths ["src"]
                        :compiler {:output-to "resources/client.js"}}]}
  :main examples.http-server)
