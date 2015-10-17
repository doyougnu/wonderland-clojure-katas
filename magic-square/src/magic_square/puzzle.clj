(ns magic-square.puzzle
  (require [clojure.math.combinatorics :as combo]))

(def values [1.0 1.5 2.0 2.5 3.0 3.5 4.0 4.5 5.0])

(def possible-boards (combo/permutations values))

(defn- nestVectors
  ;;given a integer and collection creates a vector of vectors with n elems per vector
  ;;truncates values that do not divide evenly
  [n coll]
  (vec (map vec (partition n coll))))


(defn- getCols [fn table]
  (mapv fn table))

(defn- getRows [fn table]
  (fn table))

(defn- columns
  ;;function takes a 3x3 table and returns a vector of nested vectors where each vector is a column
  ;;of the original table
  [table]
  (mapv #(getCols % table) [first second last]))

(defn- rows
  ;;function takes a 3x3 table and returns a vecotr of nested vectors where each vec is a row of
  ;;the original
  [table]
  (mapv #(getRows % table) [first second last]))

(defn- diags
  ;;funcation takes a 3x3 table and returns a vector of nested vectors where each vec is a
  ;;diagonal of the original table
  [table]
  (let [funcs [first second last]]
    [(mapv #(%1 %2) funcs table) (mapv #(%1 %2) (reverse funcs) table)]))

(defn- sum
  ;;given a vector of vectors, function returns a vector of sums for each nested vector
  [table]
  (map (partial reduce +) table))

(defn- totals
  ;;given a function to reduce by, a list of function to select nested vectors by, and a vector of
  ;;vectors function maps the reduce function to each nested vector return by the selection funcs
  ;;Ex. given (totals sum [columns rows diags] [[1.0 1.5 2.0] [2.5 3.0 3.5] [4.0 4.5 5.0]])
  ;;funciton returns  [[7.5 9.0 10.5] [4.5 9.0 13.5] [9.0 9.0]]
  [reduce-func selection-funcs table]
  (mapv #(into [] (reduce-func (% table))) selection-funcs))


(defn- possibleSolution?
  ;;given a table defined as a vecotr of vectors to form a 3x3 matrix, function calculates the sum
  ;;total of the columns, rows and diagonals, then tests if the resultant list of totals is the
  ;;same number, returns bool
  [table]
  (let [totals (totals sum [columns rows diags] table)]
    (apply = (flatten totals))))

(def solution-boards
  (->> possible-boards
       (map (partial nestVectors 3))
       (filter possibleSolution?)))

(defn magic-square [values]
  (first solution-boards))

(def tables (magic-square values))
