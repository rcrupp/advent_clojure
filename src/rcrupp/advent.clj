(ns rcrupp.advent
  (:require [rcrupp.day01 :as day01]
            [rcrupp.day02 :as day02]
            [rcrupp.day03 :as day03]))

(defn run [& args]
  (println "Day  1  part 1: " (day01/part1) " part 2: " (day01/part2))
  (println "Day  2  part 1: " (day02/part1) " part 2: " (day02/part2))
  (println "Day  3  part 1: " (day03/part1) " part 2: " (day03/part2)))

