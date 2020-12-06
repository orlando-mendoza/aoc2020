(ns omendozar.aoc2020.puzzle03
  (:require
   [clojure.string :as str]
   [clojure.java.io :as io]))

(def demo-input
  (->>
   "..##.......
#...#...#..
.#....#..#.
..#.#...#.#
.#...##..#.
..#.##.....
.#.#.#....#
.#........#
#.##...#...
#...##....#
.#..#...#.#"
   str/split-lines
   vec))

(def input
  (->> (io/resource "puzzle03_input.txt")
       io/reader
       line-seq
       vec))

(def width (-> input
               first
               count))

(def height (count input))

(->> (for [y (range height)
           :let [x (mod (* y 3) width)]]
       (get-in input [y x]))
     (filter #{\#})
     count)
;; => 181



(comment


  )
