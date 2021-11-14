# technicia-21-iot-kitchen


To Start Docker
docker-compose up -d

export PATH=/opt/bitnami/kafka/bin:$PATH

To Create new topic
docker exec -it kafka /opt/bitnami/kafka/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic grocerystream

To Produce stream text in CLI
docker exec -it kafka /opt/bitnami/kafka/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic grocerystream

To Consume stream text in CLI
docker exec -it kafka /opt/bitnami/kafka/bin/./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic grocerystream --from-beginning


