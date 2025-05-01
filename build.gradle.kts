plugins {
    id("java")
    id("org.springframework.boot") version "2.7.18"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "io.github.inertia4j"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.inertia4j:inertia4j-spring:1.0.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
