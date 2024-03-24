def call (Map params){
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM url: ${scmUrl}"

// Clonacion de REpositorio
    echo "Clonacion de repositorio"
    checkout ([$class: 'GitSCM' , branches: [[name: '*/develop']], userRemoteConfigs: [[url:scmUrl ]]])

    // Contruccion de maven
    echo "Contruir aplicacion Maven"
    sh 'mvn clean package'
}