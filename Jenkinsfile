pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "m3"
    }

    parameters {
          gitParameter branchFilter: 'origin/(.*)', defaultValue: 'main', name: 'BRANCH', type: 'PT_BRANCH'
          choice (name: 'BROWSER', choices: ['chrome','firefox','safari'], description: 'Select browser')
          choice(name: 'SUITE', choices: ['suits/smoke.xml', 'suits/regression.xml'], description: 'Choose suite to run')
          booleanParam (name: 'IS_HEADLESS', defaultValue: true, description: 'Headless mode')
        }

    triggers {
           parameterizedCron('''
           0 9 * * 1,3,5 %SUITE=smoke.xml;BROWSER=Chrome;
           0 23 1-31/2 * * %SUITE=regression;BROWSER=firefox;
           ''')
        }

    stages {
        stage('Run tests') {
            steps {
                // Get some code from a GitHub repository
                git branch: "${params.BRANCH}", url: 'https://github.com/AnzhelikaLevonyuk/SauceDemo.git'
                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true -Dsuite=${params.SUITE} -Dbrowser=${params.BROWSER} -Dheadless=${params.IS_HEADLESS} clean test"

            }

            post {

                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
    }
    stage('Generate allure report') {
            steps {
            allure includeProperties: false, report: 'target/allure-report', results: [[path: 'target/allure-results']]
            }
        }
    }
}
