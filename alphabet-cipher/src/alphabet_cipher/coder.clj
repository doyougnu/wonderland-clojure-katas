(ns alphabet-cipher.coder)

(defn rotateAlpha [n]
  (let [alphabet "abcdefghijklmnopqrstuvwxyz"
        sub (take n alphabet)
        ret (drop n alphabet)]
    (flatten (conj sub ret))))

(defn enumerateCipher []
  (clojure.walk/keywordize-keys (into {} (map (fn [a b] (sorted-map (str a) (rotateAlpha b))) "abcdefghijklmnopqrstuvwxyz" (range 26)))))

(defn alphaMap []
  (clojure.walk/keywordize-keys (into {} (map (fn [a b] (sorted-map (str a) b)) "abcdefghijklmnopqrstuvwxyz" (range)))))

(defn encodeHelper [keyword message]
  (let [keyword (map identity keyword)
        message (map identity message)
        msgcnt (count message)
        keycnt (count keyword)]
    (cond
      (> keycnt msgcnt) (take msgcnt (concat keyword))
      (<= keycnt msgcnt) (take msgcnt (reduce concat (repeat msgcnt keyword))))))

(defn encode [keyword message]
  (let [nk (encodeHelper keyword message)
        ]))

(defn decode [keyword message]
    "decodeme")

(defn decypher [cypher message]
  "drme")

