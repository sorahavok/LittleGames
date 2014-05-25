package sora.havok.games.noob.highLow

import scala.util.Random

object HighLow {

  def main(args: Array[String]) {
    val game = new HighLowGame(1, 1)

    var correctAnswer = false
    while (!correctAnswer) {
      print("Guess a number: ")
      game.check(readInt) match {
        case 1 => println("Too Low!")
        case -1 => println("Too High!")
        case 0 => {
          println("You Win!")
          correctAnswer = true
        }
      }
    }
    println(s"It took you ${game.guesses} guesses out of an expected ${game.expectedGuesses}.\nThanks for Playing ^_^")
  }
}

class HighLowGame(low: Int, high: Int) {
  require(low < high, "Low must be smaller than High!")
  private var numberOfGuesses = 0
  private val guessMe = Random.nextInt(high - low) + low
  def check(guess: Int) = {
    numberOfGuesses += 1
    guessMe.compare(guess)
  }
  def guesses = this.numberOfGuesses
  def expectedGuesses: Long = Math.round(Math.log(high - low) / Math.log(2))
}