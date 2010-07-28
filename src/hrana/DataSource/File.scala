import scala.io.Source._
import scala.collection.immutable.HashMap

class FileDS(filename : String) extends DataSource
{
    def GetAll : Map[String, String] = 
    {
        val lines = fromPath(filename).getLines("\n")
        var ret = new HashMap[String, String]
        for(line : String <- lines)
        {
            val spl = line.split(":").toList
            val tup = (spl(0), spl(1))
            ret = ret + tup
        }
        
        ret
    }
}