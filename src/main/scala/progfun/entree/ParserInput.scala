

package progfun.entree


import better.files._
import progfun._
import progfun.exeption.DataException
import progfun.othersclass.{Herbe, Instructions, Point, Position}

class ParserInput() {
  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def parse(fileName: String): Input = {
    try {
      val file = File(fileName)
      val lines = file.lines.toList

      val herbe = parseGrass(lines(0))
      val mowers = parseLawnmowers(lines.drop(1))
      new Input(herbe, mowers)
    } catch {
      case e: Exception => throw DataException("No file with this name or incorrect data in file: " + fileName + " " + e.getMessage)
    }
  }

  private def parseGrass(str: String): Herbe = {
    val coordinates = str.split(" ")
    new Herbe(coordinates(0).toInt, coordinates(1).toInt)
  }

  private def parseLawnmowers(lines: List[String]): List[Mower] = {
    lines.grouped(2).map(mower => {
      val points = mower(0).split(" ")
      val point = new Point(points(0).toInt, points(1).toInt)
      val orientation = points(2)
      val instructions = new Instructions(mower(1))
      new Mower(new Position(point, orientation), instructions, new Position(new Point(0, 0), "N"))
    }).toList
  }
}

