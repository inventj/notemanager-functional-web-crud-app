pipeline {
    agent any
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
               sh 'mvn -Dmaven.test.failure.ignore=true clean install'
            }
          
        }
        stage ('Deploy') {
           steps {
                    echo "Starting Deploy phase"
           }
        }
    }
}
