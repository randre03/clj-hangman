(ns hangman.lib.game-test
  (:require [hangman.lib.game :as game]
            [clojure.test :refer :all]))

(deftest str->list
  (testing "returns a list of letters spelling the word it was given"
    (is (= ["w" "e" "s" "t"] (game/str->list "west")))))

(deftest new-game
  (testing "creates a new map representing the correct game state"
    (is (= {:turns-remaining 7
            :game-state :initializing
            :target-letters ["t" "e" "s" "t" "i" "n" "g"]
            :already-guessed-letters #{}}
           (game/new-game "testing")))))

(deftest reveal-letter
  (testing "shows letter if already guessed, otherwise hidden as `_`"
    (is (= (str "r") (game/reveal-letter "r" true)))
    (is (= (str "_") (game/reveal-letter "r" false)))))

(deftest reveal-target
  (testing "shows only the letters of word that have been guessed"
    (is (= '("_" "e" "s" "_") (game/reveal-target ["w" "e" "s" "t"] #{"e" "s"})))
    (is (= '("_" "_" "_" "_") (game/reveal-target ["w" "e" "s" "t"] #{})))))
