package libraries.gradle.steps
import gradle.config.Default

void call(Map args = [:]){
    def properties = Default.properties
    def options = Default.options
    def publish_tasks = Default.publish_tasks

    if(config.publish != null){
        if(config.publish.release != null){
            if(config.publish.release.tasks != null){
                publish_tasks = config.publish.release.tasks
            }
            else{
                println "No publish_release task(s) are defined"
                println "Skipping publish_release stage"
                return
            }

            if(config.publish.release.options != null){
                options = config.publish.release.options
            }

            if(config.publish.release.properties != null){
                properties = config.publish.release.properties
            }
        }
    }

    args.each{
        properties = properties + " -P" + it.key + "=\"" + it.value
    }

    stage("gradle: publish release"){
        sh '''#!/bin/bash -xe
            JAVA='''+options+''' \
            ./gradlew '''+publish_tasks+''' '''+properties
    }
}