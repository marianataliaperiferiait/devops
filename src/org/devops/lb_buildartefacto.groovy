package org.devops

def clone(){
    git url: "https://github.com/marianataliaperiferiait/SpringBoot.git"
}

def aplication(){
    sh 'mvn clean package'
    sh 'mvn compile'
}

def artefact() {
    sh 'mvn package'
    junit 'target/surefire-reports/**/*.xml'
    archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false
}