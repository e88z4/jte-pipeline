package container.template

public void create(config){
    return containerTemplate(
        name: 'jnlp',
        image: 'jenkins-inbound-agent:latest',
        alwaysPullImage: true,
        resourceRequestCpu: "300m",
        resourceRequestMemory: "256Mi",
        resourceLimitCpu: "600m",
        resourceLimitMemory: "512Mi"
    )
}