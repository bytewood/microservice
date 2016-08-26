node {
    def name = "microservice"
    def image_name = "bytewood/ops/${name}"
    def repo = "https://github.com/bytewood/${name}.git"
    stage "Checkout"
    git url: "${repo}"

    stage "Build"
    sh "chmod 755 gradlew"
    sh "./gradlew --stacktrace clean build -x test"

    stage "Unit Test"
    sh "./gradlew test"

    //stage "Integration Test"
    //sh ".gradlew integration"

    stage "Containerize"
    sh "docker build -t ${image_name}:${env.BUILD_NUMBER} -t ${image_name}:latest ."

    stage "Deploy"
    sh "docker push "
}
