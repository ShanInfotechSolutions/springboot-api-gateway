pipeline {
  agent any
  environment {
    REGISTRY = 'docker.io'
    NS       = 'shaninfotech'
    CREDS    = 'dockerhub_creds'
    VERSION  = '0.0.1'   // or use "${env.BUILD_NUMBER}" or a git SHA
    JAVA_HOME = tool name: 'JDK17', type: 'jdk'
    MAVEN_HOME = tool name: 'Maven3', type: 'maven'
    PATH = "${MAVEN_HOME}/bin:${JAVA_HOME}/bin:/usr/bin:/bin"
  }
  stages {
    stage('Checkout'){ steps { checkout scm } }

    stage('Build JAR'){
      steps {
        dir('SpringBootApiGateway'){
          sh 'mvn -q -DskipTests clean package'
        }
      }
    }

    stage('Docker Login'){
      steps {
        withCredentials([usernamePassword(credentialsId: "${CREDS}", usernameVariable: 'U', passwordVariable: 'P')]) {
          sh 'echo "$P" | docker login -u "$U" --password-stdin'
        }
      }
    }

    stage('Build & Push Image'){
      steps {
        dir('SpringBootApiGateway'){
          sh """
            docker build -t ${REGISTRY}/${NS}/api-gateway:${VERSION} .
            docker push ${REGISTRY}/${NS}/api-gateway:${VERSION}
          """
        }
      }
    }
  }
  post {
    always { sh 'docker logout || true' }
    success { echo "Pushed ${REGISTRY}/${NS}/api-gateway:${VERSION}" }
  }
}
