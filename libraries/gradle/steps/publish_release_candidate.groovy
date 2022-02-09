package libraries.gradle.steps
import gradle.config.Default

void call(Map args = [:]){
    def properties = Default.properties
    def options = Default.options
    def publish_tasks = Default.publish_tasks

    if(config.publish != null){
        if(config.publish.release_candidate != null){
            if(config.publish.release_candidate.tasks != null){
                publish_tasks = config.publish.release_candidate.tasks
            }
            else{
                println "No publish_release_candidate task(s) are defined"
                println "Skipping publish_release_candidate stage"
                return
            }

            if(config.publish.release_candidate.options != null){
                options = config.publish.release_candidate.options
            }

            if(config.publish.release_candidate.properties != null){
                properties = config.publish.release_candidate.properties
            }
        }
    }

    args.each{
        properties = properties + " -P" + it.key + "=\"" + it.value
    }

    stage("gradle: publish release candidate"){        
        sh '''#!/bin/bash -xe
            JAVA='''+options+''' \
            ./gradlew '''+publish_tasks+''' '''+properties        
    }
}