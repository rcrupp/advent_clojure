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


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (aoc1))

(comment
  
  (map (fn [cal] (parse-long cal)) ["100" "200" "" "400"])
  ;; => (100 200 nil 400)
  
  (sum-up ["100" "200" "" "300" "400" "" "500" "600"])

  (aoc1a)
  (aoc1b)
  )