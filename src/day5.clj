(ns day5)

(def vowels #{\a \e \i \o \u})
(def bad-strs #{"ab" "cd" "pq" "xy"})
(def input (slurp "input/day5.txt"))

(defn- n-vowels?[n str]
  (>= (count (keep vowels str)) n))

(defn- repeats? [n str]
  (->> (partition n 1 str)
       (map set)
       (remove #(> (count %) 1))
       (not-empty)))

(defn- has-bad-str? [s]
  (not-empty (keep bad-strs (map #(apply str %) (partition 2 1 s)))))

(defn- nice? [s]
  (and (n-vowels? 3 s)
       (repeats? 2 s)
       (not (has-bad-str? s))))

(defn- nice-2? [s]

  )

(comment
  ;pt 1
  (->> input
       clojure.string/split-lines
       (filter nice?)
       count) ;;255
  )