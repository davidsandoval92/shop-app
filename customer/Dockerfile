FROM gradle:8.7-jdk21-alpine
WORKDIR extracted

COPY . .

RUN gradle clean build -x test

ENTRYPOINT jarFile=$(find "./build/libs" -type f -name "*.jar" | grep -v "plain"); java -jar $jarFile

EXPOSE 8080
