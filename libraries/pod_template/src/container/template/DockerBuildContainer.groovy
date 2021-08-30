package container.template

public void create(config){
    return containerTemplate(
        name: 'image-build-sdk',
        image: 'prod-dtr-ca.sunlifecorp.com/whs/docker-image-build:1.16.0',
        alwaysPullImage: true,
        ttyEnabled: true,
        resourceRequestCpu: "400m",
        resourceRequestMemory: "1024Mi",
        resourceLimitCpu: "800m",
        resourceLimitMemory: "2048Mi"
    )
}