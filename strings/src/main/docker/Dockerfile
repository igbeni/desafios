FROM qaware-oss-docker-registry.bintray.io/base/debian8-jre8
MAINTAINER Iggor Alves <iggorbeni@gmail.com>
COPY IDwall.jar /opt/IDwall-strings/IDwall.jar
COPY IDwall-strings-config.yaml /opt/IDwall-strings/IDwall-strings-config.yaml
EXPOSE 2890
ENTRYPOINT java -jar /opt/IDwall-strings/IDwall.jar server /opt/IDwall-strings/IDwall-strings-config.yaml
