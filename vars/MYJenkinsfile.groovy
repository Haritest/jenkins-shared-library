#!/usr/bin/env groovy

def call() {

node ('master') {

try {

 stage('checkout') {
    cleanWs()
    checkout scm
  }
   stage("GIT INFO"){

shortCommit = sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%h'").trim()

sh 'echo "$shortCommit"'
}
}
catch (err) {
        currentBuild.result = "FAILURE"
        throw err
        }
}

}
