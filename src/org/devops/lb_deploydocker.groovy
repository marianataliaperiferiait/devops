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


 stage("OWASP Analysis"){
            steps{
                script{
                    def networkName = 'jenkinsowasp'
                    def imageName = "owasp/zap2docker-stable"
                    def zapContainerName = 'owaspimagen-container'
                    def imageExist = sh(returnStdout: true, script: "docker images -q $imageName").trim()
                    if (imageExist.isEmpty()) {
                        sh "docker pull $imageName"
                    }else {
                        echo " La imagen ${imageName} ya existe. Descartando descarga.."
                    }
                    sh "docker stop ${zapContainerName} || true"
                    sh "docker rm ${zapContainerName} || true"
                    sh "docker run -d --name ${zapContainerName} --network=${networkName} ${imageName}"
                    def dockerHubUserName = 'sanchezmnperiferia'
                    def crudspringbootImageName = 'crudspringboot-buildimagen'
                    def crudspringbootContainerName = 'owaspanalysis-container'
                    sh "docker stop ${crudspringbootContainerName} || true"
                    sh "docker rm ${crudspringbootContainerName} || true"
                    sh "docker run -d --name ${crudspringbootContainerName} --network=${networkName} -p 3500:8080 --user root ${dockerHubUserName}/${crudspringbootImageName}"
                    def targetURL = "http://${env.iphost}:3500"
                    sh "docker run --rm -v fullscancrudspringboot:/zap/wrk/:rw --user root --network=${networkName} -t $imageName zap-full-scan.py -t ${targetURL} -r fullscancrudspringboot.html -I"
                    sh "docker stop ${zapContainerName}"
                    sh "docker rm ${zapContainerName}"
                    sh "docker stop ${crudspringbootContainerName}"
                    sh "docker rm ${crudspringbootContainerName}"
                }
                 }
             }                                           
    }
}