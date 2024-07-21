pipeline {
    agent any
    
    environment {
        GIT_REPO = 'https://github.com/aniket8979/deploySpringJenkins.git'
        GIT_BRANCH = 'main'
        APP_NAME = 'DeployApplication'
        JAR_NAME = "${APP_NAME}.jar"
        APP_PORT = '5000'
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: "${GIT_BRANCH}", url: "${GIT_REPO}"
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        
        stage('Package') {
            steps {
                sh 'mvn package'
                sh 'find . -name "*.jar" -type f' // Debug: List all JAR files
                script {
                    def jarFile = sh(script: "find target -name '*.jar' ! -name '*-sources.jar' ! -name '*-javadoc.jar' -type f | head -n 1", returnStdout: true).trim()
                    if (jarFile) {
                        sh "cp ${jarFile} ${JAR_NAME}"
                        echo "JAR file copied to ${JAR_NAME}"
                    } else {
                        error "No JAR file found in target directory"
                    }
                }
            }
        }
        
        stage('Deploy') {
            steps {
                script {
                
                    // Start the application
                    echo "Starting application: ${JAR_NAME}"
                    sh "nohup java -jar ${JAR_NAME} > app.log 2>&1 &"
                    
                    
                }
            }
        }
    }
    
    post {
        success {
            echo 'Jai Shree Ram !!'
        }
        failure {
            echo 'Pipeline failed!'
        }
        always {
            archiveArtifacts artifacts: "${JAR_NAME}, app.log", allowEmptyArchive: true
        }
    }
}