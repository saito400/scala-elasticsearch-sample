package main {

  import jp.co.bizreach.elasticsearch4s._
  import org.elasticsearch.index.query.QueryBuilders
  import models._

  object Main {

//    case class Tweet(name: String, message: String)

    def main(args: Array[String]) {
      println("start")

      ESClient.using("http://localhost:9200") { client =>
        val config = ESConfig("twitter", "tweet")

        // Insert
        client.insert(config, Tweet(user = Some("John doe"), message = Some("Hello World!")))
        client.insert(config, Tweet(user = Some("John doe"), message = Some("Hello World!!")))
        client.insert(config, Tweet(user = Some("John doe"), message = Some("Hello World!!!")))
        client.insert(config, Tweet(user = Some("John doe"), message = Some("Hello World!!!!")))
//        client.insertJson(config, """{name: "John doe", message: "Hello World!!"}""")

        // Update
//        client.update(config, "1", Tweet(Some("Jane doe"), Some("Hello Scala!!")))
//        client.updateJson(config, "1", """{name: "Jane doe", message: "Hello World!!"}""")

        // Delete
//        client.delete(config, "1")

        // Find one document
        val tweet: Option[(String, Tweet)] = client.find[Tweet](config) { seacher =>
          seacher.setQuery(QueryBuilders.termQuery("user", "John doe"))
        }

        println(tweet)

        // Search documents
        val list: ESSearchResult[Tweet] = client.list[Tweet](config) { seacher =>
          seacher.setQuery(QueryBuilders.termQuery("user", "John doe"))
        }

        println(list)

      }

      println("end")
    }
  }
}

