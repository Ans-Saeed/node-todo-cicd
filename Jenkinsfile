pipeline {
    agent  any

    stages {
        stage('Build') {
            steps {
                 withCredentials([
        usernamePassword(credentialsId:'dockerhub-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')
    ]){
            echo "building the docker image of application..."
            sh 'docker build -t anssaeed/my-repo:todoapp-latest .'
            sh 'echo $PASS | docker login -u $USER --password-stdin'
            sh 'docker push anssaeed/my-repo:todoapp-latest'
            }        
        }
    
    stage('RUN') {
            steps {
                sh 'echo Running the application...'
                sh 'docker-compose down && docker-compose up -d'
            }        
        }    
    }
}
