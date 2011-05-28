package hrana.DataSource
import scala.collection.immutable.HashMap

//data source that does nada
class NullDataSource extends DataSource
{
    def GetAll : Map[String, String] = new HashMap[String, String]
}