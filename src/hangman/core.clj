(ns hangman.core
  (:require [hangman.lib.game :as game]))

;;;; This is going to be the 'public' API for the Hangman Game
;;;; Therefore, it just needs to expose these three functions


(defn new-game
  "Starts a new game."
  []
  (game/new-game))

(defn get-tally
  "Returns the tally - our external game-state (what the player sees)."
  [game]
  (game/tally game))

(defn make-move
  "Allows the player to suggest a letter, returning the updated state."
  [])
