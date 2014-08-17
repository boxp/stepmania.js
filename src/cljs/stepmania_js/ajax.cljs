(ns stepmania-js.ajax
  (:require [ajax.core :refer [GET POST 
                               raw-response-format]]
            [cljs.core.async :refer [chan <! >! put!]])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(defn error-handler [{:keys [status status-text]}]
  (print "something bad happened: " status " " status-text))

(defn get-file
  [url]
  (let [c (chan)
        callback (fn [data] 
                   (put! c data))]
    (GET url {:response-format (raw-response-format)
              :handler callback
              :error-handler error-handler})
    (go (<! c))))
