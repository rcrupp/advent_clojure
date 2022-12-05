(ns rcrupp.day04
  (:require [clojure.string :as str]))


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

(defn contains-overlap [pair]
  (let [n (str/split pair #",")
        p1 (str/split (first n) #"-")
        p2 (str/split (second n) #"-")
        l1 (parse-long (first p1))
        h1 (parse-long (second p1))
        l2 (parse-long (first p2))
        h2 (parse-long (second p2))]
    (if (and (<= l1 h2) (>= h1 l2))
      1
      0)))

(defn part1 []
  (reduce + (map contains-subset (map parse (str/split-lines (slurp "input4.txt"))))))


(defn part2 []
  (reduce + (map contains-overlap (str/split-lines (slurp "input4.txt")))))


(comment

  (take 3 (map #(str/split % #",") (str/split-lines (slurp "input4.txt"))))

  (contains-subset "4-5,5-7")

  (contains-overlap "1-5,3-4")

  (part2)

  )
