package org.devops

def owaspAnalysis(String iphost, String dockerHubUsername, String springbootImageName, String networkName) {
    script {
        def imageName = "owasp/zap2docker-stable"
        def zapContainerName = 'owaspimagen-container'
        
        // Pull the OWASP ZAP Docker image if it does not exist
        def imageExists = sh(script: "docker images -q ${imageName}", returnStatus: true)
        if (imageExists != 0) {
            sh "docker pull ${imageName}"
        } else {
            echo "La imagen ${imageName} ya existe. Descartando descarga."
        }
        
        // Start OWASP ZAP Docker container
        sh "docker stop ${zapContainerName} || true"
        sh "docker rm ${zapContainerName} || true"
        sh "docker run -d --name ${zapContainerName} --network=${networkName} ${imageName}"
        
        // Start the Spring Boot application Docker container
        def springbootContainerName = 'owaspanalysis-container'
        sh "docker stop ${springbootContainerName} || true"
        sh "docker rm ${springbootContainerName} || true"
        sh "docker run -d --name ${springbootContainerName} --network=${networkName} -p 3500:8080 --user root ${dockerHubUsername}/${springbootImageName}"
        
        // Perform OWASP ZAP full scan
        def targetURL = "http://${iphost}:3500"
        sh "docker run --rm -v fullscanspringboot:/zap/wrk/:rw --user root --network=${networkName} -t ${imageName} zap-full-scan.py -t ${targetURL} -r fullscanspringboot.html -I"
        
        // Stop and remove Docker containers
        sh "docker stop ${zapContainerName}"
        sh "docker rm ${zapContainerName}"
        sh "docker stop ${springbootContainerName}"
        sh "docker rm ${springbootContainerName}"
    }
}
