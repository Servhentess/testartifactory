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
                script{
                    server = Artifactory.server 'Artifactory'

                    rtMaven = Artifactory.newMavenBuild()
                    rtMaven.tool = '3.5'
                    rtMaven.resolver server: server, releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot'
                    rtMaven.deployer server: server, releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local'

                    buildInfo = Artifactory.newBuildInfo()
                    buildInfo.env.capture = true

                }
            }
        }

        stage('Code checking & Analysis'){
            steps {
                bat 'mvn clean sonar:sonar'
            }
        }

        stage('Deploy artifact to Artifactory'){
            steps{
                script {
                    buildInfo = rtMaven.run goals: 'clean install'
                    rtMaven.deployer.deployArtifacts buildInfo
                }
            }
        }
    }
}