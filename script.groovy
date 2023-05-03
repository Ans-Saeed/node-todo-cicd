def runAPP(){
   sh 'echo Running the application...'
     sh 'docker-compose down && docker-compose up -d'
}
def buildImage(){
   withCredentials([
        usernamePassword(credentialsId:'dockerhub-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')
    ]){
            echo "building the docker image of application..."
            def image= sh "cat package.json | grep version | head -1 | awk -F: '{ print $2 }' | sed 's/[",]//g'"
            env.IMAGE_NAME= $image
            sh "echo ${IMAGE_NAME}"
            sh 'docker build -t anssaeed/my-repo:${IMAGE_NAME} .'
            sh 'echo $PASS | docker login -u $USER --password-stdin'
            sh 'docker push anssaeed/my-repo:${IMAGE_NAME}'
   }

}

def incrementVersion(){
        sh 'npm --no-git-tag-version version patch'                 
                   env.IMAGE_NAME=readJSON(file: 'package.json').version
                    
}

def commitVersion(){
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


return this
