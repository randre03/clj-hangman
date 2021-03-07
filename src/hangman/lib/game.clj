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

(defn return-tally [game])

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
