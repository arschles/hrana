package hrana

import java.util.Date
import scala.collection.immutable.HashMap
import scala.actors.Actor
import hrana.DataSource._

object Fetcher
{
    case class Get()
    case class Clear()
    
    def time : Long = 
    {
        val d = new Date
        d.getTime
    }
}

//takes in the data source and the frequency (milliseconds) which the fetcher should fetch
class Fetcher(source : DataSource, interval : Long) extends Actor
{    
    def act = 
    {
        var last_fetched_time = Fetcher.time
        var last_fetched = source.GetAll
        
        while(true)
        {
            var cur_time = Fetcher.time
            if( (cur_time - last_fetched_time) > interval )
            {
				try
				{
					last_fetched = source.GetAll
					last_fetched_time = cur_time
				}
				catch
				{
					case e: NoSuchRedisKey => LogActor ! "unable to get redis data"
					case e: Exception => LogActor ! "generic exception: " + e
				}
            }
            receive
            {
                case Fetcher.Get() => reply(last_fetched)
                case Fetcher.Clear() => last_fetched = new HashMap[String, String]
            }
        }
    }
}