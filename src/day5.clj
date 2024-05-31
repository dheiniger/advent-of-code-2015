(ns day5)

(def vowels #{\a \e \i \o \u})
(def bad-strs #{"ab" "cd" "pq" "xy"})
(def input (slurp "input/day5.txt"))

(defn- n-vowels? [n str]
  (>= (count (keep vowels str)) n))

(defn- repeats? [n s]
  (->> s
       (partition-by identity)
       (remove #(< (count %) n))
       (not-empty)))

(defn- has-bad-str? [s]
  (not-empty (keep bad-strs (map #(apply str %) (partition 2 1 s)))))

(defn- nice? [s]
  (and (n-vowels? 3 s)
       (repeats? 2 s)
       (not (has-bad-str? s))))

;; part 2
(defn- two-pairs? [s]
  (->> (partition-by identity s)
       (remove #(= 3 (count %)))
       flatten
       (apply str)
       (partition 2 1)
       frequencies
       vals
       (some #(> % 1))))

(defn- sandwiched? [s]
  (let [triplets (partition 3 1 s)
        sandwiches (reduce (fn [acc [x _ z :as triplet]]
                             (if (= x z)
                               (conj acc triplet)
                               acc))
                           [] triplets)]
    (not-empty sandwiches)))

(defn- nice-2? [s]
  (and (two-pairs? s)
       (sandwiched? s)))

(comment
  (add-tap clojure.pprint/pprint)
  ;pt 1
  (->> input
       clojure.string/split-lines
       (filter nice?)
       count) ;;255

  (->> input
       clojure.string/split-lines
       (filter nice-2?)
       count)
  )