(ns omendozar.aoc2020.puzzle04
  (:require
    [clojure.string :as str]
    [clojure.java.io :as io]))

(def demo-input "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm

iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929

hcl:#ae17e1 iyr:2013
eyr:2024
ecl:brn pid:760753108 byr:1931
hgt:179cm

hcl:#cfa07d eyr:2025 pid:166559648
iyr:2011 ecl:brn hgt:59in")

(def input (slurp (io/resource "puzzle04_input.txt")))

(defn parse-entry
  [entry]
  (->> (re-seq #"(\S+):(\S+)" entry)
       (into {} (map #(subvec % 1 3)))))

(defn passport?
  [m]
  (= (count (dissoc m "cid")) 7))

;; ------------------------------------------------------------
;; Count demo-input

(->> (str/split demo-input #"\n\n")
     (map parse-entry)
     (filter passport?)
     count)
;; => 2

;; ------------------------------------------------------------
;; Count real input

(->> (str/split input #"\n\n")
     (map parse-entry)
     (filter passport?)
     count)
;; => 204





(comment
)
