package libraries.jenkins.steps

void call(Map args, Closure body){

    // check required parameters
    if(!args.target)
        error """
        withSonarQubeEnvironment syntax error.
        Input Parameters:
        target: SonarQube installation name in Jenkins (required)
        """

    withSonarQubeEnv(args.target){
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = this
        body.run()
    }
}
