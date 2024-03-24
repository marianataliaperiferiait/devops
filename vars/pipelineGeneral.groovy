def call (Map params){
    def scmUrl = params.scmUrl

    echo "Deploying backend with SCM url: ${scmUrl}"

    script {
        load 'vars/lb_buildartefacto/lb_buildartefacto'
        lb_buildartefacto.lb_buildartefacto1()
    } 
}   