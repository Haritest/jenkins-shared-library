def call(body) {
body()

node('master')
{
stage('Deploy')


     sh  '''
            pwd
         '''
}
}


