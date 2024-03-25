def call (){

pipeline{
    agent any
    tools {
    jdk 'jdk11'
    maven 'maven3'
    }
    stages{
        stage("Fase 1: Contruccion Applicacion"){
            steps {
                script {
                    def cloneapp = new org.devops.lb_buildartefacto()
                    cloneapp.clone
                    def aplicationapp = new org.devops.lb_buildartefacto()
                    aplicationapp.aplication
                    def artefactapp = new org.devops.lb_buildartefacto()
                    artefactapp.artefact                                      
                }
            }
        }             
        stage("Fase 2: Scanner Sonnarqube") {
        }             
            // steps {
            //     script {
            //         def analisissonar = new org.devops.lb_analisissonarqube()
            //         analisissonar .clone
                                 
            //     }
            // }
        }                                     
    }
 }
