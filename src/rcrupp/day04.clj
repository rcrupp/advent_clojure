(ns rcrupp.day04
  (:require [clojure.string :as str]))

(defn get-lines [fname]
  (str/split-lines (slurp fname)))

(defn parse [s]
  (let [n (str/split s #",")
        pair1 (str/split (first n) #"-")
        pair2 (str/split (second n) #"-")]
    {:l1 (parse-long (first pair1)) 
     :h1 (parse-long (second pair1))
     :l2 (parse-long (first pair2))
     :h2 (parse-long (second pair2)) }))


(defn contains-subset [m]
  (if (or (and (<= (m :l1) (m :l2)) (>= (m :h1) (m :h2)))
          (and (<= (m :l2) (m :l1)) (>= (m :h2) (m :h1))))
    1
    0))

(defn contains-overlap [m]
  (if (and (<= (m :l1) (m :h2)) (>= (m :h1) (m :l2)))
    1
    0))

(def xf-subset (comp (map parse) (map contains-subset)))

(def xf-overlap (comp (map parse) (map contains-overlap)))


(defn part1 []
  (transduce xf-subset + (get-lines "input4.txt")))


(defn part2 []
  (transduce xf-overlap + (get-lines "input4.txt")))
