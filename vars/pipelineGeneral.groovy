    def call (scmUri){
    pipeline{
        agent any
        // tools {
        // jdk 'jdk11'
        // maven 'maven3'
        // }
        stages{
            stage("Fase 1: Contruccion Applicacion"){
                steps {
                    script {
                        def clone = new org.devops.lb_buildartefacto()
                        clone.clone()
                        def aplication = new org.devops.lb_buildartefacto()
                        aplication.aplication()
                        def artefact = new org.devops.lb_buildartefacto()
                        artefact.artefact()                                     
                    }
                }
            }             
            stage("Fase 2: Scanner Sonnarqube") {
                steps {
                script {
                    def analisissonar = new org.devops.lb_analisissonarqube()
                    analisissonar.analisissonar()
                                    
                    }
                }
            }             

            }                                     
        }
    }
