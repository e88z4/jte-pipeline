package libraries.maven.steps
import maven.config.Default

void call(){
    def properties = Default.properties
    def options = Default.options
    def sonarqube_goals = Default.sonar_goals
    def target = "SonarQube " + env.SLF_JENKINS_ENVIRONMENT
    def maven = Default.maven_binary

    if(config.use_maven_wrapper != null){
        maven = config.use_maven_wrapper? Default.maven_wrapper : Default.maven_binary
    }

    if(config.sonar != null){
        if(config.sonar.goals != null){
            sonarqube_goals = config.sonar.goals
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
        '''+maven+''' '''+sonarqube_goals+''' '''+properties
    }
}