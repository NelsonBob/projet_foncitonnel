package progfun.serializer

import play.api.libs.json.Json
import progfun.Mower
import progfun.othersclass.Herbe


object JsonSerializer {
  // serialize dynamique
  def serialize[A, B](grass: A, lawnmowers: B)(
    implicit serializer: OutputSerializer[A, B, String]
  ): String =
    serializer.serialize(grass, lawnmowers)

  implicit val mowersSerializer: OutputSerializer[Herbe, List[Mower], String] = {
    (herbe: Herbe, mowers: List[Mower]) => {
      Json.prettyPrint(Json.obj(
        "limite" -> Json.obj(
          "x" -> herbe.width,
          "y" -> herbe.height
        ),
        "tondeuses" ->
          mowers.map {
            mower =>
              Json.obj(
                "debut" -> Json.obj(
                  "point" -> Json.obj(
                    "x" -> mower.currentPosition.point.x,
                    "y" -> mower.currentPosition.point.y
                  ),
                  "direction" -> mower.currentPosition.orientation
                ),
                "instructions" -> mower.instructions.value.split(""),
                "fin" -> Json.obj(
                  "point" -> Json.obj(
                    "x" -> mower.finalPosition.point.x,
                    "y" -> mower.finalPosition.point.y
                  ),
                  "direction" -> mower.finalPosition.orientation
                )
              )
          }
      ))
    }
  }

}
