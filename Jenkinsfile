node {
    def name = "ops-microservice"
    def semver = "0.1.0"
    def ops_scripts_semver = "0.1.5"
    def registry = "localhost:5000"

    def repo = "git@github.com:bytewood/${name}.git"

    def tag = "${semver}.${env.BUILD_NUMBER}"
    def repository = "bytewood/${name}"
    def scripts = "ops-scripts"

    stage ("Checkout") {
        git url: "${repo}"
        sh "chmod 755 ops-scripts.sh"
        sh "./ops-scripts.sh ${ops_scripts_semver}"
    }

    stage ("Build") {
        sh "chmod 755 gradlew"
        sh "./gradlew --stacktrace clean build -x test"
    }

    stage ("Unit Test") {
        sh "./gradlew test"
    }

    stage ("Integration Test") {
        //sh "./gradlew integration"
    }

    stage ("Containerize") {
        sh "chmod 755 $scripts/container-build.sh"
        sh "$scripts/container-build.sh ${repository} ${tag}"
    }

    stage ("Deploy") {
        sh "chmod 755 $scripts/container-push.sh"
        sh "$scripts/container-push.sh ${registry} ${repository} ${tag}"
    }

    stage ("Tag") {
        sh "chmod 755 $scripts/git-tag.sh"
        sh "$scripts/git-tag.sh ${tag}"
    }

}

