import java.io.IOException;
import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.protocol.TBinaryProtocol.Factory
import org.apache.thrift.server.{TServer, TNonblockingServer}
import org.apache.thrift.transport.{TNonblockingServerTransport, TNonblockingServerSocket, TTransportException}
import hrana.thrift._
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
                case s : String => println(s)
                case Info(s) => println("> " + s)
                case Warning(s) => println("WARNING> " + s)
                case Error(s) => println("ERROR>> " + s)
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
            val serverTransport = new TNonblockingServerSocket(port)
            
            //TODO:add config
            val data_source = new FileDS("./sample_data_source.config")
            val refresh_frequency = 1000
            val fetcher = new Fetcher(data_source, refresh_frequency)
            fetcher.start
            
            val impl = new ServerImpl(fetcher)
            val processor = new Hrana.Processor(impl)
            val protFactory = new TBinaryProtocol.Factory(true, true)
            val server = new TNonblockingServer(processor, serverTransport)
            LogActor ! "Hrana serving on port " + port
            server.serve()
        }
        catch
        {
            case e : Exception => e.printStackTrace
        }
    }
}
