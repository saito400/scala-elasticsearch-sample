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

        client.deleteByQuery(config){ searcher => 
          searcher.setQuery(QueryBuilders.matchQuery("user", "JohnDoe"))
        }

        // Insert
        client.insert(config, Tweet(user = Some("JohnDoe"), message = Some("Hello World!")))
        client.insert(config, Tweet(user = Some("JohnDoe"), message = Some("Hello World!!")))
        client.insert(config, Tweet(user = Some("JohnDoe"), message = Some("Hello World!!!")))

        // Update
//        client.update(config, "1", Tweet(Some("Jane doe"), Some("Hello Scala!!")))
//        client.updateJson(config, "1", """{name: "Jane doe", message: "Hello World!!"}""")

        // Delete
//        client.delete(config, "1")

        // Find one document
        val tweet: Option[(String, Tweet)] = client.find[Tweet](config) { seacher =>
          seacher.setQuery(QueryBuilders.matchQuery("user", "JohnDoe"))
        }

        println(tweet)

        // Search documents
        val list: ESSearchResult[Tweet] = client.list[Tweet](config) { seacher =>
          seacher.setQuery(QueryBuilders.matchQuery("user", "JohnDoe"))
        }

        println(list)

      }

      println("end")
    }
  }
}

