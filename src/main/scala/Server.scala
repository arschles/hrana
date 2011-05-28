package hrana

import java.io.IOException;
import org.apache.thrift.server.TNonblockingServer
import org.apache.thrift.server.TNonblockingServer.Args;
import org.apache.thrift.transport.{TNonblockingServerTransport, TNonblockingServerSocket}
import hrana.thrift._
import hrana.DataSource._
import scala.actors.Actor
import scala.actors.Actor._

case class Info(s: String)
case class Warning(s: String)
case class Error(s: String)

object LogActor extends Actor
{
    start
    
    def act = 
    {
        loop
        {
            react
            {
                case s : String => println("[hrana-info] "+ s)
                case Info(s) => println("[hrana-info] " + s)
                case Warning(s) => println("[hrana-warning] " + s)
                case Error(s) => println("[hrana-error] " + s)
            }
        }
    }
}

object Server
{
    val port = 1463
    def main(args: Array[String])
    {
        try
        {
            val serverTransport:TNonblockingServerTransport = new TNonblockingServerSocket(port)
            
            val data_source = new NullDataSource
            val refresh_frequency = 1000
            val fetcher = new Fetcher(data_source, refresh_frequency)
            fetcher.start
            val processor = new Hrana.Processor(new ServerImpl(fetcher))
            val server = new TNonblockingServer(new Args(serverTransport).processor(processor))
            LogActor ! "Hrana serving on port " + port
            server.serve()
        }
        catch
        {
            case e : Exception => e.printStackTrace
        }
    }
}
