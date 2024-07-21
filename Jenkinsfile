pipeline {
    agent any

    tools {
        maven 'maven3' // Ensure 'maven3' is defined in your Jenkins tool configuration
    }

    tages {
        stage('Checkout') {
            steps {
                git 'https://github.com/aniket8979/deploySpringJenkins.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Deploy') {
            steps {
                script {
                    def artifact = 'target/DeployApplication.jar' // Replace with your artifact path

                    // Run the jar file locally
                    sh "java -jar ${artifact}"
                }
            }
        }
    }
}

