@merge libraries{
    git
    jenkins
    utility
    pod_template{
        @override cloud = "default-cloud"
        build_agent{
            sdk{
                @override image = "maven:3.8.4-openjdk-11"
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
    branch_build = /^([Dd]evelop(ment|er|))|([Hh]otfix\/.*)|([Ff]eature\/.*)|([Pp][Rr]-.*)|([Rr]elease\/.*)|([Mm](aster|ain))$/
    release_candidate_tag_build = /^(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)(?:-rc\.(0|[1-9]\d*))$/
    release_tag_build =  /^(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)$/
}