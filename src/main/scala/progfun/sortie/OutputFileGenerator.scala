package progfun.sortie

import better.files.File

trait OutputFileGenerator[A, B] {
  def generateFile(fileType: A, fileContent: B): File
}
