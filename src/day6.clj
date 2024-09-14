(ns day6
  (:require [clojure.string :as str]))

(def input (str/split-lines (slurp "input/day6.txt")))
(def grid (zipmap (for [x (range 1000) y (range 1000)] [x y]) (repeat 0)))
(defn toggle [v] (if (zero? v) 1 0))
(defn off [_] 0)
(defn on [_] 1)
(defn toggle-2 [v] (+ v 2))
(defn off-2 [v] (if (pos? v) (dec v) v))
(defn on-2 [v] (inc v))
(def command-fns {:on     on
                  :off    off
                  :toggle toggle})
(def command-fns-2 {:on     on-2
                  :off    off-2
                  :toggle toggle-2})

(defn- ->coord [coord-str]
  (map #(Integer/parseInt %) (str/split coord-str #",")))

(defn- get-light-segment [[x1 y1] [x2 y2]]
  (vec (for [x (range x1 (inc x2))
             y (range y1 (inc y2))]
         [x y])))

(defn- switch-lights [[cmd s1 s2] grid]
  (let [cmd-fn (cmd command-fns-2)
        coll (get-light-segment s1 s2)]
    (reduce (fn [m k] (update m k cmd-fn)) grid coll)))

(defn- parse-instruction [line]
  (let [command (keyword (re-find #"on|off|toggle" line))
        coords (map ->coord (re-seq #"\d+,\d+" line))]
    (conj coords command)))

(defn run [input grid]
  (->> (map parse-instruction input)
       (reduce (fn [lights instruction]
                 (switch-lights instruction lights))
               grid)))

(comment
  (def result (run input grid))
  (reduce + (vals result))
  )



