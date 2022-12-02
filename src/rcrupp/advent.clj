(ns rcrupp.advent
  (:require [clojure.string :as str])
  (:gen-class))

(defn sum-up [coll]
  (loop [cals coll
         result []]
    (if (empty? cals)
      result
      (let [lst (take-while #(< 0 (count %)) cals)
            rem (drop 1 (drop-while #(< 0 (count %)) cals))
            sum (reduce + (map #(parse-long %) lst))
            new-result (conj result sum)]
        (recur rem new-result)))))

(defn aoc1a []
  (apply max (sum-up (str/split-lines (slurp "input.txt")))))

(defn aoc1b []
  (reduce + (take 3 (reverse (sort (sum-up (str/split-lines (slurp "input.txt"))))))))

(def score_choice {"X" 1 "Y" 2 "Z" 3})

(def score_win {"AX" 3 "AY" 6 "AZ" 0
                "BX" 0 "BY" 3 "BZ" 6
                "CX" 6 "CY" 0 "CZ" 3})

(defn score [x]
  (+ (score_choice (second x)) (score_win (str/join x))))


(defn aoc2a []
  (reduce + (map score (map #(str/split % #" ") (str/split-lines (slurp "input2.txt"))))))

(def score2b_win {"X" 0 "Y" 3 "Z" 6})

(def score2b_choice {"AX" 3 "AY" 1 "AZ" 2 
                     "BX" 1 "BY" 2 "BZ" 3 
                     "CX" 2 "CY" 3 "CZ" 1})

(defn score2b [x]
  (+ (score2b_win (second x)) (score2b_choice (str/join x))))


(defn aoc2b []
  (reduce + (map score2b (map #(str/split % #" ") (str/split-lines (slurp "input2.txt"))))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (aoc1a))

(comment
  
  (map (fn [cal] (parse-long cal)) ["100" "200" "" "400"])
  ;; => (100 200 nil 400)
  
  (sum-up ["100" "200" "" "300" "400" "" "500" "600"])
  
  (score ["B" "Z"])

  (aoc1a)
  (aoc1b)
  (aoc2a)
  (aoc2b)
  )