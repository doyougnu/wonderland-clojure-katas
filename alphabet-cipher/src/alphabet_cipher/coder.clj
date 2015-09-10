(ns alphabet-cipher.coder)

(defn rotateAlpha [n]
  (let [alphabet "abcdefghijklmnopqrstuvwxyz"
        sub (take n alphabet)
        ret (drop n alphabet)]
    (flatten (conj sub ret))))

<<<<<<< HEAD
(defn enumerateCipher []
  (map (fn [a b] (sorted-map a (rotateAlpha b))) "abcdefghijklmnopqrstuvwxyz" (range 26)))
=======
>>>>>>> de7f3ab33a0e6488938516bd56c532a2b059c47f

(defn encode [keyword message]
  "ssfeme")

(defn decode [keyword message]
  "decodeme")

(defn decypher [cypher message]
  "drme")

