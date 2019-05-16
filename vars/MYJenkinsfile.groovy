#!/usr/bin/env groovy

def call() {
  
node ('master') {

try {
        String ANSI_GREEN = "\u001B[32m"
        String ANSI_NORMAL = "\u001B[0m"
        String ANSI_BOLD = "\u001B[1m"
        String ANSI_RED = "\u001B[31m"
        String ANSI_YELLOW = "\u001B[33m"

ansiColor('xterm') {
  stage('checkout') {
    cleanWs()
    checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/Haritest/testpro']]])
  }

  stage('test-reponame') {
  def repName = checkout(scm).repoName
            sh "echo 'Repository Name is: ${repName}'"
            println repName
}
  stage('test') {
  sh 'echo $my_home'
    sh 'env > env.txt'
sh 'cat env.txt'
 sh 'echo $JOB_BASE_NAME'
   sh 'echo $BRANCH_NAME'
   sh 'echo $GIT_COMMIT'
  }
  
  stage('job-name'){
       echo "${env.JOB_NAME}"
    echo "${env.BRANCH_NAME}"
    echo "${env.GIT_COMMIT}"
  }
stage('test-new') {
           echo "Running ${env.JOB_NAME} on ${env.JENKINS_URL}"
  }  
  
  stage('shellscript'){
   sh 'ansible-vault view passwd.yml --vault-password-file=secrets'
  }

  stage('ansible-test') {
   sh 'ansible-playbook --vault-id /home/hari/ansible/testpro/secrets /home/hari/ansible/testpro/testdir.yml'
  }

  stage('colour') {

    ansiblePlaybook(
        playbook: '/home/hari/ansible/testpro/testdir.yml',
        extras: '--vault-id /home/hari/ansible/testpro/secrets',
        colorized: true)

}

}
}
catch (err) {
        currentBuild.result = "FAILURE"
        throw err
        }
}
}
