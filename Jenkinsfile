node {
    stage "Checkout"
    git url: "https://github.com/bytewood/microservice.git"

    stage "Build"
    sh "chmod 755 gradlew"
    sh "./gradlew -g /tmp clean build -x test --stacktrace"

    stage "Unit Test"
    sh "./gradlew -g /tmp test --stacktrace"

    //stage "Integration Test"
    //sh ".gradlew -g /tmp integration --stacktrace"

    stage "Containerize"
    echo "Dockerizing..."
}
