node {
    def tag = "${env.BUILD_NUMBER}"
    def name = "microservice"
    def repository = "bytewood/ops/${name}"
    def registry = "localhost:5000"
    def repo = "https://github.com/bytewood/ops-${name}.git"

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
    sh "chmod 755 container-build.sh"
    sh "./container-build.sh ${repository} ${tag}"

    stage "Promotion"
    def userInput = input(
     id: 'userInput', message: 'Let\'s promote?', parameters: [
     [$class: 'TextParameterDefinition', defaultValue: 'uat', description: 'Environment', name: 'env'],
     [$class: 'TextParameterDefinition', defaultValue: 'uat1', description: 'Target', name: 'target']
    ])
    echo ("Env: " + userInput["env"])
    echo ("Target " + userInput["target"])

    stage "Deploy"
    echo ("deploying ...")
    sh "chmod 755 container-push.sh"
    sh "./container-push.sh ${registry} ${repository} ${tag}"
}
