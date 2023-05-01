pipeline {
    agent  any

    stages {
        stage('Build') {
            steps {
                sh 'echo Building the image...'
                sh 'docker-compose build'
            }        
        }
    stage('RUN') {
            steps {
                sh 'echo Running the application...'
                sh 'docker-compose up'
            }        
        }    
    }
}
