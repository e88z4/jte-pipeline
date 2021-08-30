fields{
    required{
        cloud = String
    }

    optional{
        build_agent{
            sdk{
                image = String
                resource{
                    cpu{
                        request_mcore = ~/^([1-9][0-9]{0,2}|1[0-9]{3}|2000)$/
                        limit_mcore = ~/^([1-9][0-9]{0,2}|[1-3][0-9]{3}|4000)$/
                    }
                    memory{
                        request_mbyte = ~/^([1-9][0-9]{0,2}|[1-3][0-9]{3}|4000)$/
                        limit_mbyte = ~/^([1-9][0-9]{0,2}|[1-7][0-9]{3}|8000)$/
                    }
                }
            }

            docker_build{
                enabled = Boolean
            }
        }
    }
}