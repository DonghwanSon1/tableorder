pipeline {
    agent any

    stages {
        stage('git clone') {
            steps {
                git branch: 'main', credentialsId: 'gitHub_id', url: 'https://github.com/DonghwanSon1/tableorder.git'
            }
        }
//
//        stage('Set Permissions') {
//            steps {
//                sh 'chmod +x gradlew' // gradlew에 실행 권한 추가
//            }
//        }
//
//        stage('Clean and Build') {
//            steps {
//                sh './gradlew clean build' // clean 후 build
//                sh 'ls -l build/libs' // 빌드 결과 확인
//            }
//        }

        stage('Docker Container Stop And Remove') {
            steps {
                sh 'docker rm -f table-order-service'
            }
        }

        stage('Build and Run with Docker Compose') {
            steps {
                dir('./tableorder') {
                    sh 'docker-compose up --build -d'
                }
            }
        }

        stage('Image Remove') {
            steps {
                sh 'docker image prune -f'
            }
        }
    }
}
