package org.devops

def publishImage(String dockerHubUsername, String dockerHubTokenCredentialId) {
    def imageExists = sh(script: "docker images -q ${dockerHubUsername}/springboot-buildimagen", returnStdout: true).trim()

    // Print information for debugging
    echo "Image Exists: ${imageExists}"

    if (imageExists) {
        echo "La imagen ${dockerHubUsername}/springboot-buildimagen ya existe en Docker Hub. Descartando publicación ...."
    } else {
        withCredentials([
            usernamePassword(
                credentialsId: dockerHubTokenCredentialId,
                passwordVariable: 'DOCKERHUB_PASSWORD',
                usernameVariable: 'DOCKERHUB_USERNAME'
            )
        ]) {
            sh "docker login --username ${env.DOCKERHUB_USERNAME} --password ${env.DOCKERHUB_PASSWORD}"
        }
        sh "docker tag springboot-buildimagen ${dockerHubUsername}/springboot-buildimagen"
        sh "docker push ${dockerHubUsername}/springboot-buildimagen"
    }
}
