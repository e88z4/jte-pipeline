package maven.config

class Default{
    static String options = '-Xmx1g'
    static String properties = '-Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=info'
    static String sonar_goals = "sonar:sonar"
    static String publish_goals = "publish"
    static String publish_repository_snapshot = "libs-snapshot-local"
    static String publish_repository_release_candidate = "libs-staging-local"
    static String publish_repository_release = "libs-staging-local"
    static String publish_repository_url = "https://artifactory.sunlifecorp.com/artifactory"
    static String publish_repository_credential = "Artifactory"
    static String default_parameters = "--batch-mode"
    static String maven_binary = "mvn ${default_parameters}"
    static String maven_wrapper = "./mvnw ${default_parameters}"
}