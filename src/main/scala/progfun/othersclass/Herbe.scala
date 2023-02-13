package progfun.othersclass

class Herbe(val width: Int, val height: Int) {
  def isInside(point: Point): Boolean = {
    point.x >= 0 && point.x <= width && point.y >= 0 && point.y <= height
  }
}
