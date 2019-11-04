pipeline {
    agent any

    stages {
         stage ('install core') {
              steps {
                  sh 'cd heystyles-usuarios-core'
                  sh 'mvn install'
             }
         }
        stage ('install api') {
            steps {
                 sh 'cd ..'
                 sh 'mvn install'
            }
        }
    }
}