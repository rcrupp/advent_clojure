(ns rcrupp.day06)

(defn unique [s len]
  (= len (count (set s))))

(defn test-substr [signal len index]
  (if (unique (subs signal (- index len) index) len)
    index
    -1))

(defn find-unique [unique-length]
  (let [signal (slurp "input6.txt")
        r (range unique-length (count signal))]
    (first (filter pos-int? (map (partial test-substr signal unique-length) r)))))

(defn part1 []
  (find-unique 4))

(defn part2 []
  (find-unique 14))
