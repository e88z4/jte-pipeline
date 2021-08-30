package libraries.gradle.steps
import gradle.config.Default

void call(){
    def properties = Default.properties
    def options = Default.options

    if(config.build.options != null){
        options = config.build.options
    }

    if(config.build.properties != null){
        properties = config.build.properties
    }

    stage('gradle: build'){
        sh '''#!/bin/bash -xe
            JAVA='''+options+''' \
            ./gradlew '''+config.build.tasks+''' '''+properties
    }
}