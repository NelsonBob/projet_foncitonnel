package progfun.serializer

import progfun.Mower
import progfun.othersclass.Herbe


object CsvSerializer {
  // serialize dynamique
  def serialize[A, B](herbe: A, mowers: B)(
    implicit serializer: OutputSerializer[A, B, String]
  ): String =
    serializer.serialize(herbe, mowers)

  implicit val mowersSerializer
  : OutputSerializer[Herbe, List[Mower], String] = {
    (herbe: Herbe, mowers: List[Mower]) => {
      List("numéro;",
        "taille_x;",
        "taille_y;",
        "début_x;",
        "début_y;",
        "début_direction;",
        "fin_x;",
        "fin_y;",
        "fin_direction;",
        "instructions\n",
        mowers.zipWithIndex.map {
          case (mower, index) =>
            List((index + 1).toString,
              herbe.width.toString,
              herbe.height.toString,
              mower.currentPosition.point.x.toString,
              mower.currentPosition.point.y.toString,
              mower.currentPosition.orientation,
              mower.finalPosition.point.x.toString,
              mower.finalPosition.point.y.toString,
              mower.finalPosition.orientation,
              mower.instructions.value)
              .mkString(";")
        }.mkString("\n")).mkString("")
    }
  }

}
