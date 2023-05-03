def gv
pipeline {
    agent  any

    stages {
       stage('init'){
            steps{
                script{
                    gv = load "script.groovy"
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
         stage('version bump'){
            steps{
                script{
                    gv.incrementVersion()
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
