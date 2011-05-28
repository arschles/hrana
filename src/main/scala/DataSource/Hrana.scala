package hrana.DataSource

import scala.collection.immutable.HashMap

class HranaDataSource(h : Host) extends DataSource
{
    def GetAll : Map[String, String] = new HashMap[String, String]
}