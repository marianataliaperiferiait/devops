def call (scmUri){
    pipeline{
        agent any
        stages{
            stage("Fase 1: Contruccion Applicacion"){
                // when {
                //     expression {
                //         env.BRANCH_NAME == 'develop'
                //     }
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
                // when {
                //     expression {
                //         env.BRANCH_NAME == 'develop'
                //     }
                }                
                steps {
                script {
                    def analisissonar = new org.devops.lb_analisissonarqube()
                    analisissonar.analisissonar()
                                    
                    }
                }
            }             
            stage("Fase 3: Construccion Imagen") {
                // when {
                //     expression {
                //         env.BRANCH_NAME == 'develop'
                //     }
                }                
                steps {
                script {
                    def buildImage = new org.devops.lb_buildimage()
                    buildImage.buildImage()
                                    
                    }
                }
            }
            stage("Fase 4: Publicacion en DockerHub") {
                // when {
                //     expression {
                //         env.BRANCH_NAME == 'develop'
                //     }
                }                
                steps {
                script {
                    def dockerHubUsername = 'sanchezmnperiferia'
                    def dockerHubTokenCredentialId = 'tokendockerhub'
                    def publishImage = new org.devops.lb_publicardockerhub()
                    publishImage.publishImage(dockerHubUsername, dockerHubTokenCredentialId)
                                    
                    }
                }
            // }
            stage("Fase 5: Despliegue de Container") {
                // when {
                //     expression {
                //         env.BRANCH_NAME == 'develop'
                //     }
                }                
                steps {
                script {
                    def dockerHubUsername = 'sanchezmnperiferia'
                    def imageName = 'crudspringboot-buildimagen'
                    def containerName = 'crudspringboot-container'
                    def runContainer = new org.devops.lb_buildartefacto()
                    runContainer.runContainer(dockerHubUsername, imageName, containerName)
                                    
                    }
                }
            // }            
            stage("Fase 5: Analisis Owasp") {
                // when {
                //     expression {
                //         env.BRANCH_NAME == 'develop'
                //     }
                }                
                steps {
                script {
                    def iphost = env.iphost
                    def dockerHubUsername = 'sanchezmnperiferia'
                    def crudspringbootImageName = 'springboot-buildimagen'
                    def networkName = 'jenkinsowasp'
                    def owaspAnalysis = new org.devops.lb_owasp()
                    owaspAnalysis.owaspAnalysis(iphost, dockerHubUsername, crudspringbootImageName, networkName)
                    
                                    
                    }
                }
//             }                                        
//             }                                     
//         }
// }   