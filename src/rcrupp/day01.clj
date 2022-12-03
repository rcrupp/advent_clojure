(ns rcrupp.day01
  (:require [clojure.string :as str]))


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


(defn part1 []
  (apply max
         (sum-up
          (str/split-lines (slurp "input1.txt")))))


(defn part2 []
  (reduce +
          (take 3
                (reverse
                 (sort
                  (sum-up
                   (str/split-lines (slurp "input1.txt"))))))))
