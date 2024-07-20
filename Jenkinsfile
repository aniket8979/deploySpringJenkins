pipeline {
    agent any

    environment {
        // Define environment variables
        GIT_REPO = 'https://github.com/aniket8979/deploySpringJenkins.git'
        GIT_BRANCH = 'main' // Change this if your branch is different
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from GitHub
                git branch: "${GIT_BRANCH}", url: "${GIT_REPO}"
            }
        }
        stage('Build') {
            steps {
                // Build the project using Maven
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                // Run unit tests
                sh 'mvn test'
            }
        }
        stage('Package') {
            steps {
                // Package the application
                sh 'mvn package'
            }
        }
        stage('Deploy') {
            steps {
                script {
                    def jarFile = sh(script: 'ls target/*.jar', returnStdout: true).trim()
                    sh "java -jar ${jarFile}"
                }
            }
        }
    }

    post {  // Correct indentation here
        success {
            // Actions to perform if the pipeline succeeds
            echo 'Jai Shree Ram !!'
        }
        failure {
            // Actions to perform if the pipeline fails
            echo 'Pipeline failed!'
        }
    }
}
