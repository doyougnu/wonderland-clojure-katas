(ns alphabet-cipher.coder)

(defn rotateAlpha [n]
  (let [alphabet "abcdefghijklmnopqrstuvwxyz"
        sub (take n alphabet)
        ret (drop n alphabet)]
    (flatten (conj sub ret))))

(defn genTable []
  (map (fn [a b] {a (rotateAlpha b)}) "abcdefghijklmnopqrstuvwxyz" (range 25)))

(defn encode [keyword message]
  (let [kmess (reduce concat (repeat (count keyword) message))]))

(defn decode [keyword message]
  "decodeme")

(defn decypher [cypher message]
  "drme")

