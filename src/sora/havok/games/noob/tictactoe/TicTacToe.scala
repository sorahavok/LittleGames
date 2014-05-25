package sora.havok.games.noob.tictactoe

import sora.havok.games.util.Location
import scala.collection.immutable.Range

object TicTacToe {

  def main(args: Array[String]) {

    val tic = new TicTacToeGame
    tic.printBoard
    run(tic, 0) match {
      case player: Player => println(s"Game Over, $player Wins!")
      case _ => println(s"Game Over, Cats Game.")
    }
  }

  def run(tic: TicTacToeGame, turn: Int): Player = {
    def player = Player.values()(turn % Player.values().length)
    while (tic.movesLeft) {
      println(s"$player's turn to move")
      try {
        tic.makeMove(readInt, readInt, player)
        tic.printBoard
        if (tic.playerWon(player)) {
          return player
        }
        return run(tic, turn + 1)
      } catch {
        case x: IllegalArgumentException =>
          println("Illegal Move")
          return run(tic, turn)
      }
    }
    return null
  }

}

class TicTacToeGame(board: Array[Array[Player]]) {
  def this(size: Int = 3) { this(Array.ofDim[Player](size, size)) }

  val size = board.length
  val range = Range(0, size)

  def movesLeft = board.flatten.exists(_ == null)

  def makeMove(x: Int, y: Int, player: Player) {
    require(x < size && x >= 0)
    require(y < size && y >= 0)
    require(board(x)(y) == null)
    board(x)(y) = player
  }

  def playerWon(implicit player: Player) = check(row, col, diag, antidiag)

  private def check(fList: Int => Iterable[Player]*)(implicit player: Player) = fList.exists(f => range.exists(v => validate(f(v), player)))
  private def row(row: Int) = range.map(board(row)(_))
  private def col(col: Int) = range.map(board(_)(col))
  private def diag(col: Int) = range.map(i => board(i)(i))
  private def antidiag(col: Int) = range.map(i => board(size - 1 - i)(i))
  private def validate(spots: Iterable[Player], player: Player) = spots.forall(_ == player)

  def printBoard = board.foreach {
    x =>
      x.foreach(y => print(s"${if (y != null) y else "_"}\t"))
      println
  }
}