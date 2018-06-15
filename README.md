# examen2

Ejecutar el comando para compilar

docker run -it -v /home/gustavo/curso/examen2/:/codigo kebblar/jdk18-utf8-debug-maven mvn -f /codigo clean package

Para verificar que corra

docker run -it -p 8081:8080 -v /home/gustavo/curso/examen2/:/codigo gustavoarellano/jdk18 java -jar /codigo/target/sample-1.0-SNAPSHOT-fat.jar

