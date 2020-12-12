(ns omendozar.aoc2020.puzzle07
  (:require
   [clojure.string :as str]
   [clojure.java.io :as io]))

(def demo-input
  "light red bags contain 1 bright white bag, 2 muted yellow bags.
dark orange bags contain 3 bright white bags, 4 muted yellow bags.
bright white bags contain 1 shiny gold bag.
muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
dark olive bags contain 3 faded blue bags, 4 dotted black bags.
vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.")

;; ------------------------------------------------------------
;; parse-line function
;; input and output examples
:input "light red bags contain 1 bright white bag, 2 muted yellow bags."
:output '{"light red" ([1 "bright white"] [2 "muted yellow"])}
;; ------------------------------------------------------------

(defn parse-line [entry]
  (let [[[_ _ container] & elements]
        (re-seq #"(?:^|(\d+) )(\w+ \w+) bags?" entry)]
    {container (map (fn [[_ n color]]
                      [(Integer/parseInt n) color])
                    elements)}))
;; ------------------------------------------------------------
;; testing parse-line function

(parse-line "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.")
;; => {"vibrant plum" ([5 "faded blue"] [6 "dotted black"])}
;; => {"light red" ([1 "bright white"] [2 "muted yellow"])}
;;------------------------------------------------------------

:shape '{"light red"    ([1 "bright white"] [2 "muted yellow"])
         "vibrant plum" ([5 "faded blue"]   [6 "dotted black"])}

(def database
  (with-open [rdr (io/reader (io/resource "puzzle07_input.txt"))]
   (->> rdr
        line-seq
        (map parse-line)
        (apply merge))))

;; ------------------------------------------------------------
;; PART ONE
;; 1. How many bag colors can eventually contain at least one shiny gold bag?

(defn sub-contains?
  [container own-color]
  (->> container
       database
       (some
        (fn [[n color]]
          (or
           (= color own-color)
           (sub-contains? color own-color))))))

(->> database
     (filter
       (fn [[container elements]]
         (sub-contains? container "shiny gold")))
     count)
;; => 161


;; ------------------------------------------------------------
;; PART TWO
;; How many individual bags are required inside your single shiny gold bag?

(defn sub-count
  [container]
  (->> container
       database
       (reduce
        (fn [temp [n color]]
          (+ temp n (* n (sub-count color))))
        0)))

(sub-count "shiny gold")
;; => 30899

;; ----------------------- comment -----------------------------
(comment
  (->> "light red bags contain 1 bright white bag, 2 muted yellow bags."
       (re-seq #"(\w+ \w=)") )

  (let [[[_ _ container] & elements]
        (re-seq #"(?:^|(\d+) )(\w+ \w+) bags?" "light red bags contain 1 bright white bag, 2 muted yellow bags.")]
    {container (map (fn [[_ n color]]
                      [(Integer/parseInt n) color])
                    elements)})  ;; => {"light red" ([1 "bright white"] [2 "muted yellow"])}

  ;;
  )
;; => nil
