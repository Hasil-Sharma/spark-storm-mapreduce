# Spark Storm MapReduce Project in Scala


## Mapreduce
Compiling and assembling mapreduce code:
```sbt 'project mapreduce' compile assembly```

Running Word Count:
- classname: mapreduce.WordCount
- args: "Input Path" "Output Path"

Running Twitter MapReduce:
- classname: mapreduce.TweetMR
- args: "Input Path(Dummy)" "Output Path" "Tweets to Consume"

Running PageRank:
- classname: mapreduce.PageRank
- args: --input "input path" --output "output path"

## Spark
Compiling and assembling spark code:
`sbt 'project spark' compile assembly`

Running Word Count:
- classname: spark.WordCount
- args: "Input Path" "Output Path"

Running Twitter:
- classname: spark.TwitterLiveStream
- args: "Input Path(Dummy)" "Output Path" "Tweets to Consume"

Running PageRank:
- classname: spark.PageRank
- args: "Input Path" "Output Path"


## Storm
Compiling and assembling storm code:
`sbt 'project storm' compile assembly`

Running Word Count:
- classname: storm.WordCount

Running Twitter:
- classname: storm.TwitterLiveStream

Running PageRank:
- classname: storm.PageRank
