package container.template

public void create(config){
    return containerTemplate(
        name: 'sdk',
        image: "${config.build_agent.sdk.image}",
        alwaysPullImage: true,
        ttyEnabled: true,
        command: 'cat',
        resourceRequestCpu: "${config.build_agent.sdk.resource.cpu.request_mcore}m",
        resourceRequestMemory: "${config.build_agent.sdk.resource.memory.request_mbyte}Mi",
        resourceLimitCpu: "${config.build_agent.sdk.resource.cpu.limit_mcore}m",
        resourceLimitMemory: "${config.build_agent.sdk.resource.memory.limit_mbyte}Mi"
    )
}