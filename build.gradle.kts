plugins {
    id("java")
    id("org.springframework.boot").version("3.0.2")
    id("io.spring.dependency-management").version("1.1.0")
}

group = "me.leejinkyung"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    implementation("org.springframework.security:spring-security-web")
    testImplementation("org.springframework.security:spring-security-test")

    runtimeOnly("com.h2database:h2")
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
}

tasks.test {
    useJUnitPlatform()
}