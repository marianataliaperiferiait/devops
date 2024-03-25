    def call (scmUri){

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
                        lb_buildartefacto.clone()
                        lb_buildartefacto.aplication()
                        lb_buildartefacto.artefact()                                   
                    }
                }
            }             
            stage("Fase 2: Scanner Sonnarqube") {
                steps {
                script {
                    def analisissonar = new org.devops.lb_analisissonarqube()
                    analisissonar.analisissonar
                                    
                    }
                }
            }             

            }                                     
        }
    }
