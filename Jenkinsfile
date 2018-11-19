pipeline {
  agent any
  stages {
    stage('chromebrowser') {
      parallel {
        stage('chromebrowser') {
          steps {
            sh 'echo browser=CHROME>".\\src\\main\\resources\\config.properties"'
          }
        }
        stage('firefoxbrowser') {
          steps {
            sh 'echo browser=FIREFOX>".\\src\\main\\resources\\config.properties"'
          }
        }
      }
    }
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