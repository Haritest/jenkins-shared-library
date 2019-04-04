#!groovy
node ('master'){

  stage('checkout')
  {
    checkout scm
  }

  stage('test')
  {
  sh 'echo $my_home'
    sh 'pwd'
     sh 'ls -al'
    sh 'env > env.txt'
sh 'cat env.txt'
  }

}
