pipeline {
    agent any
    tools {
        maven 'maven-3.5.3-global-tools'
        jdk 'jdk'
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
                sh 'mvn -Dmaven.test.failure.ignore=true clean install' 
            }
          
        }
        stage ('Deploy') {
           steps {
                echo "Starting Deploy phase"
                echo "Stop running application"
                echo "Copy JAR"
                sh 'ssh -i /home/ec2-user/jenkins.pem jenkins@ec2-18-197-144-95.eu-central-1.compute.amazonaws.com'
                echo "Start application"
                echo "DONE"
            }
        }
    }
}
