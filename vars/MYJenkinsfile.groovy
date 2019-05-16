#!/usr/bin/env groovy

def call() {

node ('master') {

try {

 stage('checkout') {
    cleanWs()
  }
   stage("GIT INFO"){

gitCommit = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
sh 'echo ${gitCommit}'

}
}
catch (err) {
        currentBuild.result = "FAILURE"
        throw err
        }
}

}
