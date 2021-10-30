pipeline { 
    agent any  
    stages { 
        stage('Build') { 
            steps { 
               echo 'This is a minimal pipeline.' 
            }
        }
      stage('package'){
        steps {
          sh "mvn package"
              }
                      }
      }
}
