node {
    stage "Checkout"
    git url: "https://github.com/bytewood/microservice.git"

    stage "Build"
    sh "chmod 755 gradlew"
    sh "./gradlew clean build -x test"

    stage "Unit Test"
    sh "./gradlew test"

    //stage "Integration Test"
    //sh ".gradlewe integration"

    stage "Containerize"
    echo "Dockerizing..."
}
