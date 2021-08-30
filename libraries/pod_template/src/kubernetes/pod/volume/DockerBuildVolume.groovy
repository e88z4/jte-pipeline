package kubernetes.pod.volume

public void create(config){
    volumes = []
    volumes << persistentVolumeClaim(mountPath: '/divb', claimName: 'docker-image-build-pvc')
    volumes << configMapVolume(mountPath: '/signer-keys', configMapName: 'signer-pub-key')
    volumes << secretVolume(mountPath: '/dibv/.docker/trust/private/', secretName: 'dct-keys-secret')
    volumes << secretVolume(mountPath: '/etc/ucp-config.json', secretName: 'ucp-config-secret')

    return volumes
}