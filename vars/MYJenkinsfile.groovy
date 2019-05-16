#!/usr/bin/env groovy

def call() {

node ('master') {

try {

 stage('checkout') {
    cleanWs()
  }
   stage("GIT INFO"){
     sh "git rev-parse --short HEAD > ./test"                        
commit_id = readFile('./test')

}
}
catch (err) {
        currentBuild.result = "FAILURE"
        throw err
        }
}

}
