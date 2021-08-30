fields{
    required{
        build{
            goals = String
        }
    }
    optional{
        use_wrapper_wrapper = Boolean
        build{
            options = String
            properties = String
        }

        sonar{
            goals = String
            target = ["SonarQube Dev","SonarQube Stage", "SonarQube Prod"]
            options = String
            properties = String
        }

        publish{
            snapshot{
                goals = String
                options = String
                properties = String
                credential = ["Artifactory Dev", "Artifactory Stage", "Artifactory Prod"]
            }

            release_candidate{
                goals = String
                options = String
                properties = String
                credential = ["Artifactory Dev", "Artifactory Stage", "Artifactory Prod"]
            }

            release{
                goals = String
                options = String
                properties = String
                credential = ["Artifactory Dev", "Artifactory Stage", "Artifactory Prod"]
            }
        }
    }
}