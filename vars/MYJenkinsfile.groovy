#!/usr/bin/env groovy

def call() {

node ('master') {

try {

 stage('checkout') {
    cleanWs()
    checkout scm
    commit_hash = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
    println("Tag not specified, using the latest commit hash: " + commit_hash)
    sh echo "$commit_hash"

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
