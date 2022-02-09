package libraries.git.steps

void call(Map args = [:], body){
    def tag = env.TAG_NAME

    if(args.to)
    if(!(tag ==~ args.to))
        return

    println "Running because of tag ${tag}"
    body()
}