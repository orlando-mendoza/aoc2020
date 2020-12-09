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

(defn parse-entry [entry]
  (into {} (map #(str/split % #":") (str/split entry #"\s"))))

(defn parse-input [input]
  (->> (str/split input "\n\n")
       (map parse-entry)))

(defn parse-line [line]
  (let [coll (vec (re-seq #"(\S+):(\S+)"
                          line))
        _ (clojure.pprint/pprint coll)]
    (map #(subvec % 1 3) coll)) )

(->> demo-input
     (#(str/split % #"\n\n"))
     (#(map parse-line %)))

(map #(map parse-line %) (str/split demo-input #"\n\n"))


(map parse-line)

(comment
  (parse-input input)
  (parse-input demo-input)

  (def data ["ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\nbyr:1937 iyr:2017 cid:147 hgt:183cm"
             "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\nhcl:#cfa07d byr:1929"
             "hcl:#ae17e1 iyr:2013\neyr:2024\necl:brn pid:760753108 byr:1931\nhgt:179cm"
             "hcl:#cfa07d eyr:2025 pid:166559648\niyr:2011 ecl:brn hgt:59in"])

  ;; this one is working

  (parse-line "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\nbyr:1937 iyr:2017 cid:147 hgt:183cm")

  (map #(subvec % 1 3) result)

  (rest ["ecl:gry" "ecl" "gry"])
  (subvec ["ecl:gry" "ecl" "gry"] 1 3)

  )

