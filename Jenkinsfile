pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    sh 'mvn clean install'
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    sh 'mvn test'
                }
            }
        }
        stage('Report') {
            steps {
                script {
                    sh 'mvn verify'
                }
                publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'target', reportFiles: 'cucumber-html-reports/overview-features.html', reportName: 'Cucumber Test Report'])
            }
        }
    }
}
