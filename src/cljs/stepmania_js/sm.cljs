(ns stepmania-js.sm
  (:require [clojure.string :refer [split]]))

(defrecord Notes [chart-type author difficulty
                  numerical-meter groove-radar data])

(defn split-colon
  [string]
  (split string #"\,"))

(defn str->bpms
  [string]
  (let [bpms-sq (re-seq #"([0-9\.]*)\=([0-9\.]*)" string)]
    (map #(->> % rest (map js/parseFloat)) bpms-sq)))

(defn str->data
  [string]
  (let [note-measured (split-colon string)
        note-vec (map #(re-seq #"[0-9]{4}" %) note-measured)]
    note-vec))

(defn str->notes
  [string]
  (let [notes-sq (split string #"\:")
        str->groove-radar (fn [string] (->> (split-colon string)
                                            (map js/parseFloat)))]
    (-> (apply ->Notes notes-sq)
        (update-in [:data] str->data)
        (update-in [:groove-radar] str->groove-radar))))

(defn str->sm
  [string]
  (let [sm-sq (re-seq #"\#([A-Z]*?)\:([\s\S]*?)\;" string)
        sm-keys (map #(-> % (nth 1) keyword) sm-sq)
        sm-vals (map #(-> % (nth 2)) sm-sq)
        sm (zipmap sm-keys sm-vals)]
    (-> sm
      (update-in [:STOPS] str->bpms)
      (update-in [:BPMS] str->bpms)
      (update-in [:NOTES] str->notes))))
