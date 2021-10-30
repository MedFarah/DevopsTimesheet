pipeline { 
    agent any  
    stages { 
        stage('Test') { 
            steps { 
               echo 'This is a minimal pipeline.' 
            }
        }
	stage("build & SonarQube analysis") {
            agent any
            steps {
              withSonarQubeEnv('My SonarQube Server') {
                sh 'mvn clean package sonar:sonar'
              }
            }
          }

      }
}
