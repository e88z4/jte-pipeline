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
            }

            release_candidate{
                tasks = String
                options = String
                properties = String
            }

            release{
                tasks = String
                options = String
                properties = String
            }
        }
    }
}