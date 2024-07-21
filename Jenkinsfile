Pipeline {
    Agent any

    Tools {
        Maven 'maven3'
    }


    Environment {
        APP_NAME = "DummyDeployment_APP"
        APP_ENV = "DEV"
    }

    Stages {
        Stage('Cleanup Workspace') {
            Steps {
                CleanWs()
                Sh 'echo "Cleaned Up Workspace for ${APP_NAME}"'
            }
        }

        Stage('Code Checkout') {
            Steps {
                Checkout([
                    $class: 'GitSCM',
                    Branches: [[name: '*/main']],
                    UserRemoteConfigs: [[url: 'https://github.com/aniket8979/deploySpringJenkins.git']]
                ])
            }
        }

        Stage('Code Build') {
            Steps {
                Sh 'mvn install -Dmaven.test.skip=true'
            }
        }

        Stage('Printing All Global Variables') {
            Steps {
                Sh 'env'
            }
        }

    }
}