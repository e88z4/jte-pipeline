package libraries.maven.steps
import maven.config.Default

void call(){
    def properties = Default.properties
    def options = Default.options
    def publish_goals = Default.publish_goals
    def publish_repository_snapshot = Default.publish_repository_snapshot
    def publish_repository_url = Default.publish_repository_url
    def publish_repository_credential = "Artifactory " + env.SLF_JENKINS_ENVIRONMENT
    def maven = Default.maven_binary

    if(config.use_maven_wrapper != null){
        maven = config.use_maven_wrapper? Default.maven_wrapper : Default.maven_binary
    }

    if(config.publish != null){
        if(config.publish.snapshot != null){
            if(config.publish.snapshot.goals != null){
                publish_goals = config.publish.snapshot.goals
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

    stage("maven: publish snapshot"){
        withCredentials([usernamePassword(credentialsId: publish_repository_credential, usernameVariable: "ci_publish_username", passwordVariable: "ci_publish_password")]){
            sh '''#!/bin/bash -xe
            JAVA='''+options+''' \
            '''+maven+''' '''+publish_goals+''' '''+properties

        }
    }
}