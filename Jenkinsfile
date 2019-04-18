#!groovy

node ('master') {
 
try {

  stage('checkout') {
    cleanWs()
    checkout scm
  }

  stage('test') {
  sh 'echo $my_home'
    sh 'env > env.txt'
sh 'cat env.txt'
  }

  stage('shellscript'){
   sh 'ansible-vault view passwd.yml --vault-password-file=secrets'
  }

  stage('ansible-test') {
   sh 'ansible-playbook --vault-id /home/hari/ansible/testpro/secrets /home/hari/ansible/testpro/testdir.yml'
  }

}

catch (err) {
        currentBuild.result = "FAILURE"
        throw err
	}
}
