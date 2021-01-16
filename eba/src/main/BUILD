load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_android_library")
load("@build_bazel_rules_android//android:rules.bzl", "android_library")

kt_android_library(
    name = "EBA_lib",
    visibility = ["//visibility:public"],
    srcs = glob(["**/*.kt"]),
    resource_files = glob(["res/**"]),
    custom_package="ru.tinkoff.eba",
    manifest = "AndroidManifest.xml",
    deps = [
            "@maven//:io_reactivex_rxjava2_rxjava",
            "@maven//:io_reactivex_rxjava2_rxandroid",
        ],
)

