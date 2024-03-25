def buildService = new org.devops.lb_buildartefacto()
def sonarService = new org.devops.lb_analisissonarqube()

pipeline {
    agent any
    tools {
        jdk 'jdk11'
        maven 'maven3'
    }
    stages {
        stage("Fase 1: Construcción Aplicación") {
            steps {
                script {
                    buildService.clone()
                    buildService.aplicacion()
                    buildService.artefact()
                }
            }
        }
        stage("Fase 2: Análisis SonarQube") {
            steps {
                script {
                    sonarService.analisissonar()
                }
            }
        }
    }
}
