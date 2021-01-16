load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_android_library")
load("@build_bazel_rules_android//android:rules.bzl", "android_library")

kt_android_library(
    name = "EBA_sample",
    srcs = glob(["**/*.kt"]),
    resource_files = glob(["res/**"]),
    custom_package="ru.tinkoff.eba",
    manifest = "AndroidManifest.xml",
    deps = [
            "@maven//:io_reactivex_rxjava2_rxjava",
            "@maven//:io_reactivex_rxjava2_rxandroid",
            "@maven//:androidx_appcompat_appcompat",
            "@maven//:androidx_fragment_fragment",
            "@maven//:androidx_recyclerview_recyclerview",
            "@maven//:androidx_constraintlayout_constraintlayout",
            "@maven//:com_trello_navi2_navi",
            "@maven//:com_jakewharton_rxbinding3_rxbinding",
            "@maven//:com_google_android_material_material",
             "//eba/src/main:EBA_lib"
        ],
)

android_binary(
    name = "EBA_app",
    manifest = "AndroidManifest.xml",
    deps = [":EBA_sample"]
)

