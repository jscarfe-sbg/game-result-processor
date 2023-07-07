FROM gradle:jdk17 as builder

COPY . /opt/build/
WORKDIR /opt/build/

RUN mkdir -p /opt/app/ \
 && ./gradlew build \
 && mv /opt/build/build/libs/game-result-processor-all.jar /opt/app/app.jar

FROM openjdk:17.0.2-jdk

COPY --from=builder /opt/app /opt/app

ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]

