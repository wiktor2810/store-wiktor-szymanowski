pipeline {
  agent any
  stages {
    stage('deploy') {
      steps {
        sh '''stages {
        stage(\'Build\') {
            steps {
                sh \'mvn -B -DskipTests clean package\'
            }
        }
        stage(\'Test\') { 
            steps {
                sh \'mvn test\' 
            }
}
}'''
        }
      }
    }
  }