(ns hangman.lib.game-test
  (:require [hangman.lib.game :as game]
            [clojure.test :refer :all]))

(deftest str->list
  (testing "returns a list of letters spelling the word it was given"
    (is (= ["w" "e" "s" "t"] (game/str->list "west")))))

(deftest new-game
  (testing "creates a new map representing the correct game state"
    (is (= {:turns-remaining 7
            :game-state :initialized
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

(deftest tally
  (testing "returns game-state with unguessed letters hidden"
    (is (= {:game-state :bad-guess
            :turns-remaining 3
            :revealed-portion-of-target '("t" "_" "s" "t" "_" "_" "_")}
           (let [game {:turns-remaining 4
                       :game-state :bad-guess
                       :target-letters ["t" "e" "s" "t" "i" "n" "g"]
                       :already-guessed-letters #{"t" "s" "z"}}]
             (game/tally game))))))

(deftest win-or-good-guess
  (testing "changes :game-state if game is won/lost"
    (is (= :good-guess (game/win-or-good-guess false)))
    (is (= :won (game/win-or-good-guess true)))))

;; This will run all tests automatically each timr buffer is reloaded `C-c C-k`
(run-tests)
