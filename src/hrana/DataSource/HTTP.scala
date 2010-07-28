import scala.collection.immutable.HashMap

class HTTPDS(h: Host) extends DataSource
{
    def GetAll : Map[String, String] = new HashMap[String, String]
}