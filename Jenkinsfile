
pipeline {
    agent any

    stages {
        stage('Check Maven') {
            steps {
                bat '"D:\\Downlaods\\apache-maven-3.10.0\\bin\\mvn.cmd" -version'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                bat '"D:\\Downlaods\\apache-maven-3.10.0\\bin\\mvn.cmd" clean test'
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true,
                  testResults: 'target/surefire-reports/TEST-*.xml'

            archiveArtifacts artifacts: 'target/surefire-reports/**',
                             allowEmptyArchive: true
        }
    }
}
