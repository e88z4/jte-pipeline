package libraries.docker.steps

void call(){
    stage("docker: image-build"){
        def default_build_options = '--no-cache --pull --advisory-check --sign --push'
        def build_args = config.build_args != null? config.build_args : " "
        def build_context = config.build_context =! null? '--build-context '+config.build_context : " "

        withCredentials([usernamePassword(credentialsId: config.credential_id, usernameVariable: "ci_publish_username", passwordVariable: "ci_publish_password")]){
                sh '''#!/bin/bash -xe
                    /scripts/image-build.sh '''+default_build_options+''' \
                    --user '''+ci_publish_username+''' \
                    --password '''+ci_publish_password+''' \
                    --tag '''+config.tag+''' \
                    --dockerfile '''+config.dockerfile
        }
    }
}