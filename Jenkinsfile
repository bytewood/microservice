node {
    stage "Checkout"
    git url: "https://github.com/bytewood/microservice.git"

    stage "Build"
    sh "chmod 755 gradlew"
    sh "./gradlew --stacktrace clean build -x test"

    stage "Unit Test"
    sh "./gradlew test"

    //stage "Integration Test"
    //sh ".gradlew integration"

    stage "Containerize"
    sh "docker build -t bytewood/devops/microservice:${en.BUILD_NUMBER} ."
}
