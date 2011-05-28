package hrana.DataSource

object DataType extends Enumeration
{
    val json = Value
    val csv = Value
    //val xml = Value
    //val binpack = Value
}

case class Host(hostname : String, port: Int)

trait DataSource
{
    def GetAll : Map[String, String]
}