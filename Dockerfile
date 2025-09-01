# -------- Build stage --------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Download deps first for better layer caching
COPY pom.xml .
RUN mvn -q -B -e -DskipTests dependency:go-offline

# Now copy sources and build
COPY src ./src
RUN mvn -q -B -e -DskipTests package

# -------- Runtime stage --------
FROM eclipse-temurin:17-jre
# (optional) run as non-root
RUN useradd -ms /bin/bash appuser
WORKDIR /app

# Copy the fat jar from the build stage
COPY --from=build /app/target/*.jar /app/app.jar

# Reasonable JVM defaults for containers
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0 -XX:InitialRAMPercentage=25.0 -Djava.security.egd=file:/dev/./urandom"

# Override YAML if needed (Eureka URL inside Docker network)
ENV EUREKA_CLIENT_SERVICE_URL_DEFAULTZONE="http://eureka:8761/eureka/"
ENV SERVER_PORT=9190

EXPOSE 9190
USER appuser
ENTRYPOINT ["sh","-c","exec java $JAVA_OPTS -jar /app/app.jar"]
