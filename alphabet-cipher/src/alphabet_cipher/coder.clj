(ns alphabet-cipher.coder)

(defn rotateAlpha [n]
  (let [alphabet "abcdefghijklmnopqrstuvwxyz"
        sub (take n alphabet)
        ret (drop n alphabet)]
    (flatten (conj sub ret))))

(defn encode [keyword message]
  "ssfeme")

(defn decode [keyword message]
  "decodeme")

(defn decypher [cypher message]
  "drme")

