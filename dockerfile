FROM java:8-jre
WORKDIR /usr/src
ENV MYSQL_DATABASE=cricdb
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/cricdb 
ADD ./target/favouriteservice-1.0.jar /usr/src/favouriteservice-1.0.jar
ENTRYPOINT ["java","-jar","favouriteservice-1.0.jar"]