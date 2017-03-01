FROM tomcat:jre8-alpine
LABEL organization="RENCI"
LABEL maintainer="michael_conway@unc.edu"
LABEL description="iRODS Core REST API."
ADD runit.sh /

ADD target/irods-base-rest.war /usr/local/tomcat/webapps/
CMD ["/runit.sh"]



# build: docker build -t diceunc/irods-base-rest:4.2.1.0 .

# run:  docker run -d --rm -p 8080:8080 -v /etc/irods-ext:/etc/irods-ext  --add-host irods420.irodslocal:172.16.250.101 diceunc/irods-base-rest:4.2.1.0
