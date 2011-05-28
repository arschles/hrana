package hrana.DataSource
import scala.collection.immutable.HashMap

class NullDataSource extends DataSource
{
    def GetAll : Map[String, String] = new HashMap[String, String]
}