(ns examples.core
  (:require [vertx.repl :as repl]))

(defn init []
  (repl/start))
