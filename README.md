# examen2

Ejecutar el comando para compilar

docker run -it -v /home/gustavo/curso/examen2/:/codigo kebblar/jdk18-utf8-debug-maven mvn -f /codigo clean package


Para verificar que corra

docker run -it -p 8081:8080 -v /home/gustavo/curso/examen2/:/codigo gustavoarellano/jdk18 java -jar /codigo/target/sample-1.0-SNAPSHOT-fat.jar


Para  compilar el docker

docker build -t examen2 .


para ejecutar examen2

docker run -d -p 8081:8080 examen2


Para hacer commit del docker

docker commit 39edd733e458 dgpecurso12/examen12


Para subir a docker hub

docker push dgpecurso12/examen12


para ejecutar el docker desde docker hub

docker run -d p 8081:8080 dgpecurso12/examen12

