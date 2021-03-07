(ns hangman.lib.game
  (:require [clojure.string :as str]))

(def game-state {:turns-remaining 7
                 :game-state :initializing
                 :target-letters []
                 :already-guessed-letters #{}})

(defn str->list [word]
  (str/split word #""))

(defn new-game
  ;; this will be used to pull random word from dictionary once I figure out how to `require` it
  ;; ([] (new-game (dictionary/random-word)))
  ([word] (assoc game-state :target-letters (str->list word))))

(defn reveal-letter
  "Returns the `letter` if it is in the word and has been guessed.
  Returns `_` for the portions of the word not yet guessed."
  [letter in-word?]
  (case in-word?
    false "_"
    true letter))

(defn reveal-target
  [target-letters guessed-letters]
  (map #(reveal-letter % (contains? guessed-letters %)) target-letters))

(defn tally
  "Returns a map representing the `tally`. In the game, the `tally` is
  the representation of the game that the player is allowed to see."
  [game]
  {:game-state (:game-state game)
   :turns-remaining (:turns-remaining game)
   :revealed-portion-of-target (reveal-target (:target-letters game) (:already-guessed-letters game))})

(defn return-tally
  "Takes a `game` and returns a tuple of the game and the tally"
  [game]
  [game (tally game)])

;; Not implemented
(defn process-move [game guess already-guessed?])

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
