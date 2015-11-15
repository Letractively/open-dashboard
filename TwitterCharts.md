# Idea #
I was correlating ODB with the tweeter (regarding publisher-subscriber model) and an Idea struck that we can use tweeter to send data to ODBServer on periodic basis. This will give the power of all the tweeter clients that are currently available for data collection. This way ODB will be universally accessible. Essentially, we will be using twitter as a carrier for graph data.

## How it works ##
So What users have to do is to send a message to twitter in the following format
@odb pubID=XXX,dsID=YYY,data=0,1,2

To avoid collision here, we can use tweet-sender ID for authentication of pubID and dsID.

So essentially, parallel to collector component in the odbserver, we need to implements tweetcollector component that actually will have its own tweeter account(e.g. odb) and publishers will send Reply tweets for this account with the data. tweetcollector will implement tweeter API to check the odb account periodically and update the dataseries by calling addDataSeries(). Quite Simple Huh!!

## Applications ##
This could be useful for budget monitoring, i.e. a person always tweets about his spendings to odb and then can monitor his spending charts later on.
