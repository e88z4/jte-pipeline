package libraries.utility.steps

String call(){
    def release_scope_options = ['minor','major','patch']
    def release_scope = ''

    try{
        timeout(time: 180, unit: 'SECONDS'){
            release_scope = input(
                message: "What is the release scope?",
                parameters: [
                    choice(name: 'release_scope', choices: release_scope_options, description: 'release scope of the build')
                ]
            )

            echo "Release scope is [${release_scope}]"
        }
    }
    catch(err){
        def user = err.getCauses()[0].getUser()
        if('SYSTEM' == user.toString()){
            currentBuild.result = 'FAILURE'
            echo "no input was received before timeout"
        }
        else{
            currentBuild.result = 'ABORTED'
            echo "Aborted by: [${user}]"
        }

        throw err
    }

    return release_scope
}