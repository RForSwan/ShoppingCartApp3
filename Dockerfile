FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

COPY ./pom.xml .
COPY ./src ./src

RUN mvn -DskipTests package

FROM eclipse-temurin:21-jdk

WORKDIR /app

# Install GUI libraries and CJK fonts
RUN apt-get update && apt-get install -y \
    libx11-6 libxext6 libxrender1 libxtst6 libxi6 libgtk-3-0 mesa-utils wget unzip \
    fontconfig fonts-noto-cjk \
    && fc-cache -f -v \
    && rm -rf /var/lib/apt/lists/*


# Download JavaFX SDK
RUN mkdir -p /javafx-sdk \
    && wget -O javafx.zip https://download2.gluonhq.com/openjfx/21/openjfx-21_linux-x64_bin-sdk.zip \
    && unzip javafx.zip -d /javafx-sdk \
    && mv /javafx-sdk/javafx-sdk-21/lib /javafx-sdk/lib \
    && rm -rf /javafx-sdk/javafx-sdk-21 javafx.zip

# Copy fat JAR
COPY --from=build /app/target/ShoppingCartApp3-1.0-SNAPSHOT.jar app.jar
# COPY target/carcassonne.jar app.jar

# Set DISPLAY for Windows (Xming)
#ENV DISPLAY=host.docker.internal:0.0

# Run JavaFX app
CMD ["java", "--module-path", "/javafx-sdk/lib", "--add-modules", "javafx.controls,javafx.fxml", "-jar", "app.jar"]

#FROM maven:3.9.12-eclipse-temurin-21 AS build
#LABEL authors="RForSwan"
#
#WORKDIR /app
#
#COPY pom.xml .
#
#COPY . /app
#
#RUN mvn -DskipTests package
#
#CMD ["java","-jar", "target/ShoppingCartApp3-1.0-SNAPSHOT.jar"]