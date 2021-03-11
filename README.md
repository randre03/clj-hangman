# CLJ-Hangman

This is (going to be) a text-based implementation of the classic Hangman game.
Currently, this repo includes the dictionary and the back-end of the game.

Despite, and in contradiction thereof, *Separation of Concerns*, the plan is to 
include the text-based front-end with this code as well.

## Installation

I advise against it.

## Usage

- Install (see instructions above).
- Load the `hangman.core` namespace into your favorite REPL.
- In the REPL, run `(def game (new-game))` - this will start the game with a pseudo-random word.
- In the same REPL, call `(def game (make-move game "x"))`, where `x` is a letter (your guess - which may actually be `x`).
- Keep calling `make-move` as shown above, with your different guesses.
- NOTE: a strict honor rule must be in force at all times. You mustn't look at any of the return values. You *can* call (tally game); however, and this will show you the state of the game you are supposed to see (hiding any letters not yet guessed).

## Attribution
While the letters and parens are all mine, this code is based on pragdave's (aka Coding Gnome) hangman game in his excellent [introduction](https://codestool.coding-gnome.com/courses/elixir-for-programmers) to the [Elixir](https://elixir-lang.org/) programming language. While neither of these entities are associated with this repo, please do check them out.
