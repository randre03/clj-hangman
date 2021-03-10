(ns hangman.core
  (:require [hangman.lib.game :as game]))

;; This is the 'public' API for the Hangman Game.

;; Create a new Hangman Game
(defn new-game [] (game/new-game))

;; Get the game-state the player sees
(defn get-tally [game] (game/tally game))

;; Allows player to make a guess
(defn make-move [game guess] (game/make-move game guess))
