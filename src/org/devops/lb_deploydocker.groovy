package org.devops

def runContainer(String dockerHubUsername, String imageName, String containerName) {
    def containerExists = sh(script: "docker ps -a --filter name=${containerName} --format {{.Names}}", returnStdout:true).trim()
    
    if (containerExists) {
        echo "El contenedor ${containerName} ya existe. Descartando Creación..."
    } else {
        sh "docker stop ${containerName} || true"
        sh "docker rm ${containerName} || true"
        sh "docker run -d -p 3000:8080 --name ${containerName} ${dockerHubUsername}/${imageName}"
    }
}
