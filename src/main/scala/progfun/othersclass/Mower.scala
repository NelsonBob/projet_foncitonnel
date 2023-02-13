package progfun

import progfun.othersclass.{Herbe, Instructions, Point, Position}

case class Mower(currentPosition: Position, instructions: Instructions, finalPosition: Position) {
  def followInstructions(herbe: Herbe): Position = {
    instructions.value.foldLeft(currentPosition) { (position, instruction) =>
      instruction match {
        case 'G' => turnLeft(position)
        case 'D' => turnRight(position)
        case _ => move(herbe, position)
      }
    }
  }

  private def move(herbe: Herbe, position: Position): Position = {
    val newCoordinate = position.orientation match {
      case "E" => new Point(position.point.x + 1, position.point.y)
      case "S" => new Point(position.point.x, position.point.y - 1)
      case "W" => new Point(position.point.x - 1, position.point.y)
      case _ => new Point(position.point.x, position.point.y + 1)
    }
    if (herbe.isInside(newCoordinate)) {
      new Position(newCoordinate, position.orientation)
    } else {
      position
    }
  }

  private def turnLeft(position: Position): Position = {
    val newOrientation = position.orientation match {
      case "W" => "S"
      case "S" => "E"
      case "E" => "N"
      case _ => "W"
    }
    new Position(position.point, newOrientation)
  }

  private def turnRight(position: Position): Position = {
    val newOrientation = position.orientation match {
      case "E" => "S"
      case "S" => "W"
      case "W" => "N"
      case _ => "E"
    }
    new Position(position.point, newOrientation)
  }

}
