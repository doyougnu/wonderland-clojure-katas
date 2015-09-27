(ns wonderland-number.finder
  (:require [clojure.math.combinatorics :as combo]))

(defn toDigits
  ([n] (toDigits (list) n))
  ([acc n]
   (if (= n 0)
     acc
     (recur (conj acc (mod n 10)) (quot n 10)))))

(defn genMults [n]
  (map (partial * n) [2 3 4 5 6]))

(defn wonderland-number-helper
  "takes a number, generates multiples of 2 3 4 5 6, then checks if the multiples list has the
  same digits as the number, converting to sets with significant performance cost, a brute
  force solution"
  [n]
  (let [mults (map #(into [] (sort %)) (map toDigits (genMults n)))
        n (into [] (sort (toDigits n)))]
    (every? (partial = n) mults)))

(defn wonderland-number []
  (first (filter wonderland-number-helper (range 100000 180000))))
