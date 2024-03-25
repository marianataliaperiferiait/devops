def call (){

pipeline{
    agent any
    stages{
        stage("Fase 1: Contruccion Applicacion"){
            steps {
                script {
                    def build cloneapp = new org.devops.lb_buildartefacto()
                    cloneapp.clone
                    def build aplicationapp = new org.devops.lb_buildartefacto()
                    aplication.aplication
                    def build artefactapp = new org.devops.lb_buildartefacto()
                    artefactapp.artefact                                      
                }
            }
        }             
        stage("Fase 2: Scanner Sonnarqube") {
        }             
                                   
    }
 }
}