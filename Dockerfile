# ============================
# ETAPA 1 - COMPILAÇÃO
# ============================
FROM maven:3.9.4-eclipse-temurin-21 AS builder

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos do projeto
COPY pom.xml .
COPY src ./src

# Compila e empacota o JAR
RUN mvn clean package -DskipTests

# ============================
# ETAPA 2 - RUNTIME
# ============================
FROM eclipse-temurin:21-jdk-alpine

# Diretório de trabalho
WORKDIR /app

# Copia o JAR gerado da etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]