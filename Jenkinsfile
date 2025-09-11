pipeline {
    agent any

    tools {
        maven 'MyMaven'   // Maven tool configured in Jenkins
        jdk 'MyJava'      // JDK configured in Jenkins
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
                // Publish JUnit results
                junit 'target/surefire-reports/*.xml'

                // Archive HTML reports (Extent / custom reports)
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
                to: 'sagar.bankar590@gmail.com',
                subject: "❌ Build Failed - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    Hello Team,<br><br>
                    Build/Tests FAILED ❌<br><br>

                    <b>Test Summary:</b><br>
                    Total: ${currentBuild.rawBuild.getAction(hudson.tasks.junit.TestResultAction)?.totalCount} <br>
                    Passed: ${currentBuild.rawBuild.getAction(hudson.tasks.junit.TestResultAction)?.passCount} <br>
                    Failed: ${currentBuild.rawBuild.getAction(hudson.tasks.junit.TestResultAction)?.failCount} <br>
                    Skipped: ${currentBuild.rawBuild.getAction(hudson.tasks.junit.TestResultAction)?.skipCount} <br><br>

                    Please check Jenkins console logs.<br><br>
                    <b>Job:</b> ${env.JOB_NAME}<br>
                    <b>Build Number:</b> #${env.BUILD_NUMBER}<br>
                    <b>Build URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a>
                """,
                attachmentsPattern: 'reports/*.html'
            )
        }
        success {
            echo 'Build successful!'
            emailext (
                to: 'sagar.bankar590@gmail.com',
                subject: "✅ Build Success - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    Hello Team,<br><br>
                    All tests executed successfully ✅<br><br>

                    <b>Test Summary:</b><br>
                    Total: ${currentBuild.rawBuild.getAction(hudson.tasks.junit.TestResultAction)?.totalCount} <br>
                    Passed: ${currentBuild.rawBuild.getAction(hudson.tasks.junit.TestResultAction)?.passCount} <br>
                    Failed: ${currentBuild.rawBuild.getAction(hudson.tasks.junit.TestResultAction)?.failCount} <br>
                    Skipped: ${currentBuild.rawBuild.getAction(hudson.tasks.junit.TestResultAction)?.skipCount} <br><br>

                    Please find the attached test report.<br><br>
                    <b>Job:</b> ${env.JOB_NAME}<br>
                    <b>Build Number:</b> #${env.BUILD_NUMBER}<br>
                    <b>Build URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a>
                """,
                attachmentsPattern: 'reports/*.html'
            )
        }
    }
}
