pipeline {
	environment
	{
		registry = "ezzeddine/spring"
		registryCredential= 'DockerHub'
		dockerImage = ''
	}
    agent any

    stages {
		stage('Checkout GIT') {
            steps {
                echo 'Pulling...';
                git branch: 'ezzeddine',
                url: 'https://github.com/MedFarah/DevopsTimesheet';
            }
        }

        stage('Build, Testing'){
        	steps {
        		echo 'Building and testing...';
           		bat """mvn clean install"""
        	}    
        }
        
        stage('Test Junit') {
            steps {
                bat """mvn test"""
            }
        }
        
        stage('Sonar'){
            steps {
            	echo 'Sonar';
                bat """mvn sonar:sonar"""
            }
        }
        
        stage('Nexus'){
            steps {
            	echo 'Deploying on Nexus...';
                bat """mvn clean package deploy:deploy-file -DgroupId=tn.esprit.spring -DartifactId=Timesheet-spring-boot-core-data-jpa-mvc-REST-1 -Dversion=0.0.1-SNAPSHOT -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://localhost:8081/repository/maven-releases/ -Dfile=target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.1-SNAPSHOT.war"""
            }
        }

		stage('Building our image') {
			steps { 
				script { 
					echo 'Building image...';
					dockerImage = docker.build registry + ":$BUILD_NUMBER"
				} 
			}
		}

		stage('Deploy our image') {
			steps { 
				script { 
					echo 'Deploying image...';
					docker.withRegistry( '', registryCredential) { dockerImage.push() } 
					} 
				}
		}

		stage('Cleaning up') {
			steps { 
				echo 'Cleaning up...';
				bat "docker rmi $registry:$BUILD_NUMBER" 
			}
		}
    }
    
    post {
        success {
            emailext body: 'build success', subject: 'Jenkins', to:
            'ezzeddine.chariout@gmail.com'
        }
        
        failure {
            emailext body: 'build failure', subject: 'Jenkins', to:
            'ezzeddine.chariout@gmail.com'
        }
    }
}