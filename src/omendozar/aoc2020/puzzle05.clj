(ns omendozar.aoc2020.puzzle05
  (:require
   [clojure.string :as str]
   [clojure.java.io :as io]))

;; The seat numbers can be considered as binary numbers
;; where the lower half (F) is 0 and the upper half (B) is 1
;; as for the column R means the upper half (1) and L means the lower half (0)
;; so we can map our number with binary numbers
;; F B F B B F F R L R
;; 0 1 0 1 1 0 0 1 0 1 --> 357

(Integer/parseInt "0101100101" 2)   ;; => 357

(defn seat-id [seat]
  (-> seat
      (str/replace #"F|L" "0")
      (str/replace #"B|R" "1")
      (Integer/parseInt 2)))
;; => 35760

;; ------------------------------------------------------------
;; PART ONE

(with-open [rdr (io/reader "resources/puzzle05_input.txt") ]
  (->> rdr
       line-seq
       (map seat-id)
       ; vec
       ;; part 1 - max id
       (apply max)

       ;; part 2 - own id
       ))

;; ------------------------------------------------------------
;; PART TWO
(with-open [rdr (io/reader "resources/puzzle05_input.txt") ]
  (->> rdr
       line-seq
       (map seat-id)
       ;; part 2 - own id
       sort
       (partition 2 1)
       (filter (fn [[x y]] (= (inc x) (dec y))))
       first ; sole pair
       first
       inc
       ))
;; => 646
