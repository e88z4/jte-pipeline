package container.template

public void create(config){
    return containerTemplate(
        name: 'jnlp',
        image: 'prod-dtr-ca.sunlifecorp.com/devopstoolchain/jenkins-inbound-agent:4.7-1',
        alwaysPullImage: true,
        resourceRequestCpu: "300m",
        resourceRequestMemory: "256Mi",
        resourceLimitCpu: "600m",
        resourceLimitMemory: "512Mi"
    )
}