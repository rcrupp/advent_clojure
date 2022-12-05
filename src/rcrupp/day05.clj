(ns rcrupp.day05
  (:require [clojure.string :as str]))

(def stacks (vector "ZPMHR"
                    "PCJB"
                    "SNHGLCD"
                    "FTMDQSRL"
                    "FSPQBTZM"
                    "TFSZBG"
                    "NRV"
                    "PGLTDVCM"
                    "WQNJFML"))

(defn stack-peek [direction stacks index n]
  (let [stack (stacks index)
        size (count stack)
        start (- size n)
        boxes (subs stack start size)]
    (if (= direction "reverse")
      (str/reverse boxes)
      boxes)))

(defn stack-pop [stacks index n]
  (let [stack (stacks index) 
        size (count stack)
        end (- size n)
        new-stack (subs stack 0 end)]
    (assoc stacks index new-stack)))

(defn stack-push [stacks index boxes]
  (let [stack (stacks index)
        new-stack (str stack boxes)]
    (assoc stacks index new-stack)))

(defn stack-move [direction from to n stacks]
  (let [from-index (- from 1)
        to-index (- to 1)
        boxes (stack-peek direction stacks from-index n)
        new-stacks (stack-push (stack-pop stacks from-index n) to-index boxes)]
    new-stacks))

(defn process-line [line stacks direction]
  (let [matches (re-matches #"move ([0-9]+) from ([0-9]+) to ([0-9]+)" line)
        n (parse-long (matches 1))
        from (parse-long (matches 2))
        to (parse-long (matches 3))
        new-stacks (stack-move direction from to n stacks)]
    new-stacks))

(defn process-lines [lines stacks direction]
  (if (empty? lines)
    stacks
    (let [line (first lines)
          new-stacks (process-line line stacks direction)]
      (recur (rest lines) new-stacks direction))))

(defn part1 []
  (map last (process-lines (str/split-lines (slurp "input5.txt")) stacks "reverse")))

(defn part2 []
  (map last (process-lines (str/split-lines (slurp "input5.txt")) stacks "forward")))

