package libraries.git.steps

void call(Map args, Closure body){

  // check required parameters
  if (!args.url || !args.cred)
    error """
    withGit syntax error.
    Input Parameters:
      url: ssh git url to repository (required)
      cred: jenkins credential ID for BitBucket. (required)
      branch: branch in the repository to checkout. defaults to master. (optional)
    """

  sshagent(args.cred){
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = this
    body.run()
  }
}
