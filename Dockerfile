FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package -DskipTests

FROM openjdk:8-jre-alpine
WORKDIR /app

# Get firefox
RUN apk add firefox-esr=60.9.0-r0

RUN wget -q -O /etc/apk/keys/sgerrand.rsa.pub https://alpine-pkgs.sgerrand.com/sgerrand.rsa.pub
RUN wget https://github.com/sgerrand/alpine-pkg-glibc/releases/download/2.30-r0/glibc-2.30-r0.apk
RUN wget https://github.com/sgerrand/alpine-pkg-glibc/releases/download/2.30-r0/glibc-bin-2.30-r0.apk
RUN apk add glibc-2.30-r0.apk glibc-bin-2.30-r0.apk
RUN rm -rf glibc-2.30-r0.apk glibc-bin-2.30-r0.apk

# Install geckodriver
RUN wget https://github.com/mozilla/geckodriver/releases/download/v0.26.0/geckodriver-v0.26.0-linux64.tar.gz
RUN tar -zxf geckodriver-v0.26.0-linux64.tar.gz -C /usr/bin
RUN rm -rf geckodriver-v0.26.0-linux64.tar.gz


COPY --from=MAVEN_BUILD /build/src/main/resources/*.yml /app/
COPY --from=MAVEN_BUILD /build/target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]