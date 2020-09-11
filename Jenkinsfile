ipeline {
  agent any
  tools {
        maven 'Maven3'
        jdk 'JDK14'
  }
  environment {
    NAME = readMavenPom().getName();
    VERSION = '1.1.1';
  }
  options {
    buildDiscarder logRotator(numToKeepStr: '10')
  }
  stages {
    stage('Clean') {
      steps {
        sh 'mvn clean'
      }
    }
    stage('Package') {
      steps {
        sh 'mvn package'
      }
    }
    stage('Archive') {
      steps {
        archiveArtifacts allowEmptyArchive: true, artifacts: 'target/' + env.NAME + '.jar', fingerprint: true, onlyIfSuccessful: true
      }
    }
    stage('Publish') {
      steps {
        script {
          if (env.BRANCH_NAME == 'dev') {
            nexusPublisher nexusInstanceId: 'lostNameNexus', nexusRepositoryId: 'maven-snapshots', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: 'target/' + env.NAME + '.jar']], mavenCoordinate: [groupId: 'eu.lostname', artifactId: env.NAME, version: env.VERSION + "-SNAPSHOT", packaging: 'jar']]]
          } else if (env.BRANCH_NAME == 'master') {
            nexusPublisher nexusInstanceId: 'lostNameNexus', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: 'target/' + env.NAME + '.jar']], mavenCoordinate: [groupId: 'eu.lostname', artifactId: env.NAME, version: env.VERSION, packaging: 'jar']]]
          }
        }
      }
    }
  }
  post {
    always {
      discordSend description: 'Jenkins Build', footer: "${JOB_NAME} - ${BUILD_DISPLAY_NAME}", link: env.BUILD_URL, result: currentBuild.currentResult, title: NAME, webhookURL: 'https://discordapp.com/api/webhooks/716257990751027230/Ej7s_AjnyzXFpzYTxWwgU5oUpiHfUpPJh5v2mbWHrOA8HG7ZalWnghrDmLXLBErGlIaT'
    }
  }
}
