(ns examples.http-server
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [vertx.embed :as embed]
            [vertx.http :as http]
            [ring.adapter.vertx :as ring-vertx]))

(embed/set-vertx! (embed/vertx))

(defroutes app
  (GET "/" [] "<h1>Hello World</h1>")
  (route/not-found "<h1>Page not found</h1>"))

(defn -main [& args]
  (ring-vertx/run-vertx-web (http/server) app "localhost" 8888)
  (.read (System/in)))
