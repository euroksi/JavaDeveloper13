plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation ("jakarta.persistence:jakarta.persistence-api:3.1.0")

    implementation ("org.hibernate:hibernate-core:6.2.12.Final")

    implementation ("com.h2database:h2:2.2.224")

    implementation ("org.flywaydb:flyway-core:9.21.2")

    implementation ("jakarta.validation:jakarta.validation-api:3.0.2")

    implementation ("javax.persistence:javax.persistence-api:2.2")

    implementation ("org.hibernate:hibernate-core:5.6.9.Final")

    runtimeOnly ("com.h2database:h2")

}

tasks.test {
    useJUnitPlatform()
}
