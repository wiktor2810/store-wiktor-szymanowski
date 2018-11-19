pipeline {
  agent any
  stages {
    stage('deploy') {
      steps {
        sh '''node{
        stage(\'Build\') 
                bat "mvn clean install\'
        }
   
}'''
        }
      }
    }
  }