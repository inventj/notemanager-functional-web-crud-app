pipeline {
    agent any
    tools {
        maven 'mvn'
        jdk 'java'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build') {
            steps {
              echo 'doing the build'
            }
          
        }
        stage ('Deploy') {
           steps {
                    echo "Starting Deploy phase"
           }
        }
    }
}
