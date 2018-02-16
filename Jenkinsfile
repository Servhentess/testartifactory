pipeline{

    agent any

    tools{
        jdk 'jdk 8'
        maven 'mvn 3.5'
    }

    options{
        buildDiscarder(logRotator(numToKeepStr: '6'))
    }

    stages{
        stage('Git checkout'){
            steps{
                deleteDir()
                checkout scm
            }
        }

        stage('Artifactory'){
            steps{
                def server = Artifactory.server 'Artifactory'
            }
        }

        stage('Maven Package'){
            steps{
                bat "mvn clean package"
            }
            /*post{
                always{
                    junit "path/to/xml"
                }
            }*/
        }

        stage('Code checking & Analysis'){

        }

        stage('Deploy artifact to Artifactory'){
            steps{
                bat "mvn clean deploy"
            }
        }
    }
}