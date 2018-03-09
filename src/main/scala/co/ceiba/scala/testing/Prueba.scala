package co.ceiba.scala.testing

class Prueba {
  def getTest(data: String): String = {
    var test = "444 Ave Maria Stairway Ave"
    if(test.endsWith("Ave")) test = test.replace("Ave", "Avenida")
    return data+" Test Scala"
  }
}