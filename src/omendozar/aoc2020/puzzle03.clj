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


;; ----------------------------------------------------
;; PART ONE

(->> (for [y (range height)
           :let [x (mod (* y 3) width)]]
       (get-in input [y x]))
     (filter #{\#})
     count)
;; => 181

;; ----------------------------------------------------
;; PART TWO

(def slope-paths [[1 1] [3 1] [5 1] [7 1] [1 2]])

(->> (for [[slope-x slope-y] slope-paths]
       (->> (iterate (fn [[x y]]
                       [(mod (+ x slope-x) width) (+ y slope-y)])
                     [0 0])
            (take-while (fn [[x y]]
                          (< y height)))
            (map (fn [[x y]]
                   (get-in input [y x])))
            (filter #{\#})
            count))
     (apply *))
;; => 1260601650



(comment


  
  )
