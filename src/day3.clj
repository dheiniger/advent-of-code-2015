(ns day3
  (:require [clojure.java.io :as io]))

(def input (clojure.string/trim (slurp "input/day3.txt")))

(def dirs {\< [:x -1] \> [:x 1] \v [:y -1] \^ [:y 1]})

(defn next-house[next-pos current-house]
  (let [[_ xd _ yd] current-house
        [axis distance] next-pos]
    (if (= :x axis)
      [:x (+ xd distance) :y yd]
      [:x xd :y (+ yd distance)])))

(defn find-direction
  ([input [current-house :as houses-visited]]
   (if (empty? input)
     houses-visited
     (recur (rest input)
            (cons (next-house (dirs (first input)) current-house) houses-visited)))))

;;part 1
(prn(count (set (find-direction input [[:x 0 :y 0]]))))

(defn find-direction-2
  ([input [santa-curr-house robosanta-curr-house :as houses-visited]]
   (if (empty? input)
     houses-visited
     (let [[santa-next-dir robo-santa-next-dir] (first (partition 2 input))
           santas-next-house (next-house (dirs santa-next-dir) santa-curr-house)
           rbsantas-next-house (next-house (dirs robo-santa-next-dir) robosanta-curr-house)]
       (recur (drop 2 input)
              (->> (cons rbsantas-next-house houses-visited)
                  (cons santas-next-house)))))))

(count (set (find-direction-2 input [[:x 0 :y 0] [:x 0 :y 0]])))





