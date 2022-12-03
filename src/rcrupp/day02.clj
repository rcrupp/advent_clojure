(ns rcrupp.day02
  (:require [clojure.string :as str]))

(def score_choice_part1 {"X" 1 "Y" 2 "Z" 3})

(def score_win_part1    {"AX" 3 "AY" 6 "AZ" 0
                         "BX" 0 "BY" 3 "BZ" 6
                         "CX" 6 "CY" 0 "CZ" 3})

(def score_win_part2    {"X" 0 "Y" 3 "Z" 6})

(def score_choice_part2 {"AX" 3 "AY" 1 "AZ" 2
                         "BX" 1 "BY" 2 "BZ" 3
                         "CX" 2 "CY" 3 "CZ" 1})


(defn score_part1 [input]
  (+ (score_choice_part1 (second input))
     (score_win_part1 (str/join input))))


(defn score_part2 [input]
  (+ (score_win_part2 (second input))
     (score_choice_part2 (str/join input))))


(defn part1 []
  (reduce +
          (map score_part1
               (map #(str/split % #" ")
                    (str/split-lines (slurp "input2.txt"))))))


(defn part2 []
  (reduce +
          (map score_part2
               (map #(str/split % #" ")
                    (str/split-lines (slurp "input2.txt"))))))
