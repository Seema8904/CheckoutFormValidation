
pipeline {
    agent any

    stages {
        stage('Run Selenium Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/TEST-*.xml'
            archiveArtifacts artifacts: 'target/surefire-reports/**',
                             allowEmptyArchive: true
        }
    }
}
