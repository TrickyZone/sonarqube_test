FROM  maven:3.6.3-openjdk-11 as builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests=true


FROM openjdk:11-jdk-slim
WORKDIR /app
RUN echo "Asia/Kolkata" > /etc/timezone && \
    cp /usr/share/zoneinfo/Asia/Kolkata /etc/localtime
COPY --from=builder /app/target/feed-service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","feed-service-0.0.1-SNAPSHOT.jar"]