(ns stepmania-js.example
  (:require [cljs.core.async :refer [chan <! >! put!]]
            [stepmania-js.ajax :refer [get-file]]
            [stepmania-js.sm :refer [str->sm]])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(def filename "/songs/strobo_rmx/strobo_rmx.sm")

(defn -main []
  (go
    (let [sm (str->sm (<! (get-file filename)))])))

(set! *print-fn* #(.log js/console (apply str %&)))
