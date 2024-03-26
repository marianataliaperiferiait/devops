package org.devops

def analisissonar(){
    def scannerHome = tool 'SonarqubeScanner'
        withSonarQubeEnv('ServerSonarqube') {
        sh "${scannerHome}/bin/sonar-scanner \
        -Dsonar.projectKey=analisisSpringBoot \
        -Dsonar.projectName=analisisSpringBoot \
        -Dsonar.sources=src \
        -Dsonar.java.binaries=target/classes"
    }        
}
