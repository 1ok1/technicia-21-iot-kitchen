# technicia-21-iot-kitchen


To Start Docker
docker-compose up -d


To Create new topic
docker exec -it c95ab5cc2c58 /opt/bitnami/kafka/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic javatechie

export PATH=/opt/bitnami/kafka/bin:$PATH

