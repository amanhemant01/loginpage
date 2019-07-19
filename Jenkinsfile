pipeline{
    agent any
    tools {
        maven 'mymaven' 
    }
    stages{
        stage ('build and install'){
            steps{
                sh "mvn clean install"
            }
        }
        stage('Sonar') {
            environment {
                scannerHome=tool 'sonar scanner'
            }
            steps{
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId:'Hemant_Sonar_Cred', usernameVariable: 'USER', passwordVariable: 'PASS']]){
                    sh "mvn $USER:$PASS -Dsonar.host.url=http://3.14.251.87:9000"
                }
            }
        }
        stage ('Uploading artifact to nexus'){
            steps{
                withCredentials([usernamePassword(credentialsId: 'Hemant_Nexus_Cred', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    sh label: '', script: 'curl -u $USER:$PASS --upload-file target/loginpage-${BUILD_NUMBER}.war http://3.14.251.87:8081/nexus/content/repositories/devopstraining/Hemant/loginpage-${BUILD_NUMBER}.war'
                }
            }
        }
        stage ('Deploy'){
            steps{
                withCredentials([usernamePassword(credentialsId: 'devops-tomcat', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                    sh label: '', script: 'curl -u  $USER:$PASS http://ec2-18-224-182-74.us-east-2.compute.amazonaws.com:8080/manager/text/undeploy?path=/login_hemant'
                    //sh label: '', script: 'curl -u  $USER:$PASS --upload-file target/loginpage-${BUILD_NUMBER}.war http://ec2-18-224-182-74.us-east-2.compute.amazonaws.com:8080/manager/text/deploy?config=file:/var/lib/tomcat8/loginpage-${BUILD_NUMBER}.war\\&path=/login_hemant'
                    sh label: '', script: 'curl -u $USER:$PASS http://3.14.251.87:8081/nexus/content/repositories/devopstraining/Hemant/redirect?r=releases&a=loginpage&v=${BUILD_NUMBER}&e=war -o http://ec2-18-224-182-74.us-east-2.compute.amazonaws.com:8080/manager/text/deploy?config=file:/var/lib/tomcat8/loginpage-${BUILD_NUMBER}.war\\&path=/login_hemant'
                    sh 'echo ${BUILD_NUMBER}'
                }
            }
        }
    }
    post {
       success {
           slackSend (color: '#00FF00', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
       }
       failure {
           slackSend (color: '#FF0000', message: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
       }
    }
}
