def gv
pipeline {
    agent  any

    stages {
      stage('Checkout') {
             steps {
                    scmSkip(deleteBuild: true, skipPattern:'.*\\[ci skip\\].*')
                }
            }

       stage('init'){
            steps{
                script{
                    gv = load "script.groovy"
                }
            }
        }
        stage('version bump'){
            steps{
                script{
                    gv.incrementVersion()
                }
            }
        }
        stage('Build') {
            steps {
                script{
                    gv.buildImage()
                }
        }
        }
    stage('RUN') {
            steps {
                script{
                    gv.runAPP()
                }
            }        
        }
         
     stage('Version Commit') {
            steps {         
                script{
                    gv.commitVersion()
                }
        }
     }
    }
}
