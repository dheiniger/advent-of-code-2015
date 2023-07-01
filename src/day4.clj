(ns day4
  (:require [clojure.string :as string])
  (:import java.util.HexFormat
           java.security.MessageDigest))

(def hash-key "yzbqklnj")

(defn create-hash [x]
  (let [md (MessageDigest/getInstance "MD5")
        hf (HexFormat/of)]
    (.update md (.getBytes (str hash-key x)))
    (let [bytes (.digest md)]
      (.formatHex hf bytes))))

(defn count-leading-zeros [x]
  (count (take-while #(= % \0) x)))

(defn problem [n]
  (letfn [(not-n-leading-zeroes[x] (not= n (count-leading-zeros (create-hash x))))]
    (-> (take-while #(not-n-leading-zeroes %) (range))
        last
        inc)))

(prn (problem 5))
(prn(problem 6))

