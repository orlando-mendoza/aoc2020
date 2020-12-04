(ns omendozar.aoc2020.puzzle02
  (:require
   [clojure.string :as str]))

(def sample-input
  "1-3 a: abcde
1-3 b: cdefg
2-9 c: ccccccccc")

;; ------------------------------------------------------------
;; Helper Funcions

(defn- parse-line
  [s]
  (let [[_ min max letter pass] (re-find #"(\d+)-(\d+) (.): (.*)" s)]
    [(Long/parseLong min) (Long/parseLong max) (first letter) pass]))

(defn serialize-input
  [input]
  (->> input
       str/split-lines
       (map #(parse-line %))))

;; ------------------------------------------------------------
;; Get input from file

(def input
  (str (slurp (clojure.java.io/reader "resources/puzzle02_input.txt"))))

;; ------------------------------------------------------------
;; PART ONE

(->> input
     serialize-input
     (filter (fn [[min max letter password]]
               (<= min (get (frequencies password) letter 0) max)))
     count)
;; => 434

;; ------------------------------------------------------------
;; PART TWO

(->> input
     serialize-input
     (filter (fn [[pos1 pos2 letter password]]
               (not= (= (nth password (dec pos1)) letter)
                     (= (nth password (dec pos2)) letter))))
     count)
;; => 509

(comment
  ;; Part two
  (nth "achasdfk" (dec 1))
  ;; ------------------------------------------------------------

  (char (first (.getBytes "a")))
  ;; => \a
  (first "a")
  (apply char "a")

  (let [bytes-array (.getBytes "abc")]
    (char (first bytes-array)))

  (re-find #"(\d+)-(\d+) (.): (.*)" "1-3 a: abcde"))
;; => nil;; => nil
