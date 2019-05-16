#!/usr/bin/env groovy

def call() {

node ('master') {

try {

 stage('checkout') {
    cleanWs()
    checkout scm  
  }
   stage("GIT INFO"){
     sh "git rev-parse --short HEAD > .git/commit-id"                        
commit_id = readFile('.git/commit-id')

}
}
catch (err) {
        currentBuild.result = "FAILURE"
        throw err
        }
}

}
