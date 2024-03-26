package org.devops

def buildImage(){
    def imageExists = sh(script: "docker images -q springboot-buildimagen", returnStdout: true).trim()
    if (imageExists){
        echo "La imagen springboot-buildimagen ya existe. Descantando construcción...."
    } else {
        sh 'docker build -t springboot-buildimagen -f Dockerfile .'
    }
}
