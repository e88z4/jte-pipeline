package libraries.maven.steps
import maven.config.Default

void call(String release_scope){
    def properties = Default.properties
    def options = Default.options
    def publish_goals = Default.publish_goals
    def publish_repository_release = Default.publish_repository_release
    def publish_repository_url = Default.publish_repository_url
    def publish_repository_credential = "Artifactory " + env.SLF_JENKINS_ENVIRONMENT

    if(config.publish != null){
        if(config.publish.release != null){
            if(config.publish.release.goals != null){
                publish_goals = config.publish.release.goals
            }

            if(config.publish.release.options != null){
                options = config.publish.release.options
            }

            if(config.publish.release.properties != null){
                properties = config.publish.release.properties
            }

            if(config.publish.release.repository != null){
                publish_repository_release = config.publish.release.repository
            }

            if(config.publish.release.credential != null){
                if(config.publish.release.credential == "Artifactory Dev"){
                    publish_repository_credential = "dev-artifactory"
                }

                if(config.publish.release.credential == "Artifactory Stage"){
                    publish_repository_credential = "stage-artifactory"
                }

                if(config.publish.release.credential == "Artifactory Prod"){
                    publish_repository_credential = "Artifactory"
                }
            }
        }
    }

    stage("gradle: publish release"){
        withCredentials([usernamePassword(credentialsId: publish_repository_credential, usernameVariable: "ci_publish_username", passwordVariable: "ci_publish_password")]){
            sh '''#!/bin/bash -xe
            JAVA='''+options+''' \
            '''+maven+''' '''+publish_goals+''' '''+properties
        }
    }
}