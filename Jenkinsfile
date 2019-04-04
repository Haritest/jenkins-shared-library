#!groovy
node ('master'){

  stage('checkout')
  {
    checkout scm
  }

  stage('test')
  {
  sh 'echo $my_home'
    sh 'env > env.txt'
sh 'cat env.txt'
  }
  stage('shellscript')
  {
   sh 'bash ./bye.sh'
  }

}
