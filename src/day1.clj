(ns day-1
  (:use [clojure.string :refer [trim]]))

(def data (map {\( 1 \) -1} (trim (slurp "input/day1.txt"))))

;;1a
(apply + data)

;;1b
(count(take-while #(>= % 0) (reductions + 0 data)))




