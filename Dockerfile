# ---------- Build stage: 用 Maven 打包 fat jar ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY settings.xml .
COPY src ./src
RUN mvn -s settings.xml -q clean package -DskipTests

# ---------- Runtime stage: 只帶 JRE + jar，映像細、啟動快 ----------
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
# Render 免費版得 512MB RAM：限制 JVM 用最多 75% 記憶體，留返位俾系統
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]
