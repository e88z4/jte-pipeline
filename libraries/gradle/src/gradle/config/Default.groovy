package gradle.config

class Default{
    static String options = '-Xmx1g'
    static String properties = ''
    static String sonar_tasks = "sonarqube"
    static String publish_tasks = "publish"
    static String publish_repository_snapshot = "libs-snapshot-local"
    static String publish_repository_release_candidate = "libs-staging-local"
    static String publish_repository_release = "libs-staging-local"
    static String publish_repository_url = "https://artifactory.sunlifecorp.com/artifactory"
    static String publish_repository_credential = "Artifactory"
}