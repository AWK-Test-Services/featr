pipeline {
    agent any
    tools {
        maven 'maven_3.5.3'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Unit test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Server test') {
            steps {
                sh 'mvn verify -Dskip.unit.tests'
            }
        }
        stage('Publish') {
            steps {
                sh 'mvn install -Dmaven.test.skip=true'
//                sh 'mvn deploy -Dmaven.test.skip=true'
            }
        }
    }
    post {
        always {
            junit 'target/surefire-reports/*.xml'
            junit 'target/failsafe-reports/*.xml'
        }
    }
}