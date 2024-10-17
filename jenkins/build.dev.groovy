pipeline {
    agent any

    stages {
        stage('git clone') {
            steps {
                git branch: 'main', credentialsId: 'gitHub_id', url: 'https://github.com/DonghwanSon1/tableorder.git'
            }
        }

        stage('Build and Run with Docker Compose') {
            steps {
                dir('./tableorder') {
                    sh 'docker-compose up --build -d'
                }
            }
        }
    }
}