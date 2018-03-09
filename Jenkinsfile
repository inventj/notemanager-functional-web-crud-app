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
               sh '''
                    echo "Starting Deploy phase"
                    echo "Stop running application"
                     ssh -o StrictHostKeyChecking=no -t -t -i /data/jenkins.pem jenkins@ec2-18-197-144-95.eu-central-1.compute.amazonaws.com "
                     /data/notes/stop.sh ; cp /var/lib/jenkins/.m2/repository/be/inventj/notemanager-api/1.0-SNAPSHOT/notemanager-api-1.0-SNAPSHOT.jar /data/notes/notes.jar ; /data/notes/start.sh
                     "               
                   echo "Starting application"  
                    echo "DONE"
                  '''
            }
        }
    }
}
