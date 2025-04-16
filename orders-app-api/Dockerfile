# Etapa de Build
FROM openjdk:21-jdk as builder

WORKDIR /app

# Copia o Maven Wrapper (mvnw) e o arquivo pom.xml
COPY ./mvnw ./pom.xml ./
COPY .mvn/ .mvn/

# Dá permissão de execução ao mvnw
RUN sed -i 's/\r$//' mvnw && chmod +x ./mvnw

# Resolve as dependências do projeto
RUN ./mvnw dependency:resolve

# Copia o código-fonte para o container
COPY ./src ./src

# Faz o build do projeto
RUN ./mvnw package -DskipTests

# Etapa de execução
FROM openjdk:21-jdk

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta 8080 do container
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
