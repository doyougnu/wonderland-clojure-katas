(ns fox-goose-bag-of-corn.puzzle)

(def start-pos [[[:fox :goose :corn :you] [:boat] []]])

(defn inVector? [coll key]
  (some (partial = key) coll))

(defn removeElem [coll elem]
  (let [elemindex (.indexOf coll elem)
        coll1 (subvec coll 0 elemindex)
        coll2 (subvec coll (+ elemindex 1) (count coll))]
    (into [] (flatten (conj coll1 coll2)))))

(defn sendItem [state item]
  ;;given start-pos and item returns vector of similar form as start-pos with item moved to
  ;;other "shore"
  (let [[[shore boat dest]] state]
    (if (inVector? shore item)
      [[(removeElem shore item) boat (conj dest item)]]
     state)))

(defn takeItem [state item]
  (let [[[shore boat dest]] state]
    (if (inVector? dest item)
      [[(conj shore item) boat (removeElem dest item)]]
      state)))

(defn river-crossing-plan []
  start-pos)
