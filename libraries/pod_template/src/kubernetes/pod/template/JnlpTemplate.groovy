package kubernetes.pod.template

public void create(config, body){
    podTemplate(
        cloud: "${config.cloud}",
        containers: [
            containerTemplate(
                name: 'jnlp',
                image: 'prod-dtr-ca.sunlifecorp.com/devopstoolchain/jenkins-inbound-agent:4.7-1-alpine',
                alwaysPullImage: true,
                resourceRequestCpu: "300m",
                resourceRequestMemory: "256Mi",
                resourceLimitCpu: "600m",
                resourceLimitMemory: "512Mi"
            )
        ]
    ){
        body.call()
    }
}

return this