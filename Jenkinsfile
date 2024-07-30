pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                sh 'echo "Building project..."'
//                     sh 'mvn clean install'
                }
            }
        }
        stage('Test') {
            steps {
                script {
                sh 'echo "Running tests..."'
//                     sh 'mvn test'
                }
            }
        }
        stage('Report') {
            steps {
            sh 'echo "Generating reports..."'
//                 publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'target', reportFiles: 'cucumber-reports.html', reportName: 'Cucumber Test Report'])
            }
        }
    }
}
