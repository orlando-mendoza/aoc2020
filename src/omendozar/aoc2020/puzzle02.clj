(ns omendozar.aoc2020.puzzle02
  (:require
   [clojure.string :as str]))

(def sample-input
  "1-3 a: abcde
1-3 b: cdefg
2-9 c: ccccccccc")

(defn- parse-line
  [s]
  (let [[_ min max letter pass] (re-find #"(\d+)-(\d+) (.): (.*)" s)]
    [(Long/parseLong min) (Long/parseLong max) letter pass]))

(defn serialize-input
  [input]
  (->> input
       str/split-lines
       (map #(parse-line %))))

(defn valid-password?
  [s]
  (let [_ (println s)
        freq (frequencies (get s 3))
        letter (char (first (.getBytes (get s 2))))
        min (get s 0)
        max (get s 1)
        times (get freq letter 0)]
    (if (and (>= times min) (<= times max))
      true
      false)))

(defn list-of-passwords
  [vector-of-vectors]
  (let [counter 0]
    (for [v vector-of-vectors
          :when (valid-password? v)]
      (inc counter))))

(->> sample-input
    serialize-input
    (map #(apply valid-password? %)))

(def input
  (line-seq (clojure.java.io/reader "resources/puzzle02_input.txt")))

(comment
 (def s [1 3 "a" "abcde"])
 (valid-password? s )
 (valid-password? [1 3 "a" "abcdesaaa"] )
 (def s (serialize-input sample-input))
 (list-of-passwords s)

 (for [v '([1 3 "a" "abcde"] [1 3 "b" "cdefg"] [2 9 "c" "ccccccccc"])
       :when (valid-password? v)]
   (into [] v))
;; => (2) ;; => ([2 9 "c" "ccccccccc"])

 (for [x '([:a 1] [:b 2] [:c 0]) :when (= (get x 1) 0) ]  x)
 ;; => ([:c 0])

  (def pm (map valid-password? '([1 3 "a" "abcde"] [1 3 "b" "cdefg"] [2 9 "c" "ccccccccc"])))
  (filter even? [1 2 3 4])

  (first '([1 3 "a" "abcde"] [1 3 "b" "cdefg"] [2 9 "c" "ccccccccc"]))
  ;; => [1 3 "a" "abcde"]

  (first '([1 3 "a" "abcde"]))
  ;; => [1 3 "a" "abcde"]

  (parse-line s)  ;; => [1 3 "a" "abcde"]

  (char (first (.getBytes "a")))

  (let [bytes-array (.getBytes "abc")]
    (char (first bytes-array)))

  (re-find #"(\d+)-(\d+) (.): (.*)" "1-3 a: abcde"))
