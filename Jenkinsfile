pipeline {
    agent any

    tools {
        maven 'MyMaven'   // Jenkins मध्ये configure केलेलं Maven tool
        jdk 'MyJava'      // Jenkins मध्ये configure केलेलं JDK
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
                archiveArtifacts artifacts: 'reports/*.html', fingerprint: true
            }
        }
    }

    post {
        always {
            echo 'Cleaning workspace...'
            cleanWs()
        }
        failure {
            echo 'Build failed. Please check logs.'
            emailext (
                to: 'qa-team@xyz.com',
                subject: "❌ Build Failed - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    Hello Team,<br><br>
                    Build/Tests FAILED ❌<br>
                    Please check Jenkins console logs.<br><br>
                    Report attached.
                """,
                attachmentsPattern: 'reports/*.html'
            )
        }
        success {
            echo 'Build successful!'
            emailext (
                to: 'qa-team@xyz.com',
                subject: "✅ Build Success - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    Hello Team,<br><br>
                    All tests executed successfully ✅<br><br>
                    Please find the attached test report.<br><br>
                    <b>Jenkins Job:</b> ${env.JOB_NAME}<br>
                    <b>Build Number:</b> #${env.BUILD_NUMBER}<br>
                    <b>Build URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a>
                """,
                attachmentsPattern: 'reports/*.html'
            )
        }
    }
}
