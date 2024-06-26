pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "m3"
    }

    stages {
        stage('Run tests') {
            steps {
                // Get some code from a GitHub repository
                git branch: "main", url: 'https://github.com/AnzhelikaLevonyuk/SauceDemo.git'
                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean test"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
     stages {
         stage('Run tests') {
            steps {
                 allure includeProperties: false, report: 'target/allure-report', results: [[path: 'target/allure-results']]
                }
            }
    }
}
