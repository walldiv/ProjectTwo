# start with the alpine linux tomcat image
FROM tomcat:8.5-jdk8-openjdk

ARG PG_DB_NAME
ARG PG_PASSWORD
ARG PG_PORT
ARG PG_URL
ARG PG_USERNAME

ENV POSTGRES_DATABASE_NAME $PG_DB_NAME
ENV POSTGRES_PASSWORD $PG_PASSWORD
ENV POSTGRES_PORT $PG_PORT
ENV POSTGRES_URL $PG_URL
ENV POSTGRES_USERNAME $PG_USERNAME

# switch to the CATALINA_HOME directory
WORKDIR $CATALINA_HOME

# create a reference to some file on the physical machine (optional)
ARG WAR_FILE=./target/*.war

# copy the file from the physical machine to the webapps dir in the PWD
COPY $WAR_FILE ./webapps/ROOT.war

# EXPOSE port 8080
EXPOSE 8080

# execute this command to start tomcat
CMD ["catalina.sh", "run"]

# image creation complete
