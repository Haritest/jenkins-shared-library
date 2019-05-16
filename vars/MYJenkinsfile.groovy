#!/usr/bin/env groovy

def call() {

node ('master') {

try {

 stage('checkout') {
    cleanWs()
    checkout scm
    def commit_hash = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
    println("Tag not specified, using the latest commit hash: " + commit_hash)
    env.commit_hash = commit_hash
 //   echo "${commit_hash}"
    sh 'echo "commit_hash"'    
  }
   stage("GIT INFO"){


}
}
catch (err) {
        currentBuild.result = "FAILURE"
        throw err
        }
}

}
