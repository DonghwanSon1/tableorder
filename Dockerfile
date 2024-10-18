### 현재 사용 X ###

## 1. 기본 이미지 선택
#FROM openjdk:17-jdk
#
## 2. 작업 디렉토리 설정
#WORKDIR /spring-boot
#
## 3. Gradle Wrapper 및 Gradle 설정 파일 복사
#COPY gradlew ./
#COPY gradle/ ./gradle/
#COPY build.gradle.kts settings.gradle.kts ./
#
## 4. 소스 코드 복사
#COPY src ./src/
#
## 5. 실행 권한 부여
#RUN chmod +x gradlew
#
## 6. Gradle 빌드 수행
#RUN #./gradlew build --no-daemon
#
## 7. 빌드된 JAR 파일 복사
#COPY build/libs/*SNAPSHOT.jar app.jar
#
## 8. 애플리케이션 실행
#ENTRYPOINT ["java", "-jar", "/spring-boot/app.jar"]

FROM openjdk:17-jdk

WORKDIR /spring-boot

COPY build/libs/*SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/spring-boot/app.jar"]
# 10초 대기 후 애플리케이션 시작
#ENTRYPOINT ["sh", "-c", "sleep 10 && java -jar /spring-boot/app.jar"]