package libraries.gradle.steps
import gradle.config.Default

void call(Map args = [:]){
    def properties = Default.properties
    def options = Default.options
    def publish_tasks = Default.publish_tasks

    if(config.publish != null){
        if(config.publish.snapshot != null){
            if(config.publish.snapshot.tasks != null){
                publish_tasks = config.publish.snapshot.tasks
            }
            else{
                println "No publish_snapshot task(s) are defined"
                println "Skipping publish_snapshot stage"
                return
            }

            if(config.publish.snapshot.options != null){
                options = config.publish.snapshot.options
            }

            if(config.publish.snapshot.properties != null){
                properties = config.publish.snapshot.properties
            }
        }
    }

    args.each{
        properties = properties + " -P" + it.key + "=\"" + it.value
    }

    stage("gradle: publish snapshot"){
        sh '''#!/bin/bash -xe
            JAVA='''+options+''' \
            ./gradlew '''+publish_tasks+''' '''+properties
    }
}