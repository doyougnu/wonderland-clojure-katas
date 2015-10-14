(ns doublets.solver
  (:require [clojure.java.io :as io]

            [clojure.edn :as edn]
            [clojure.set :as set]))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)))

(defn- possible-word?
  "function takes two words as strings, utlizes map function behavior, to test each character of
  string, if equal convert character to 0, if not 1, then test to see which words are =1"
  [word1 word2]
  (and (= (count word1) (count word2))
       (= 1 (apply + (map
                      #(if (= %1 %2) 0 1)
                      word1 word2)))))

(defn- possible-words 
  "function takes a dictionary, and a list of words (typically a list of one word liek [door]
  fucntion then filters down the dictionary to the last word in the list which should be the
  latest iteration of tree-seq, then conj's the words to create resultant word sequence"
  [dict words]
  (let [word (last words)
        used-word (into #{} words)]
    (->> dict
         (filter (complement used-word))
         (filter (partial possible-word? word))
         (map (fn [a] (conj words a))))))

(defn tree-gen
  "function takes a word then calls tree-seq with helper functions to iterate functions until no
  new possible words are found, returns tree of possible words"
  [dict word]
  (tree-seq (constantly true) (partial possible-words dict) [word]))

(defn doublets
  "function takes a root-word and a target word, calls tree-gen funciton on root-word, then filters
  solution tree to find the target word in the last position, if it can't find the target then
  return and empty vector"
  [root-word target-word]
  (let [results (->> root-word
                     (tree-gen words)
                     (filter (fn [a] (= (last a) target-word)))
                     (first))]
    (if results
      results
      [])))

