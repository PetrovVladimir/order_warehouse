FROM openjdk:11-jre as builder

WORKDIR /opt/
COPY /target/order-service.jar /opt/order-service.jar
RUN java -Djarmode=layertools -jar /opt/order-service.jar extract

FROM openjdk:11-jre

RUN adduser --system --home /var/cache/bootapp --shell /sbin/nologin bootapp;
USER bootapp

WORKDIR /opt/

COPY --from=builder /opt/spring-boot-loader/ ./
RUN true
COPY --from=builder /opt/dependencies/ ./
RUN true
COPY --from=builder /opt/snapshot-dependencies/ ./
RUN true
COPY --from=builder /opt/application/ ./

ENTRYPOINT java org.springframework.boot.loader.JarLauncher