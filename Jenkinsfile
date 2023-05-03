pipeline {
    agent  any

    stages {
       
        stage('Build') {
            steps {
                 withCredentials([
        usernamePassword(credentialsId:'dockerhub-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')
    ]){
            echo "building the docker image of application..."
            sh 'docker build -t anssaeed/my-repo:${IMAGR_NAME} .'
            sh 'echo $PASS | docker login -u $USER --password-stdin'
            sh 'docker push anssaeed/my-repo:${IMAGR_NAME}'
            }        
        }
        }
    stage('RUN') {
            steps {
                sh 'echo Running the application...'
                sh 'docker-compose down && docker-compose up -d'
            }        
        }
         stage('version bump'){
            steps{
                   sh 'npm --no-git-tag-version version patch'
                   sh "def matcher = readFile('package.json.xml') =~ '<version>(.+)</version>'"
                   sh 'def version =matcher[0][1]'
                   sh 'env.IMAGE_NAME="$version-$BUILD_NUMBER"'

            }
        }
     stage('Version Commit') {
            steps {         
                withCredentials([
        usernamePassword(credentialsId:'git-hub-access-key', usernameVariable: 'USER', passwordVariable: 'PASS')
    ]){

                sh 'echo Minor Version Bump...'
                sh 'git status'
                sh 'git config user.email jenkins@example.com'
                sh 'git config user.name jenkins'
                    sh "git remote set-url origin https://${PASS}@github.com/${USER}/node-todo-cicd.git"
                sh 'git add .'
                sh 'git commit -m "[ci skip] : version bump"'
                sh 'git push origin HEAD:master'

            }        
        }
     }
    }
}
