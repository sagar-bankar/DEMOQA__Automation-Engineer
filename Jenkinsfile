pipeline {
    agent any

    tools {
        maven 'MyMaven'   // Define Maven tool from Jenkins global config
        jdk 'MyJava'      // Define JDK from Jenkins global config
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from GitHub...'
                git url: 'https://github.com/sagar-bankar/DEMOQA__Automation-Engineer.git', branch: 'master'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the Maven project...'
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running Tests...'
                bat 'mvn test'
            }
        }

        stage('Archive Reports') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            echo 'Cleaning workspace...'
            cleanWs()  // Needs Workspace Cleanup Plugin
        }
        failure {
            echo 'Build failed. Please check logs.'
        }
        success {
            echo 'Build successful!'
        }
    }
}
