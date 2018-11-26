pipeline {
  agent any
  stages {
    stage('deploy') {
      steps {
        sh 'mvn clean source:jar package'
      }
    }
  }
}