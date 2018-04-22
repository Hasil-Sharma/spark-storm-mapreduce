package mapreduce.mappers

import mapreduce.utils.GetTweets
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.io.{NullWritable, Text}
import org.apache.hadoop.mapreduce.Mapper
import twitter4j.Logger

class TweetMapper extends Mapper[Text, Text, Object, Text]{
  val word = new Text()
  val logger = Logger.getLogger(classOf[TweetMapper])
  override def map(key: Text, value: Text, context: Mapper[Text, Text, Object, Text]#Context): Unit = {
    val configuration: Configuration = context.getConfiguration()
    var num : Int  = configuration.getInt("num", -1)
    val token : String = configuration.get("token")
    if (num == -1){
      println("Failed !")
      System.exit(1)
    }
    val getTweets: GetTweets = new GetTweets()
    val queue = getTweets.consumeTweets(num)
    getTweets._twitterStream.sample("en")
    logger.info("Starting to fetch tweets")
    while (num > 0){
      val ret = Option(queue.poll())
      if (ret.nonEmpty && ret.get.contains(token)) {
        word.set(ret.get.stripMargin)
        context.write(NullWritable.get(), word)
        num -= 1
        logger.info(s"Left with downloading tweets: $num")
      }
    }

    logger.info("Done with downloading tweets")
    getTweets._twitterStream.shutdown()
    getTweets._twitterStream.cleanUp()

  }

}
