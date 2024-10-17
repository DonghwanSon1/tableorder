pipeline {
    agent any

    stages {
        stage('git clone') {
            steps {
                git branch: 'main', credentialsId: 'gitHub_id', url: 'https://github.com/DonghwanSon1/tableorder.git'
            }
        }

        stage('Clean and Build') {
            steps {
                sh './gradlew clean build' // clean 후 build
                sh 'ls -l build/libs' // 빌드 결과 확인
            }
        }

        stage('Build and Run with Docker Compose') {
            steps {
                dir('./tableorder') { // 디렉토리 이동
                    sh 'docker-compose up --build -d'
                }
            }
        }
    }
}