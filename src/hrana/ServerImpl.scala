import java.util.Map
import java.util.Map._

import org.apache.thrift._
import org.apache.thrift.meta_data._
import org.apache.thrift.protocol._

import org.apache.thrift.TException
import hrana.thrift._
import hrana.DataSource._
import scala.collection.JavaConversions._
import scala.collection.immutable.HashMap

class ServerImpl(f : Fetcher) extends Hrana.Iface
{
    val NO_SUCH_KEY = 100
    val KEY_EXISTS = 101
    
    private var local = new HashMap[String, String]
    
    @throws(classOf[TException])
    def Has(key : String) : Boolean = 
    {
        val all = GetAll
        all.contains(key)
    }
    
    @throws(classOf[HranaException])
    @throws(classOf[TException])
    def Get(key : String) : String = 
    {
        val all = GetAll()
        val ret : String = all.get(key)
        if(ret == null) throw new HranaException(NO_SUCH_KEY, "no such key " + key)
        ret
    }
    
    @throws(classOf[TException])
    def GetAll : Map[String,String] = 
    {
        var m = new java.util.HashMap[String, String]
        
        //merge f.GetAll with local, overwriting with local if necessary
        val fetched = (f !? Fetcher.Get()).asInstanceOf[HashMap[String, String]]
        fetched.foreach { case (k: String, v: String) => m.put(k, v) }
        local.foreach { case (k: String, v: String) => m.put(k, v) }
        m
    }
    
    @throws(classOf[HranaException])
    @throws(classOf[TException])
    def Push(key : String, value : String, overwrite : Boolean) : Boolean = 
    {
        if(local.contains(key) && overwrite == false) throw new HranaException(KEY_EXISTS, "key [" + key + "] already exists")
        val toadd = (key, value)
        local = local + toadd
        true
    }
    
    @throws(classOf[TException])
    def PushBulkAsync(vals : Map[String, String]) = 
    {
        for(entry : Map.Entry[String, String] <- vals.entrySet())
        {
            val toadd = (entry.getKey, entry.getValue)
            local = local + toadd
        }
    }
    
    @throws(classOf[TException])
    def Clear : Boolean = 
    {
        local = new HashMap[String, String]
        f ! Fetcher.Clear
        true
    }
    
}