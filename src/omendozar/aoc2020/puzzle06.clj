(ns omendozar.aoc2020.puzzle06
  (:require
   [clojure.string :as str]
   [clojure.java.io :as io]))

(def demo-input
  "abc

a
b
c

ab
ac

a
a
a
a

b")

(defn split
  [regex s]
  "swaps clojure.string/split parameters for idiomatic ->> threading"
  (str/split s regex))


(defn count-y-in-group
  [group]
  (-> group
      set
      (disj \newline)
      count))

;; testing count-y-in-group
(count-y-in-group "a\nbc\nad") ;; => 4

;; Testing demo-input
(->> demo-input
    (split #"\n\n")         ;; => ["abc" "a\nb\nc" "ab\nac" "a\na\na\na" "b"]
    (map count-y-in-group)  ;; => (3 3 3 1 1)
    (reduce +))             ;; => 11

;; ------------------------------------------------------------
;; PART ONE
;; Real input

(with-open [rdr (io/reader (io/resource "puzzle06_input.txt"))]
  (->> rdr
       slurp
       (split #"\n\n")
       (map count-y-in-group)
       (reduce +)
       ))
;; => 6778

;; ------------------------------------------------------------
;; PART TWO

(defn count-all-yes
  [group]
  (->> group
       (split #"\n")
       (map set)
       (apply clojure.set/intersection)
       count))

;; Testing demo-input
(->> demo-input
     (split #"\n\n")
     (map count-all-yes)        ;; => (3 0 1 1 1)
     (reduce +)
     )                          ;; => 6

(with-open [rdr (io/reader (io/resource "puzzle06_input.txt"))]
  (->> rdr
       slurp
       (split #"\n\n")
       (map count-all-yes)
       (reduce +)))
;; => 3406
