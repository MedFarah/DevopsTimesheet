pipeline { 
	environment {
		registry = "mofarah7/timesheet"
		registryCredential= 'dockerHub'
		dockerImage = ''
	}
    agent any  
    stages {

		stage("build project") {
				agent any
				steps {
				  
					bat 'mvn clean install'
				  
				}
			  }
        
		stage("SonarQube analysis & jacoco code coverage") {
				agent any
				steps {
				  
					bat 'mvn compile sonar:sonar'
				  
				}
			  }
		stage(' nexus'){
			steps {
					bat 'mvn clean package -Dmaven.test.failure.ignore=true deploy:deploy-file -DgroupId=org.springframework.boot -DartifactId=spring-boot-starter-parent -Dversion=2.1.4.RELEASE -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://localhost:8082/repository/maven-releases/ -Dfile=target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.1-SNAPSHOT.jar'
			}
			  }
			  
	    stage('Building our image') {
            steps { 
					script { 
							dockerImage= docker.build registry  
							}
				  }
			}
			
		stage('Deploy our image') {
			steps { script { docker.withRegistry( '', registryCredential) { dockerImage.push() } } }
			}
			
	  }
	  post {
        always {
            echo 'Post jenkins file execution'
        }
        success {
            mail bcc: '', body: "<b>Details:</b><br>\n<br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> URL de build: ${env.BUILD_URL}", cc: '', charset: 'UTF-8', from: 'mohamed.farah1@esprit', mimeType: 'text/html', replyTo: '', subject: "SUCCESS : Project name -> ${env.JOB_NAME}", to: "mohamed.farah1@esprit.tn";

        }
        failure {
            mail bcc: '', body: "<b>Details:</b><br>\n<br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> URL de build: ${env.BUILD_URL}", cc: '', charset: 'UTF-8', from: 'mohamed.farah1@esprit', mimeType: 'text/html', replyTo: '', subject: "ERROR : Project name -> ${env.JOB_NAME}", to: "mohamed.farah1@esprit.tn";
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
	}

}
