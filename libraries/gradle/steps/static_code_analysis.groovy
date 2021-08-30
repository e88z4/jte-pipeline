package libraries.gradle.steps
import gradle.config.Default

void call(){
    def properties = Default.properties
    def options = Default.options
    def sonarqube_tasks = Default.sonar_tasks
    def target = "SonarQube " + env.SLF_JENKINS_ENVIRONMENT

    if(config.sonar != null){
        if(config.sonar.tasks != null){
            sonarqube_tasks = config.sonar.tasks
        }

        if(config.sonar.options != null){
            options = config.sonar.options
        }

        if(config.sonar.properties != null){
            properties = config.sonar.properties
        }
    }

    stage("Sonarqube: Static Code Analysis"){
        sh '''#!/bin/bash -xe
        JAVA='''+options+''' \
        ./gradlew '''+sonarqube_tasks+''' '''+properties
    }
}