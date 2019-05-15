#!/usr/bin/env groovy


def call() {
  node {
    stage('Checkout') {
      checkout scm
    }
    def p = pipelineCfg()

    if (p.runTests == true) {
      docker.image(p.testImage).inside() {
        stage('Test') {
          sh 'pip install -r requirements.txt'
          sh p.testCommand
        }
      }
    }

    if (env.BRANCH_NAME == 'master' && p.deployUponTestSuccess == true) {
      docker.image(p.deployToolImage).inside {
        stage('Deploy') {
          sh "echo ${p.deployCommand} ${p.deployEnvironment}"
        }
      }
    }
  }
