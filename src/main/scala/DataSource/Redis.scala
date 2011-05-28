package hrana.DataSource
import scala.collection.immutable.HashMap
import com.redis
import com.redis._

class RedisDataSource(h: Host, key: String) extends DataSource
{
	def GetAll : Map[String, String] = new HashMap[String, String]
}