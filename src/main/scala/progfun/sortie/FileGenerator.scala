
package progfun.sortie
import better.files.File
import progfun.Main.conf


object FileGenerator {
  def generateFile[A, B](fileType: A, fileContent: B)(
    implicit generator: OutputFileGenerator[A, B]
  ): File =
    generator.generateFile(fileType, fileContent)

  implicit val jsonFileGenerator: OutputFileGenerator[String, String] = {
    (fileType: String, fileContent: String) => {
      val outputFileName = conf.getString("appplication.output-" + fileType + "-file")
      val file = File(outputFileName)

      file.createIfNotExists().overwrite(fileContent)
    }
  }

}
