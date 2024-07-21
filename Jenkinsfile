pipeline {
    agent any
    
    environment {
        GIT_REPO = 'https://github.com/aniket8979/deploySpringJenkins.git'
        GIT_BRANCH = 'main'
        APP_NAME = 'DeployApplication'
        JAR_NAME = "${APP_NAME}.jar"
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
                    if (!fileExists(JAR_NAME)) {
                        error "JAR file ${JAR_NAME} not found"
                    }
                    
                    // Stop any running instance
                    sh """
                    pid=\$(ps aux | grep ${JAR_NAME} | grep -v grep | awk '{print \$2}')
                    if [ ! -z "\$pid" ]; then
                        echo "Stopping existing application instance (PID: \$pid)"
                        kill \$pid
                        sleep 10
                    else
                        echo "No existing instance found"
                    fi
                    """
                    
                    // Start the application
                    echo "Starting application: ${JAR_NAME}"
                    sh "nohup java -jar ${JAR_NAME} > app.log 2>&1 &"
                    
                    // Check if application started successfully
                    sh """
                    sleep 30
                    if ps aux | grep ${JAR_NAME} | grep -v grep > /dev/null; then
                        echo "Application started successfully"
                    else
                        echo "Application failed to start"
                        exit 1
                    fi
                    """
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