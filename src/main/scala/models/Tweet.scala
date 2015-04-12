package models
import Tweet._

case class Tweet(
  message: Option[String], 
  post_date: Option[org.joda.time.DateTime], 
  user: Option[String]
)

object Tweet {

  val typeName = "tweet"


  val message = "message"
  val post_date = "post_date"
  val user = "user"

}