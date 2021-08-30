package libraries.gradle.steps
import gradle.config.Default

void call(String release_scope){
    def properties = Default.properties
    def options = Default.options
    def publish_tasks = Default.publish_tasks
    def publish_repository_release_candidate = Default.publish_repository_release_candidate
    def publish_repository_url = Default.publish_repository_url
    def publish_repository_credential = "Artifactory " + env.SLF_JENKINS_ENVIRONMENT

    if(config.publish != null){
        if(config.publish.release_candidate != null){
            if(config.publish.release_candidate.tasks != null){
                publish_tasks = config.publish.release_candidate.tasks
            }

            if(config.publish.release_candidate.options != null){
                options = config.publish.release_candidate.options
            }

            if(config.publish.release_candidate.properties != null){
                properties = config.publish.release_candidate.properties
            }

            if(config.publish.release_candidate.repository != null){
                publish_repository_release_candidate = config.publish.release_candidate.repository
            }

            if(config.publish.release_candidate.credential != null){
                if(config.publish.release_candidate.credential == "Artifactory Dev"){
                    publish_repository_credential = "dev-artifactory"
                }

                if(config.publish.release_candidate.credential == "Artifactory Stage"){
                    publish_repository_credential = "stage-artifactory"
                }

                if(config.publish.release_candidate.credential == "Artifactory Prod"){
                    publish_repository_credential = "Artifactory"
                }
            }
        }
    }

    stage("gradle: publish release candidate"){
        withCredentials([usernamePassword(credentialsId: publish_repository_credential, usernameVariable: "ci_publish_username", passwordVariable: "ci_publish_password")]){
            sh '''#!/bin/bash -xe
                JAVA='''+options+''' \
                ./gradlew '''+publish_tasks+''' '''+properties
        }
    }
}