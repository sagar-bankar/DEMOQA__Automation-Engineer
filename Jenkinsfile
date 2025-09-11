pipeline {
    agent any

    tools {
        maven 'MyMaven'   // Jenkins मध्ये आधी Maven tool configure करून नाव "Maven3" द्या
        jdk 'MyJava'      // Jenkins मध्ये JDK configure करून नाव "JDK11" द्या
    }

    environment {
        // Report path, इथे बदल करू शकतोस
        REPORT_DIR = "target/surefire-reports"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Checking out code from GitHub..."
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "Building the Maven project..."
                sh "mvn clean install -DskipTests"
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running TestNG automation suite..."
                sh "mvn test -DsuiteXmlFile=DEMOQASuite.xml"
            }
        }

        stage('Archive Reports') {
            steps {
                echo "Archiving test reports..."
                junit '**/target/surefire-reports/*.xml'
                archiveArtifacts artifacts: '**/target/surefire-reports/*.*', fingerprint: true
            }
        }

        stage('Post Results') {
            steps {
                echo "Post-build actions (email/Slack notification can be added here)..."
            }
        }
    }

    post {
        always {
            echo "Cleaning workspace..."
            cleanWs()
        }
        success {
            echo "Build & Test successful!"
        }
        failure {
            echo "Build failed. Please check logs."
        }
    }
}
