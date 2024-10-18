pipeline {
    agent any

    stages {
        stage('git clone') {
            steps {
                git branch: 'main', credentialsId: 'gitHub-token', url: 'https://github.com/DonghwanSon1/tableorder.git'
            }
        }

        stage('Docker Container Stop And Remove') {
            steps {
                bat 'docker rm -f table-order-service'
            }
        }

        stage('Clean and Build') {
            steps {
                bat './gradlew clean build' // clean 후 build
//                bat 'ls -l build/libs' // 빌드 결과 확인
            }
        }

        stage('Build and Run with Docker Compose') {
            steps {
                dir('./tableorder') {
                    bat 'docker-compose up --build -d'
                }
            }
        }

        stage('Image Remove') {
            steps {
                bat 'docker image prune -f'
            }
        }
    }
}
