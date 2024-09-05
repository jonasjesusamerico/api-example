FROM gradle:7.6.0-jdk17-alpine AS build

# Definindo o diretório de trabalho dentro do container
WORKDIR /app

# Copiando os arquivos de projeto para o container
COPY . /app

# Executando o comando de build com Gradle para gerar o JAR
RUN ./gradlew build --no-daemon -x test

FROM openjdk:17-jdk-slim

# Definindo o diretório de trabalho no container final
WORKDIR /app

# Copiando o JAR gerado do estágio de build anterior para o container final
COPY --from=build /app/build/libs/api-wallet-0.0.1-SNAPSHOT.jar /app/api-wallet-0.0.1-SNAPSHOT.jar

# Definindo o comando de entrada para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app/api-wallet-0.0.1-SNAPSHOT.jar"]

