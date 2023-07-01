(ns day-2
  (:require [clojure.string :refer [split-lines split]]))


(defn parse-dimensions [dim-str]
  (map #(Integer/parseInt %) (split dim-str #"x")))

(def input (map parse-dimensions (split-lines (slurp "input/day2.txt"))))

(defn box-paper-total [[l w h]]
  (let [side-1 (* l w)
        side-2 (* w h)
        side-3 (* h l)
        smallest-side (min side-1 side-2 side-3)
        box-sqft (reduce + (map (partial * 2) [side-1 side-2 side-3]))]
    (+ box-sqft smallest-side)))

(time (reduce + (map box-paper-total input)))

(defn ribbon [[l w h :as dims]]
  (let [volume (* l w h)
        [side-1 side-2] (sort dims)]
    (+ volume (* 2 side-1) (* 2 side-2))))

(reduce + (map ribbon input))
