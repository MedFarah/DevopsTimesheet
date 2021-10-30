pipeline { 
    agent any  
    stages { 
        stage('Test') { 
            steps { 
               echo 'mvn clean install' 
            }
        }
	stage("build & SonarQube analysis") {
            agent any
            steps {
              
                bat 'mvn clean install sonar:sonar'
		echo 'im here **********************************'
              
            }
          }
	stage('Deploy to nexus'){
	steps {
	bat 'mvn clean package -Dmaven.test.failure.ignore=true deploy:deploy-file -DgroupId=org.springframework.boot -DartifactId=spring-boot-starter-parent -Dversion=2.1.4.RELEASE -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://localhost:8082/repository/maven-releases/ -Dfile=target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.1.jar'
	}
      }
}
}
