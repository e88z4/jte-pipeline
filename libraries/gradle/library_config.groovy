fields{
    required{
        build{
            tasks = String
        }
    }
    optional{
        build{
            options = String
            properties = String
        }

        sonar{
            tasks = String
            options = String
            properties = String
        }

        publish{
            snapshot{
                tasks = String
                options = String
                properties = String
                credential = ["Artifactory Dev", "Artifactory Stage", "Artifactory Prod"]
            }

            release_candidate{
                tasks = String
                options = String
                properties = String
                credential = ["Artifactory Dev", "Artifactory Stage", "Artifactory Prod"]
            }

            release{
                tasks = String
                options = String
                properties = String
                credential = ["Artifactory Dev", "Artifactory Stage", "Artifactory Prod"]
            }
        }
    }
}