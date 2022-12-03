(ns rcrupp.day03
  (:require [clojure.string :as str]
            [clojure.set :as set]))


(defn priority-char [ch]
  (let [i (int ch)]
    (if (< i 91)
      (- i 38)
      (- i 96))))


(defn to-priority [s]
  (let [char-seq (seq (char-array s))
        cnt (count char-seq)
        halves (split-at (/ cnt 2) char-seq)
        intersect (set/intersection (set (first halves)) (set (second halves)))]
    (priority-char (first intersect))))


(defn to-badge-priority [g]
  (let [intersect (set/intersection (set (first g)) (set (second g)) (set (nth g 2)))]
    (priority-char (first intersect))))


(defn part1 []
  (reduce + (map to-priority (str/split-lines (slurp "input3.txt")))))


(defn part2 []
  (reduce + (map to-badge-priority (partition 3 (str/split-lines (slurp "input3.txt"))))))


(comment

  (part1)

  (take 1 (partition 3 (str/split-lines (slurp "input3.txt"))))

  (part2))