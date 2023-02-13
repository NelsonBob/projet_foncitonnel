package progfun

import com.typesafe.config.{Config, ConfigFactory}
import progfun.entree.ParserInput
import progfun.sortie.FileGenerator
import progfun.sortie.FileGenerator.jsonFileGenerator
import progfun.serializer.{CsvSerializer, JsonSerializer, YamlSerializer}

object Main extends App {

  val conf: Config = ConfigFactory.load()

  private val parserInput = new ParserInput
  private val input = parserInput.parse(conf.getString("appplication.input-file"))

  val mowers = for (mower <- input.mowers)
    yield mower.copy(finalPosition = mower.followInstructions(input.herbe))

  private val outputTypeChoice = conf.getString("appplication.output-type-choice")
  private val outputValue: String = outputTypeChoice match {
    case "json" =>
      import progfun.serializer.JsonSerializer.mowersSerializer
      JsonSerializer.serialize(input.herbe, mowers)
    case "yaml" =>
      import progfun.serializer.YamlSerializer.mowersSerializer
      YamlSerializer.serialize(input.herbe, mowers)
    case "csv" => {
      import progfun.serializer.CsvSerializer.mowersSerializer
      CsvSerializer.serialize(input.herbe, mowers)
    }
    case _ =>
      println("Invalid output type choice")
      ""
  }

  val file = FileGenerator.generateFile(outputTypeChoice, outputValue)
}
