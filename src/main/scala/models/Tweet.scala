package models
import Tweet._

case class Tweet(
  message: Option[String], 
  user: Option[String]
)

object Tweet {

  val typeName = "tweet"


  val message = "message"
  val user = "user"

}