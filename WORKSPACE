load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

rules_kotlin_version = "legacy-1.4.0-rc4"
rules_kotlin_sha = "9cc0e4031bcb7e8508fd9569a81e7042bbf380604a0157f796d06d511cff2769"
http_archive(
    name = "io_bazel_rules_kotlin",
    urls = ["https://github.com/bazelbuild/rules_kotlin/releases/download/%s/rules_kotlin_release.tgz" % rules_kotlin_version],
    type = "tgz",
    sha256 = rules_kotlin_sha,
)

load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kotlin_repositories", "kt_register_toolchains")
kotlin_repositories() # if you want the default. Otherwise see custom kotlinc distribution below
kt_register_toolchains() # to use the default toolchain, otherwise see toolchains below

# Load the Android build rules
http_archive(
    name = "build_bazel_rules_android",
    urls = ["https://github.com/bazelbuild/rules_android/archive/v0.1.1.zip"],
    sha256 = "cd06d15dd8bb59926e4d65f9003bfc20f9da4b2519985c27e190cddc8b7a7806",
    strip_prefix = "rules_android-0.1.1",
)

# Configure Android SDK Path
load("@build_bazel_rules_android//android:rules.bzl", "android_sdk_repository")
android_sdk_repository(
    name = "androidsdk",
    path = "/Users/jackson/Library/Android/sdk",
)

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

RULES_JVM_EXTERNAL_TAG = "4.0"
RULES_JVM_EXTERNAL_SHA = "31701ad93dbfe544d597dbe62c9a1fdd76d81d8a9150c2bf1ecf928ecdf97169"

http_archive(
    name = "rules_jvm_external",
    sha256 = RULES_JVM_EXTERNAL_SHA,
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")


maven_install(
    artifacts = [
        "io.reactivex.rxjava2:rxandroid:2.1.1",
        "io.reactivex.rxjava2:rxjava:2.2.20",
        "androidx.appcompat:appcompat:1.2.0",
        "androidx.fragment:fragment:1.2.5",
        "androidx.recyclerview:recyclerview:1.1.0",
        "androidx.constraintlayout:constraintlayout:1.1.3",
        "com.trello.navi2:navi:2.1.0",
        "com.jakewharton.rxbinding3:rxbinding:3.1.0",
        "com.google.android.material:material:1.1.0"
    ],
    repositories = [
        "https://jcenter.bintray.com/",
        "https://maven.google.com",
        "https://repo1.maven.org/maven2",
    ],
)