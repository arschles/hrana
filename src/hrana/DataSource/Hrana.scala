import hrana.DataSource
import scala.collection.immutable.HashMap

class HranaDS(h : Host) extends DataSource
{
    def GetAll : Map[String, String] = new HashMap[String, String]
}