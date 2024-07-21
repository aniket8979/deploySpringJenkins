pipeline {
    agent any

    // environment {
    //     // Define environment variables (optional)
    //     // For example:
    //     // APP_NAME = 'my-spring-app'
    // }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from Git
                git branch: 'main', // Change this if your branch is different
                   url: 'https://github.com/aniket8979/deploySpringJenkins.git'
            }
        }

        stage('Build') {
            steps {
                // Build the project using Maven
                sh 'mvn clean install'
            }
        }

        // stage('Test') { // (Optional)
        //     steps {
        //         // Run unit tests (if applicable)
        //         sh 'mvn test'
        //     }
        // }

        stage('Package') {
            steps {
                // Package the application (assuming JAR output)
                sh 'mvn package'
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Find the generated JAR file
                    def jarFile = sh(script: "ls target/*.jar", returnStdout: true).trim()

                    // Start the application (assuming Spring Boot)
                    sh "nohup java -jar ${jarFile} &" // Use "nohup" to run in background

                    // Optional: Copy the JAR to a specific location on the server
                    // sh "cp ${jarFile} /path/to/deployment/directory/"
                }
            }
        }
    }

    post {
        success {
            // Actions to perform if the pipeline succeeds (optional)
            echo 'Project built and deployed successfully!'
        }

        failure {
            // Actions to perform if the pipeline fails (optional)
            echo 'Pipeline failed!'
        }
    }
}
