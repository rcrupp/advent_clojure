(ns rcrupp.advent
  (:require [rcrupp.day01 :as day01]
            [rcrupp.day02 :as day02]
            [rcrupp.day03 :as day03]
            [rcrupp.day04 :as day04]
            [rcrupp.day05 :as day05]
            [rcrupp.day06 :as day06]
            [rcrupp.day07 :as day07]))

(defn run [& args]
  (println "Day  1  part 1: " (day01/part1) " part 2: " (day01/part2))
  (println "Day  2  part 1: " (day02/part1) " part 2: " (day02/part2))
  (println "Day  3  part 1: " (day03/part1) " part 2: " (day03/part2))
  (println "Day  4  part 1: " (day04/part1) " part 2: " (day04/part2))
  (println "Day  5  part 1: " (day05/part1) " part 2: " (day05/part2))
  (println "Day  6  part 1: " (day06/part1) " part 2: " (day06/part2))
  (println "Day  7  part 1: " (day07/part1) " part 2: " (day07/part2)))

