import hrana.DataSource

import scala.collection.immutable.HashMap

class FileDS(filename : String) extends DataSource
{
    def GetAll : Map[String, String] = new HashMap[String, String]
}