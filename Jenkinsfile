pipeline {
  agent any
  stages {
    stage('deploy') {
      steps {
        sh '''stages {
        stage(\'Build\') {
            steps {
                bat \'mvn -B -DskipTests clean package\'
            }
        }
        stage(\'Test\') { 
            steps {
                bat \'mvn test\' 
            }
}
}'''
        }
      }
    }
  }