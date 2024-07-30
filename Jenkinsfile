pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                script {
                    try {
                        sh 'mvn clean install'
                    } catch (Exception e) {
                        echo 'Build failed'
                        throw e
                    }
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    try {
                        sh 'mvn test'
                    } catch (Exception e) {
                        echo 'Tests failed'
                        throw e
                    }
                }
            }
        }
        stage('Report') {
            steps {
                script {
                    try {
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
