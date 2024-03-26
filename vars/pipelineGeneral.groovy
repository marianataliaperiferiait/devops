    def call (scmUri){
    pipeline{
        agent any
        stages{
            stage("Fase 1: Contruccion Applicacion"){
                when {
                    expression {
                        env.BRANCH_NAME == 'feature'
                    }
                }                
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
                when {
                    expression {
                        env.BRANCH_NAME == 'feature'
                    }
                }                
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
