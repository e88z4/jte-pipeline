package kubernetes.yamlPatch

public void create(){
    return '''
        apiVersion: v1
        kind: Pod
        spec:
          imagePullSecrets:
          - name: "dtr-secret"
          containers:
          - name: "image-build-sdk"
            env:
            - name: "DOCKER_CONTENT_TRUST_ROOT_PASSPHRASE"
              valueFrom:
                secretKeyRef:
                  key: "DOCKER_CONTENT_TRUST_ROOT_PASSPHRASE"
                  name: "dct-secret"
            - name: "DOCKER_CONTENT_TRUST_REPOSITORY_PASSPHRASE"
              valueFrom:
                secretKeyRef:
                  key: "DOCKER_CONTENT_TRUST_REPOSITORY_PASSPHRASE"
                  name: "dct-secret"
            - name: "CONTAINER_SUPPORT_SERVICE_URL"
              valueFrom:
                configMapKeyRef:
                  key: "CONTAINER_SUPPORT_SERVICE_URL"
                  name: "docker-image-build-configmap"
            - name: "DOCKER_LINTER_IMAGE"
              valueFrom:
                configMapKeyRef:
                  key: "DOCKER_LINTER_IMAGE"
                  name: "docker-image-build-configmap"
            - name: "DOCKER_CONTENT_TRUST_SIGNER_PUB_KEY"
              valueFrom:
                configMapKeyRef:
                  key: "DOCKER_CONTENT_TRUST_SIGNER_PUB_KEY"
                  name: "docker-image-build-configmap"
            - name: "DOCKER_CONFIG"
              value: "/dibv/.docker"
            securityContext:
              privileged: true
            volumeMounts:
            - mountPath: "/dibv"
              name: "docker-image-build-volume"
            - mountPath: "/signer-keys"
              name: "signer-pub-key-volume"
            - mountPath: "/dibv/.docker/trust/private/7e68336846014199f8dfabe09067b7933a7bef634ea74a8154c374f8da43d8a3.key"
              name: "dct-keys-volume"
              subPath: "7e68336846014199f8dfabe09067b7933a7bef634ea74a8154c374f8da43d8a3.key"
            - mountPath: "/dibv/.docker/trust/private/e0b2c3df2a30dbde2d99dd4752f50c45465dfc4175dc87c7bf45525c370a290c.key"
              name: "dct-keys-volume"
              subPath: "e0b2c3df2a30dbde2d99dd4752f50c45465dfc4175dc87c7bf45525c370a290c.key"
            - mountPath: "/etc/ucp-config.json"
              name: "ucp-config-secret-volume"
              readOnly: true
              subPath: "ucp-config.json"
          volumes:
          - name: "docker-image-build-volume"
            persistentVolumeClaim:
              claimName: "docker-image-build-pvc"
          - configMap:
              name: "signer-pub-key"
            name: "signer-pub-key-volume"
          - secret:
              defaultMode: 384
              secretName: "dct-keys-secret"
            name: "dct-keys-volume"
          - secret:
              secretName: "ucp-config-secret"
            name: "ucp-config-secret-volume"
    '''
}

