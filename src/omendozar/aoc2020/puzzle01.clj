(ns omendozar.aoc2020.puzzle01)

(def input-sample [1721
                   979
                   366
                   299
                   675
                   1456])

(defn sum2020? [input]
  (set (for [x input
             y input
             :when (= 2020 (+ x y))]
         (* x y))))

;; Part 1 with my input
(def input
  (map #(Long/parseLong %)
       (line-seq (clojure.java.io/reader "resources/puzzle01_input.txt"))))

;; ------------------------------------------------------------
;; Part two

(defn sum2020-from-3-numbers
  [input]
  (set (for [x input
             y input
             z input
             :when (= 2020 (+ x y z))]
         (* x y z))))

(comment


  (sum2020? input-sample);; => #{514579}

  (sum2020? input)
;; => #{388075}
  (sum2020-from-3-numbers input)  ;; => #{293450526})
