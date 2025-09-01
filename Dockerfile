# Etapa 1: Compilar a aplicação (build stage)
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copia pom.xml e baixa dependências para cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte e gera o JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Executa Flyway baseline e migrate
RUN mvn flyway:baseline flyway:migrate

# Etapa 2: Criar a imagem final (runtime stage)
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copia apenas o JAR da etapa de build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
