######  #     #   ###   #       ######
#     # #     #    #    #       #     #
#     # #     #    #    #       #     #
######  #     #    #    #       #     #
#     # #     #    #    #       #     #
#     # #     #    #    #       #     #
######   #####    ###   ####### ######
FROM maven:3.8.4-openjdk-11-slim AS builder

WORKDIR /build

COPY ./pom.xml .

# Copy source code
COPY ./src ./src

# Build application
RUN mvn package

######  #     # #     #
#     # #     # ##    #
#     # #     # # #   #
######  #     # #  #  #
#   #   #     # #   # #
#    #  #     # #    ##
#     #  #####  #     #
FROM openjdk:11-jre-slim

WORKDIR /

# Add application built jar file
COPY --from=builder /build/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]