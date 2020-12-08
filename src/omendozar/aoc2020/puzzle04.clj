(ns omendozar.aoc2020.puzzle04
  (:require
    [clojure.string :as str]))

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

(->> demo-input
     (#(str/split % #"\n\n"))
     (#(str/split % #"\s")))

;; (mapv #(str/replace % #"\n" \space))
;; (re)

(->> (str/split demo-input #"\n\n"))

(comment
  (def data ["ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\nbyr:1937 iyr:2017 cid:147 hgt:183cm"
             "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\nhcl:#cfa07d byr:1929"
             "hcl:#ae17e1 iyr:2013\neyr:2024\necl:brn pid:760753108 byr:1931\nhgt:179cm"
             "hcl:#cfa07d eyr:2025 pid:166559648\niyr:2011 ecl:brn hgt:59in"])
  (apply map (#(str/replace % #"\n" \space) %) data)

  (map #(str/split \space %) data)

  (def result
    (let [[_ k v] (re-seq #"(\S+):(\S+)"
                          "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\nbyr:1937 iyr:2017 cid:147 hgt:183cm")]
      into {} {k v}))
  )
