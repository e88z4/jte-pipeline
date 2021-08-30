package libraries.jenkins.steps

void call(Map args, Closure body){

  // check required parameters
  if (!args.name)
    if (!args.location && !args.variable)
      error """
      withConfigFileProvider syntax error.
      Input Parameters:
        name: config file name (required)
        location: target config location. (optional, either location or variable must exist)
        variable: target environment variable (optional, either location or variable must exist)
      """

  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = this

  if(args.location && args.variable){
    configFileProvider([configFile(fileId: args.name, targetLocation: args.location, variable: args.variable)]) {
      body.run()
    }
  }

  if(args.location){
    configFileProvider([configFile(fileId: args.name, targetLocation: args.location)]) {
      body.run()
    }
  }

  if(args.variable){
    configFileProvider([configFile(fileId: args.name, variable: args.variable)]) {
      body.run()
    }
  }
}
