package hrana.DataSource
import scala.collection.immutable.HashMap

class RedisDataSource(h: Host) extends DataSource
{
    def GetAll : Map[String, String] = new HashMap[String, String]
}