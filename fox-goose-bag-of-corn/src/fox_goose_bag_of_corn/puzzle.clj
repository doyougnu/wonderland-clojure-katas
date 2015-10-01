(ns fox-goose-bag-of-corn.puzzle)

(defn inVector?
  ;;given a vector and a key, function checks if key is in vector, if so returns true, else returns
  ;;nil
  [coll key]
  (some (partial = key) coll))

(defn removeElem 
  ;;given a collection and an elem, function returns a new collection with element removed;;
  [coll elem]
  (let [elemindex (.indexOf coll elem)
        coll1 (subvec coll 0 elemindex)
        coll2 (subvec coll (+ elemindex 1) (count coll))]
    (into [] (flatten (conj coll1 coll2)))))

(defn sendItem 
  ;;given start-pos and item returns vector of similar form as start-pos with item moved to
  ;;other "shore"
  [state item]
  (let [[[shore boat dest]] state
        shore (removeElem shore :you)]
    (if (inVector? shore item)
      [[(removeElem shore item) boat (conj dest item :you)]]
     state)))

(defn takeItem
  ;;given a state and item, function checks for item in state, if exists will return new state
  ;;with item moved to other side of "state"
  [state item]
  (let [[[shore boat dest]] state
        dest (removeElem dest :you)]
    (if (inVector? dest item)
      [[(conj shore item :you) boat (removeElem dest item)]]
      state)))

(defn toDest
  ;;given a state, function will check if :you key is in the shore vector, if it is, function
  ;;returns a new state with :you in the Dest vector
  [state]
  (let [[[shore boat dest]] state]
    (if (inVector? shore :you)
      [[(removeElem shore :you) boat (conj dest :you)]]
      state)))

(defn toShore
  ;;given a state, function checks if :you key is in the dest vector, if it is, function returns
  ;;a new state with :you in the shore vector
  [state]
  (let [[[shore boat dest]] state]
    (if (inVector? dest :you)
      [[(conj shore :you) boat (removeElem dest :you)]]
      state)))

(def start-pos [[[:fox :goose :corn :you] [:boat] []]])
(def move-one (sendItem start-pos :goose))
(def move-two (toShore move-one))
(def move-three (sendItem move-two :corn))
(def move-four (takeItem move-three :goose))
(def move-five (sendItem move-four :fox))
(def move-six (toShore move-five))
(def final-move (sendItem move-six :goose))

(defn river-crossing-plan []
  (map first  [start-pos
               move-one
               move-two
               move-three
               move-four
               move-five
               move-six
               final-move]))
