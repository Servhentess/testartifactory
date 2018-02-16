pipeline{

    agent any

    tools{
        jdk 'jdk 8'
        maven '3.5'
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
                sh "mvn clean package"
                sh "echo !!! PACKAGE OK !!!"
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
                sh "mvn clean deploy"
            }
        }
    }
}