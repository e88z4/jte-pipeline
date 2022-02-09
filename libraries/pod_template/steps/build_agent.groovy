package libraries.pod_template.steps

import container.template.JnlpContainer
import container.template.SdkContainer
import kubernetes.Namespace
import kubernetes.Cloud

void call(Closure body){
    def cloud = 'my-jenkins-cloud'
    def containers = []
    def volumes = []
    def namespace = 'my-kubernetes-build-agent-namespace'
    def yamlPatch = ''
    def nodeSelector = ['my-company.com/node.name=server123',
                        'my-company.com/node.cloud=aws'
                        'my-company.com/node.region=us-east-1',
                        'my-company.com/node.zone=use-east-1a']

    containers << (new JnlpContainer()).create(config)

    if(config.build_agent != null){
        if(config.build_agent.sdk != null){
            containers << (new SdkContainer()).create(config)
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