(ns examples.examples)

(require '[vertx.eventbus :as eb])

;; using a fn by name as a handler
(eb/on-message "example.fn" println)

;; publish a string
(eb/publish "example.fn" "hi")

;; how about some structured data?
(eb/publish "example.fn" {:hi "there"})

;; a lambda as a handler 
(eb/on-message "example.fn"
  (fn [msg]
    (println "lambda fn:"
      msg (class msg))))

;; a java object as a handler
(import 'tcrawley.examples.JavaHandler)

(eb/on-message "example.java" (JavaHandler.))

(eb/publish "example.java" {:hi "there"})

(require '[vertx.filesystem.sync :as fs])

;; anything that can be coerced to a Buffer is handled automatically
(fs/write-file "/tmp/foo.txt" "bar")

;; require vertx primary namespace
(require '[vertx.core :as vertx])

;; deploy a module
(vertx/deploy-module "io.vertx~mod-web-server~2.0.0-final"
  :config {:web_root           "resources"
           :port               8080
           :bridge             true
           :inbound_permitted  [{:address "chat.users"}
                                {:address "chat.messages"}]
           :outbound_permitted [{:address "chat.users"}
                                {:address "chat.messages"}]}
  :handler println)

;; show app

;; jump to client.cljs

;; run ring apps on Vert.x
(require
  '[compojure.core :as compojure]
  '[compojure.route :as route]
  '[vertx.http :as http]
  '[ring.adapter.vertx :as ring-vertx])

(compojure/defroutes app
  (compojure/GET "/" [] "<h1>Hello World</h1>")
  (route/not-found "<h1>Page not found</h1>"))

(ring-vertx/run-vertx-web (http/server) app "localhost" 8888)

;; jump to http_server.clj to show embedding


