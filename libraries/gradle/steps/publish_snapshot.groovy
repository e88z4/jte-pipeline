package libraries.gradle.steps
import gradle.config.Default

void call(){
    def properties = Default.properties
    def options = Default.options
    def publish_tasks = Default.publish_tasks
    def publish_repository_snapshot = Default.publish_repository_snapshot
    def publish_repository_url = Default.publish_repository_url
    def publish_repository_credential = "Artifactory " + env.SLF_JENKINS_ENVIRONMENT

    if(config.publish != null){
        if(config.publish.snapshot != null){
            if(config.publish.snapshot.tasks != null){
                publish_tasks = config.publish.snapshot.tasks
            }

            if(config.publish.snapshot.options != null){
                options = config.publish.snapshot.options
            }

            if(config.publish.snapshot.properties != null){
                properties = config.publish.snapshot.properties
            }

            if(config.publish.snapshot.repository != null){
                publish_repository_snapshot = config.publish.snapshot.repository
            }

            if(config.publish.snapshot.credential != null){
                if(config.publish.snapshot.credential == "Artifactory Dev"){
                    publish_repository_credential = "dev-artifactory"
                }

                if(config.publish.snapshot.credential == "Artifactory Stage"){
                    publish_repository_credential = "stage-artifactory"
                }

                if(config.publish.snapshot.credential == "Artifactory Prod"){
                    publish_repository_credential = "Artifactory"
                }
            }
        }
    }

    stage("gradle: publish snapshot"){
        withCredentials([usernamePassword(credentialsId: publish_repository_credential, usernameVariable: "ci_publish_username", passwordVariable: "ci_publish_password")]){
            sh '''#!/bin/bash -xe
                JAVA='''+options+''' \
                ./gradlew '''+publish_tasks+''' '''+properties

        }
    }
}