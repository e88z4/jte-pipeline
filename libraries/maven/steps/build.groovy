package libraries.maven.steps
import maven.config.Default

void call(){
    def properties = Default.properties
    def options = Default.options
    def maven = Default.maven_binary

    if(config.use_maven_wrapper != null){
        maven = config.use_maven_wrapper? Default.maven_wrapper : Default.maven_binary
    }

    if(config.build.options != null){
        options = config.build.options
    }

    if(config.build.properties != null){
        properties = config.build.properties
    }

    stage('maven: build'){
        sh '''#!/bin/bash -xe
            JAVA='''+options+''' \
            '''+maven+''' '''+config.build.goals+''' '''+properties
    }
}