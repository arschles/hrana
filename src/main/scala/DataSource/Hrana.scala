package hrana.DataSource

import scala.collection.immutable.HashMap
import scala.collection.JavaConverters._
import hrana.thrift._
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.TException;

//data source that lets you chain together hrana instances
class HranaDataSource(h : Host) extends DataSource
{
    def GetAll : Map[String, String] = {
		val transport = new TFramedTransport(new TSocket(h.hostname, h.port))
		val protocol = new TBinaryProtocol(transport);
		val client = new Hrana.Client(protocol)
		client.GetAll.asScala.toMap
	}
}