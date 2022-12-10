(ns rcrupp.day07
  (:require [clojure.string :as str]))


(defn ls-command [dirs dir-path]
  (list dirs dir-path))  ;; no-op


(defn goto-root [dirs]
  (list dirs "/"))


(defn parent-dir [dir]
  (if (= dir "/")
    nil
    (if (= 0 (str/last-index-of dir "/"))
      "/"
      (subs dir 0 (str/last-index-of dir "/")))))


(defn goto-parent [dirs dir-path]
  (let [new-dir-path (parent-dir dir-path)]
    (if (= dir-path "/")
      (list dirs "")
      (list dirs new-dir-path))))


(defn goto-dir [dirs dir-path new-dir]
  (let [new-dir-path (str dir-path "/" new-dir)]
    (list dirs new-dir-path)))


(defn cd-command [dirs dir-path new-dir]
  (case new-dir
    "/" (goto-root dirs)
    ".." (goto-parent dirs dir-path)
    (goto-dir dirs dir-path new-dir)))


(defn dir-command [dirs dir-path]
    (list dirs dir-path))  ;; no-op


(defn add-size-to-path [dirs dir-path size]
  (if (nil? dir-path)
    dirs
    (let [path-hash (hash dir-path)
          new-dirs (conj dirs {:hash path-hash :size size})
          new-dir-path (parent-dir dir-path)]
      (recur new-dirs new-dir-path size))))


(defn file-command [dirs dir-path size]
  (let [new-dirs (add-size-to-path dirs dir-path size)]
    (list new-dirs dir-path)))


(defn process-command [cmd dirs dir-path]
  (case (:command cmd)
    :ls (ls-command dirs dir-path)
    :cd (cd-command dirs dir-path (:name cmd))
    :add-dir (dir-command dirs dir-path)
    :add-file (file-command dirs dir-path (:size cmd))
    (list dirs dir-path)))


(defn line-to-command [line]
  (let [words (str/split line #" ")]
    (cond
      (= "$" (first words))
      (cond
        (= "cd" (second words)) {:command :cd :name (nth words 2)}
        (= "ls" (second words)) {:command :ls})
      (number? (parse-long (first words))) {:command :add-file :name (second words) :size (parse-long (first words))}
      (= "dir" (first words)) {:command :add-dir :name (second words)})))


(defn commands-to-directories [cmds dirs dir-path]
  (if (empty? cmds)
    dirs
    (let [cmd (first cmds)
          command-update (process-command cmd dirs dir-path)
          new-dirs (first command-update)
          new-dir-path (second command-update)]
      (recur (rest cmds) new-dirs new-dir-path))))


(defn directories-to-sizes [dirs]
   (map second (apply merge-with + (for [x dirs] {(:hash x) (:size x)}))))


(defn part1 []
  (let [cmds (map line-to-command (str/split-lines (slurp "input7.txt")))
        dirs (commands-to-directories cmds [] "/")
        sizes (directories-to-sizes dirs)
        filtered (filter #(<= % 100000) sizes)]
    (reduce + 0 filtered)))


(defn part2 []
  (let [cmds (map line-to-command (str/split-lines (slurp "input7.txt")))
        dirs (commands-to-directories cmds [] "/")
        sizes (directories-to-sizes dirs)
        max  (apply max sizes)
        needed (- 30000000 (- 70000000 max))]
    (apply min (filter #(>= % needed) sizes))))

