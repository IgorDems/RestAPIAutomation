pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build and Test') {
            steps {
                script {
                    try {
                        sh 'ls -al target' // Debugging step to check the directory before build
                        sh 'mvn clean verify'
                        sh 'ls -al target' // Debugging step to check the directory after build
                    } catch (Exception e) {
                        echo 'Build and Test failed'
                        throw e
                    }
                }
            }
        }
        stage('Report') {
            steps {
                script {
                    try {
                        sh 'ls -al target' // Check if cucumber.json exists before generating the report
                        sh 'mvn verify'
                    } catch (Exception e) {
                        echo 'Report generation failed'
                        throw e
                    }
                }
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: '**/target/cucumber.json', allowEmptyArchive: true
            junit '**/target/surefire-reports/*.xml'
        }
        failure {
            mail to: 'dems_i@yahoo.com',
                 subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
                 body: "Something is wrong with ${env.BUILD_URL}"
        }
    }
}
