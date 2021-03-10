(ns hangman.lib.dictionary
  (:require [clojure.string :as str]))

(def words (str/split-lines  (slurp "resources/words.txt")))

(defn random-word [] (rand-nth words))
