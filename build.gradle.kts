plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
	kotlin("plugin.jpa") version "1.9.25"
	kotlin("kapt") version "1.9.25"
	id ("org.flywaydb.flyway") version "8.5.13"
}

group = "tableorder"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// QueryDSL
	implementation ("com.querydsl:querydsl-jpa:5.0.0:jakarta")
	// Swagger
	implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")
	// Flyway
	implementation ("org.flywaydb:flyway-core")
	implementation ("org.flywaydb:flyway-mysql")
	// Validation
	implementation("org.springframework.boot:spring-boot-starter-validation")
	// Spring Security 사용시 필요
	implementation("org.springframework.boot:spring-boot-starter-security")
	// JWT 사용시 필요
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

	kapt ("com.querydsl:querydsl-apt:5.0.0:jakarta")
	kapt ("jakarta.annotation:jakarta.annotation-api")
	kapt ("jakarta.persistence:jakarta.persistence-api")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.mysql:mysql-connector-j")

//	implementation("org.springframework.boot:spring-boot-starter-security")
//	runtimeOnly("com.h2database:h2")
//	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
//	testImplementation("org.springframework.security:spring-security-test")
//	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
