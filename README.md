# MyBookStore
Demo Spring Boot application for a BookStore using MongoDB

# Install MongoDB:    
https://docs.mongodb.com/manual/tutorial/install-mongodb-on-os-x/

# How to run mongoDB: 
mongod

# Install Kafka 

# How to run Kafka on local machine 
1. Run Zookeeper : ./bin/zookeeper-server-start.sh config/zookeeper.properties
1. Run Kafka : ./bin/kafka-server-start.sh config/server.properties

# Check Kafka Topics 
./bin/kafka-topics.sh --list --zookeeper localhost:2181  

#Check Data on Kafka Topic 
./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic OrderCreation --from-beginning

# How to run application:
 ./mvnw spring-boot:run
 
# URL: 
1. UserController : http://localhost:8080/swagger-ui/#/user-controller
1. BookController : http://localhost:8080/swagger-ui/#/book-controller
1. CartController : http://localhost:8080/swagger-ui/#/cart-controller
1. OrderController : http://localhost:8080/swagger-ui/#/order-controller
