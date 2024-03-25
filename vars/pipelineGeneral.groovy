def call (){
pipeline {
    agent any
    stages {
        stage("Build and Package") {
            steps {
                script {
                    def buildScript = load "src/org/devops/lb_buildartefacto.groovy"
                    buildScript.clone()
                    buildScript.aplication()
                    buildScript.artefact()
                }
            }
        }
        stage("SonarQube Analysis") {
            steps {
                script {
                    def sonarScript = load "src/org/devops/lb_analisissonarqube.groovy"
                    sonarScript.analisissonar()
                }
            }
        }
    }
}
}
