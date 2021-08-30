package libraries.jenkins.steps

void call(Map args, Closure body){

    // check required parameters
    if (!args.credential_id || !args.username_variable || !args.password_variable)
        error """
        withUserPassCredential syntax error.
        Input Parameters:
        credential_id: config file name (required)
        username_variable: target username variable (required)
        password_variable: target password variable (required)
        """

    withCredentials([usernamePassword(credentialsId: args.credential_id, passwordVariable: args.password_variable, usernameVariable: args.username_variable)]) {
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = this
        body.run()
    }
}