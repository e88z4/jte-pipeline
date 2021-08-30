jte{
    pipeline_template = "java_build"
}

@merge libraries{
    git
    jenkins
    utility
    pod_template{
        @override cloud = "prod-ucp-ca--prod-devops-jenkinsagents-ns"
        build_agent{
            sdk{
                @override image = "prod-dtr-ca.sunlifecorp.com/devopstoolchain/maven:3.8.1-openjdk-8"
                @override resource{
                    cpu{
                        request_mcore = "750"
                        limit_mcore = "1500"
                    }
                    memory{
                        request_mbyte = "2000"
                        limit_mbyte = "4000"
                    }
                }
            }

            docker_build{
                @override enabled = false
            }
        }
    }
}

keywords{
    development =  /^([Dd]evelop(ment|er|))|([Hh]otfix\/.*)|([Ff]eature\/.*)|([Pp][Rr]-.*)$/
    release =  /^[Rr]elease\/.*$/
    main    =  /^[Mm](aster|ain)$/
}