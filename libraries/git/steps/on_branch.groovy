package libraries.git.steps

void call(Map args = [:], body){
    def branch = env.BRANCH_NAME

    if(args.to)
    if(!(branch ==~ args.to))
        return

    println "Running because of branch ${branch}"
    body()
}