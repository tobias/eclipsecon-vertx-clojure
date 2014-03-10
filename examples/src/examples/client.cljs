(ns examples.client
  (:require [clojure.string :as str]
            [vertx.client.eventbus :as eb]))

(defn $ [id]
  (.getElementById js/document id))

(defn append-div [el content]
  (set! (.-innerHTML el)
    (str (.-innerHTML el) "<div>" content "</div>")))

(defn append-msg [content]
  (append-div ($ "messages") content))

(defn on-enter [el f]
  (set! (.-onkeypress el)
    (fn [evt]
      (if (= 13 (.-charCode evt))
        (f)))))

(defn endpoint []
  (str (.-protocol js/location) "//"
       (.-host js/location)
       "/eventbus"))

(defn handle-login [eb user]
  (eb/on-message eb "chat.users"
    (fn [user]
      (append-msg (str user " joined"))))
  
  (eb/on-message eb "chat.messages"
    (fn [msg]
      (append-msg (str (:user msg) ": " (:content msg)))))
  
  (eb/publish eb "chat.users" user)
  
  (on-enter ($ "message")
    (fn []
      (eb/publish eb "chat.messages"
        {:user user :content (.-value ($ "message"))}))))

(defn init []
  (eb/on-open
    (eb/eventbus (endpoint))
    (fn [eb]
      (handle-login eb (js/prompt "Enter a username:" "")))))

(set! (.-onload js/window) init)
