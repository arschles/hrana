package hrana.DataSource
import scala.collection.immutable.HashMap
import com.redis
import com.redis._

class NoSuchKeyException extends Exception
{}

class RedisDataSource(h: Host, key: String) extends DataSource
{
	var redis_ = new RedisClient(h.hostname, h.port)
	var key_ = key
		
	def GetAll : Map[String, String] = {
		val res = redis_.hgetall(key_).getOrElse(null)
		if(res == null)
		{
			throw new NoSuchKeyException
		}
		res
	}
}