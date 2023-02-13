package progfun.serializer

import progfun.Mower
import progfun.othersclass.Herbe


object YamlSerializer {
  // serialize dynamique
  def serialize[A, B](herbe: A, mowers: B)(
    implicit serializer: OutputSerializer[A, B, String]
  ): String =
    serializer.serialize(herbe, mowers)

  implicit val mowersSerializer: OutputSerializer[Herbe, List[Mower], String] = {
    (herbe: Herbe, mowers: List[Mower]) => {
      "limite: \n"
        .concat("  x: ")
        .concat(herbe.width.toString
          .concat("\n")
          .concat("  y: ")
          .concat(herbe.height.toString)
          .concat("\n")
          .concat("tondeuses: \n")
          .concat(mowers.map {
            mower =>
              "  - debut: \n"
                .concat("      point: \n")
                .concat("        x: ")
                .concat(mower.currentPosition.point.x.toString)
                .concat("\n")
                .concat("        y: ")
                .concat(mower.currentPosition.point.y.toString)
                .concat("\n")
                .concat("      direction: ")
                .concat(mower.currentPosition.orientation)
                .concat("\n")
                .concat("    instructions: ")
                .concat(mower.instructions.value.split("").map(
                  instruction => "\n      - ".concat(instruction)
                ).mkString)
                .concat("\n")
                .concat("    fin: \n")
                .concat("      point: \n")
                .concat("        x: ")
                .concat(mower.finalPosition.point.x.toString)
                .concat("\n")
                .concat("        y: ")
                .concat(mower.finalPosition.point.y.toString)
                .concat("\n")
                .concat("      direction: ")
                .concat(mower.finalPosition.orientation)
          }.mkString("\n")))
    }
  }

}
