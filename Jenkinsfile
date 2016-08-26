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
}
