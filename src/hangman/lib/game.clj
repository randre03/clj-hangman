(ns hangman.lib.game
  (:require [clojure.string :as str]))

(def game-state {:turns-remaining 7
                 :game-state :initializing
                 :target-letters []
                 :already-guessed-letters #{}})

(defn str->list
  "Given `word` (string), returns a list of the letters that make up `word`."
  [word]
  (str/split word #""))

(defn new-game
  "Returns a new game map with the given `word` or selects a random word if one is not provided."
  #_([] (new-game (dictionary/random-word)))
  ([word] (assoc game-state :target-letters (str->list word))))

(defn reveal-letter
  "Returns the `letter` if it is in the word and has been guessed.
  Returns `_` for the portions of the word not yet guessed."
  [letter in-word?]
  (case in-word?
    false "_"
    true letter))

(defn reveal-target
  "Returns a seq representing the word with blanks (`_`) in place of letters not yet guessed."
  [target-letters guessed-letters]
  (map #(reveal-letter % (contains? guessed-letters %)) target-letters))

(defn tally
  "Returns the `tally` map. Tally is the representation of the game that the player is allowed to see."
  [game]
  {:game-state (:game-state game)
   :turns-remaining (:turns-remaining game)
   :revealed-portion-of-target (reveal-target (:target-letters game) (:already-guessed-letters game))})

(defn return-tally
  "Takes a `game` and returns a tuple of the game and the tally"
  [game]
  [game (tally game)])

(defn did-guess-win-game
  [won-game?]
  (case won-game?
    false :good-guess
    true :won))

;; Not Implemented
(defn evaluate-guess [game good-guess?]
  ;; this might be better as an if-let or when-let
  (case good-guess?
    false (if (= 1 (:turns-remaining game))
            ;; if you make a bad guess (false) with 1 turn remaining, then you just lost
            (assoc game :turns-remaining 0 :game-state :lost)
            ;; otherwise, you just lose a turn
            (-> game
                (assoc :game-state :bad-guess)
                (update :turns-remaining dec)))
    true nil))

(defn process-move
  "If `guess` is `already-guessed` then player loses turn `(dec turns)`. Otherwise, `evaluate-guess`."
  [game guess already-guessed?]
  (case already-guessed?
    ;; I don't know if this code works. Can I replace get-in -> conj -> assoc-in with update-in???
    false (let [current-set (get-in game [:already-guessed-letters])
                updated-set (conj current-set guess)
                game (assoc-in game [:already-guessed-letters] updated-set)]
            evaluate-guess game guess (contains? (:target-letters game) guess))
    true (update game :turns-remaining dec)))

(defn make-move
  "Takes the current state of the game `game` and a `guess` and returns the `tally"
  [game guess]
  (let [state (:game-state game)
        already-guessed (:already-guessed-letters game)]
    (case state
      :won (return-tally game)
      :lost (return-tally game)
      (process-move game guess (contains? already-guessed guess)))))

#_(new-game "sensibility")
