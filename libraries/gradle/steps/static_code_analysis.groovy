package libraries.gradle.steps
import gradle.config.Default

void call(Map args = [:]){
    def properties = Default.properties
    def options = Default.options
    def sonarqube_tasks = Default.sonar_tasks

    if(config.sonar != null){
        if(config.sonar.tasks != null){
            sonarqube_tasks = config.sonar.tasks
        }
        else{
            println "No static_code_analysis task(s) are defined"
            println "Skipping static_code_analysis stage"
            return
        }

        if(config.sonar.options != null){
            options = config.sonar.options
        }

        if(config.sonar.properties != null){
            properties = config.sonar.properties
        }
    }

    args.each{
        properties = properties + " -P" + it.key + "=\"" + it.value
    }

    stage("Sonarqube: Static Code Analysis"){
        sh '''#!/bin/bash -xe
        JAVA='''+options+''' \
        ./gradlew '''+sonarqube_tasks+''' '''+properties
    }
}