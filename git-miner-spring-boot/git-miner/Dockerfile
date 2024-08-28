FROM maven:3.8.6-ibmjava-8 as build
WORKDIR /git-miner-spring-boot
COPY . /git-miner-spring-boot/
RUN mvn clean install -DskipTests
RUN mkdir -p ./target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:8-jdk-alpine
WORKDIR /git-miner-spring-boot
ARG BUILD_FOLDER=/git-miner-spring-boot/target/dependency
COPY --from=build ${BUILD_FOLDER}/BOOT-INF/lib ./app/lib
COPY --from=build ${BUILD_FOLDER}/META-INF ./app/META-INF
COPY --from=build ${BUILD_FOLDER}/BOOT-INF/classes ./app
RUN mkdir /data
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.ericsson.gitminer.GitMinerApplication"]