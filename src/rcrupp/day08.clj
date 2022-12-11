(ns rcrupp.day08
  (:require [clojure.string :as str]))

(defn subcol [trees y start end]
  (let [max (count trees)
        col (mapv #(get-in trees [% y]) (range max))]
    (subvec col start end)))


(defn search-helper [v h i]
  (if (empty? v)
    i
    (if (<= h (first v))
      (inc i)
      (recur (rest v) h (inc i)))))


(defn search [v height]
  (search-helper v height 0))


(defn view-left [height trees x y]
  (if (= y 0)
    0
    (let [v (reverse (subvec (nth trees x) 0 y))]
      (search v height))))


(defn view-right [height trees x y]
  (if (= y (dec (count trees)))
    0
    (let [max (count trees)
          v (subvec (nth trees x) (inc y) max)]
      (search v height))))


(defn view-top [height trees x y]
  (if (= x 0)
    0
    (let [v (reverse (subcol trees y 0 x))]
      (search v height))))


(defn view-bottom [height trees x y]
  (if (= x (dec (count trees)))
    0
    (let [max (count trees)
          v (subcol trees y (inc x) max)]
      (search v height))))


(defn check-left [height trees x y]
  (if (= y 0)
    1
    (let [left-trees (subvec (nth trees x) 0 y)
          taller (filter #(>= % height) left-trees)]
      (if (< 0 (count taller))
        0
        1))))


(defn check-right [height trees x y]
  (let [max (count trees)]
    (if (= y (dec max))
      1
      (let [right-trees (subvec (nth trees x) (inc y) max)
            taller (filter #(>= % height) right-trees)]
        (if (< 0 (count taller))
          0
          1)))))


(defn check-top [height trees x y]
  (if (= x 0)
    1
    (let [top-trees (subcol trees y 0 x)
          taller (filter #(>= % height) top-trees)]
      (if (< 0 (count taller))
        0
        1))))


(defn check-bottom [height trees x y]
  (let [max (count trees)]
    (if (= x (dec max))
      1
      (let [bottom-trees (subcol trees y (inc x) max)
            taller (filter #(>= % height) bottom-trees)]
        (if (< 0 (count taller))
          0
          1)))))


(defn check [trees x y]
  (let [height (get-in trees [x y])]
    (if (= 1 (check-left height trees x y))
      1
      (if (= 1 (check-right height trees x y))
        1
        (if (= 1 (check-top height trees x y))
          1
          (check-bottom height trees x y))))))


(defn view-score [trees x y]
  (let [height (get-in trees [x y])
        score-left (view-left height trees x y)
        score-right (view-right height trees x y)
        score-top (view-top height trees x y)
        score-bottom (view-bottom height trees x y)]
    (* score-left score-right score-top score-bottom)))


(defn check-row [trees x]
  (reduce + (mapv #(check trees x %) (range (count trees)))))


(defn check-rows [trees]
  (reduce + (mapv #(check-row trees %) (range (count trees)))))


(defn score-row [trees x]
  (apply max (mapv #(view-score trees x %) (range (count trees)))))


(defn score-rows [trees]
  (apply max (mapv #(score-row trees %) (range (count trees)))))


(defn load-row [row trees]
  (if (= 0 (count row))
    trees
    (let [tree (parse-long (str (first row)))]
      (recur (rest row) (conj trees tree)))))


(defn load-rows [rows trees]
  (if (= 0 (count rows))
    trees
    (let [row (first rows)
          new-trees (load-row row [])]
      (recur (rest rows) (conj trees new-trees)))))


(defn part1 []
  (let [lines (str/split-lines (slurp "input8.txt"))
        trees (load-rows lines [])]
    (check-rows trees)))


(defn part2 []
  (let [lines (str/split-lines (slurp "input8.txt"))
        trees (load-rows lines [])]
    (score-rows trees)))

