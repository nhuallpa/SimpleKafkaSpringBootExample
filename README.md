# Kafka producer/consumer example

I [install kafka](https://kafka.apache.org/quickstart) in `/usr/local/bin/kafka`, and I wil run the command in this directory.

- Start the Zookeeper:

```
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-topics.sh --create --topic replicated_topic  --zookeeper localhost:2181 --replication-factor 3 --partitions 3
```

- In each windows, start servers

```
bin/kafka-server-start.sh config/server-0.properties
bin/kafka-server-start.sh config/server-1.properties
bin/kafka-server-start.sh config/server-2.properties
```

- In each windows, start servers
```
bin/kafka-topics.sh --create --topic replicated_topic  --zookeeper localhost:2181 --replication-factor 3 --partitions 3
```

- Create topic with two replicas and partitions
```
bin/kafka-topics.sh --create --topic my_topic  --zookeeper localhost:2181 --replication-factor 3 --partitions 3
```
After this, I can check the topic created in with this:

```
bin/kafka-topics.sh --describe --topic my_topic  --zookeeper localhost:2181
```


- At this point, I can start my Spring Boot application to test the producer and consumer with Kafka:

```
gradle bootRun
```

- Test the application with:

```
curl -X POST -F 'message=test' http://localhost:9000/users
```

- The result of each post can be see like this in the log:

```
2020-08-29 17:26:50.829  INFO 28692 --- [nio-9000-exec-4] c.example.kafka.user.services.Producer   : ### -> Producing Message -> test 
2020-08-29 17:26:50.846  INFO 28692 --- [ntainer#0-0-C-1] c.example.kafka.user.services.Producer   : ### -> Consumed message -> test
2020-08-29 17:27:38.339  INFO 28692 --- [nio-9000-exec-5] c.example.kafka.user.services.Producer   : ### -> Producing Message -> test 
2020-08-29 17:27:38.354  INFO 28692 --- [ntainer#0-0-C-1] c.example.kafka.user.services.Producer   : ### -> Consumed message -> test
2020-08-29 17:27:40.230  INFO 28692 --- [nio-9000-exec-6] c.example.kafka.user.services.Producer   : ### -> Producing Message -> test 
2020-08-29 17:27:40.238  INFO 28692 --- [ntainer#0-0-C-1] c.example.kafka.user.services.Producer   : ### -> Consumed message -> test
2020-08-29 17:27:41.924  INFO 28692 --- [nio-9000-exec-7] c.example.kafka.user.services.Producer   : ### -> Producing Message -> test 
2020-08-29 17:27:41.930  INFO 28692 --- [ntainer#0-0-C-1] c.example.kafka.user.services.Producer   : ### -> Consumed message -> test
2020-08-29 17:27:44.053  INFO 28692 --- [nio-9000-exec-8] c.example.kafka.user.services.Producer   : ### -> Producing Message -> test 
2020-08-29 17:27:44.065  INFO 28692 --- [ntainer#0-0-C-1] c.example.kafka.user.services.Producer   : ### -> Consumed message -> test

```

## Referencias
[Kafka and SpringBoot](https://www.confluent.io/blog/apache-kafka-spring-boot-application/)
