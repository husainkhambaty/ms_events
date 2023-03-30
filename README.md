# Events Service - Publish to Kafka Topic
This service provides a REST interface to help publish a message/event to a Kafka topic.

## Pre-requisites
This service depends on pre-existing Kafka infrastructure. Use the below commands to bring up all Kafka components to quickly play with the Publisher.

### Get Kafka

Download Kafka latest tarball [here](https://www.apache.org/dyn/closer.cgi?path=/kafka/3.4.0/kafka_2.13-3.4.0.tgz).
Setup Kafka locally on your machine.

```bash
tar -xzf kafka_2.13-3.4.0.tgz
cd kafka_2.13-3.4.0
```

Note: Define your `KAFKA_HOME` environment variable to the Kafka installable path on your machine.

```bash
export KAFKA_HOME="<YOUR-KAFKA-PATH>"
```

### Kafka ZooKeeper service

Start the Kafka ZooKeeper service using the below command:
```bash
${KAFKA_HOME}/bin/zookeeper-server-start.sh ${KAFKA_HOME}/config/zookeeper.properties
``` 

This will bring up the ZooKeeper on default port `2181`. 

### Kafka Broker service

Start the Kafka Broker service using the below command:
```bash
${KAFKA_HOME}/bin/kafka-server-start.sh ${KAFKA_HOME}/config/server.properties
``` 

### Topic Creation
Create a topic called "NewTopic"
```bash
${KAFKA_HOME}/bin/kafka-topics.sh --create --replication-factor 1 --partitions 1 --topic NewTopic --bootstrap-server localhost:9092
```

### Console Consumer
Start a console consumer to view the messages that will be produced out of the Events service via REST interface
```bash
${KAFKA_HOME}/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic NewTopic1 --from-beginning
```

## Start the service

Bring up the service using the below command in your project directory:
```console
./gradlew bootRun
```

This will start the service on default port `8080`.

## Publish Messages

Use postman to hit the `publish` endpoint to publish events to Kafka topic.
  
![Postman Usage](/images/Postman_Usage.png?raw=true "Postman Usage")