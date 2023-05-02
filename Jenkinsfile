pipeline {
    agent  any

    stages {
        stage('version bump'){
            steps{
                   sh 'npm version patch'
            }
        }
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
        }
    stage('RUN') {
            steps {
                sh 'echo Running the application...'
                sh 'docker-compose down && docker-compose up -d'
            }        
        }  
     stage('Version Commit') {
            steps {         
                withCredentials([
        usernamePassword(credentialsId:'eb7ded11-68c4-4f9e-9f2f-dd16e659433c', usernameVariable: 'USER', passwordVariable: 'PASS')
    ]){

                sh 'echo Minor Version Bump...'
                sh 'git status'
                sh 'git config user.email jenkins@example.com'
                sh 'git config user.name jenkins'
                sh "git remote set-url origin https://${USER}:${PASS}@github.com/Ans-Saeed/node-todo-cicd.git"
                sh 'git add .'
                sh 'git commit -m "[ci skip] : version bump"'
                sh 'git push origin HEAD:master'

            }        
        }
     }
    }
}
