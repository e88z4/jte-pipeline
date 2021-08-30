package libraries.pod_template.steps

import container.template.JnlpContainer
import container.template.SdkContainer
import container.template.DockerBuildContainer
import kubernetes.pod.volume.DockerBuildVolume
import kubernetes.Namespace
import kubernetes.Cloud
import kubernetes.pod.yamlPatch.DockerBuildContainerPatch

void call(Closure body){
    def cloud = config.cloud
    def containers = []
    def volumes = []
    def namespace = env.SLF_JENKINS_ENVIRONMENT == 'Prod'? Namespace.prod_jenkinsagents_ns : Namespace.stage_jenkinsagents_ns
    def yamlPatch = ''
    def nodeSelector = ['sunlife.com/node.licensing.none=true',
                        'sunlife.com/node.network=corp',
                        'sunlife.com/node.sdlc.prod=true']

    containers << (new JnlpContainer()).create(config)

    if(config.build_agent != null){
        if(config.build_agent.sdk != null){
            containers << (new SdkContainer()).create(config)
        }

        if(config.build_agent.docker_build != null){
            if(config.build_agent.docker_build.enabled){
                containers << (new DockerBuildContainer().create(config))
                namespace = Namespace.prod_imagebuilderpipeline_ns
                cloud = Cloud.prod_whs_cloud
                yamlPatch = (new DockerBuildContainerPatch()).create()
            }
        }
    }

    podTemplate(
        cloud: cloud,
        containers: containers,
        volumes: volumes.flatten(),
        nodeSelector: nodeSelector.join(','),
        namespace: namespace,
        yaml: yamlPatch,
        yamlMergeStrategy: merge()
    ){
        node(POD_LABEL, body)
    }
}