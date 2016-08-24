node {
    stage "Checkout"
    git url: "https://github.com/bytewood/microservice.git"

    stage "Build"
    sh "chmod 755 gradlew"
    sh "./gradlew --stacktrace -g /tmp clean build -x test"

    stage "Unit Test"
    sh "./gradlew -g /tmp test --stacktrace"

    //stage "Integration Test"
    //sh ".gradlew -g /tmp integration --stacktrace"

    stage "Containerize"
    echo "Dockerizing..."
}
