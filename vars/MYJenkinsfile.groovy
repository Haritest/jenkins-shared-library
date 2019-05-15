#!/usr/bin/env groovy

def call() {



node ('master') {

try {

 stage('checkout') {
    cleanWs()
    checkout scm  
  }
   stage("GIT INFO"){
    echo ":::::::::::GIT_SHORT_COMMIT::::::::::::::::::::::::"

    GIT_SHORT_COMMIT = sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%h'").trim()
    //echo in jenkins console
    echo GIT_SHORT_COMMIT
    //wanted to send these info to build artifacts, append to any file
    sh("echo ${GIT_SHORT_COMMIT} > GIT_SHORT_COMMIT")

    //Similar proceed for other git info's 
    echo ":::::::::::GIT_COMMITTER_EMAIL::::::::::::::::::::::::"

    GIT_COMMITTER_EMAIL = sh(returnStdout: true, script: "git show -s --pretty=%ae").trim()
    sh("echo ${GIT_COMMITTER_EMAIL} > GIT_COMMITTER_EMAIL-${GIT_COMMITTER_EMAIL}")



    echo ":::::::::::GIT_COMMITTER_NAME::::::::::::::::::::::::"

    GIT_COMMITTER_NAME = sh(returnStdout: true, script: "git show -s --pretty=%an").trim()
    sh("echo ${GIT_COMMITTER_NAME} > GIT_COMMITTER_NAME-${GIT_COMMITTER_NAME}")
  }

}
}
catch (err) {
        currentBuild.result = "FAILURE"
        throw err
        }
}

}
