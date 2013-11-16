package models

import play.api.Play.current
import java.util.Date
import com.novus.salat._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import se.radley.plugin.salat._
import mongoContext._

case class Log(
                 id: ObjectId = new ObjectId,
                 exception: String,
                 message: String,
                 trace: String,
                 address: Option[Address] = None,
                 added: Date = new Date(),
                 logDate: Option[Date] = None,
                 deleted: Option[Date] = None,
                 @Key("company_id")company: Option[ObjectId] = None
              )

object Log extends ModelCompanion[Log, ObjectId] {

  val dao = new SalatDAO[Log, ObjectId](collection = mongoCollection("logs")) {}
  def findByException(exception: String) = dao.find(MongoDBObject("exception" -> exception))

}