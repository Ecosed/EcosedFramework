plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "io.ecosed.framework"
    compileSdk = 33

    defaultConfig {
        applicationId = "io.ecosed.framework"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

//        splits {
//            abi {
//                isEnable = true
//                reset()
//                include("x86", "x86_64")
//                isUniversalApk = false
//            }
//        }

        ndk {
            // Filter for architectures supported by Flutter.
            abiFilters.add("armeabi-v7a")
            abiFilters.add("arm64-v8a")
            abiFilters.add("x86_64")
            abiFilters.add("x86")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
        aidl = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // 第三方库
    // FlutterBoost: https://github.com/alibaba/flutter_boost
    implementation(dependencyNotation = project(path = ":flutter_boost"))
    // Flutter: https://github.com/flutter/flutter
    implementation(dependencyNotation = project(path = ":flutter"))
    // AndroidUtilCode: https://github.com/Blankj/AndroidUtilCode
    implementation(dependencyNotation = "com.blankj:utilcodex:1.31.1")
    // DialogX: https://github.com/kongzue/DialogX
    implementation(dependencyNotation = "com.kongzue.dialogx:DialogX:0.0.48")
    implementation(dependencyNotation = "com.kongzue.dialogx.style:DialogXMaterialYouStyle:0.0.48")
    // LicensesDialog: https://github.com/PSDev/LicensesDialog
    implementation(dependencyNotation = "de.psdev.licensesdialog:licensesdialog:2.2.0")
    // Shizuku-API: https://github.com/RikkaApps/Shizuku-API
    implementation(dependencyNotation = "dev.rikka.shizuku:api:13.1.1")
    implementation(dependencyNotation = "dev.rikka.shizuku:provider:13.1.1")
    // RikkaX: https://github.com/RikkaApps/RikkaX
    implementation(dependencyNotation = "dev.rikka.rikkax.appcompat:appcompat:1.6.1")
    implementation(dependencyNotation = "dev.rikka.rikkax.buildcompat:buildcompat:34.0.1")
    implementation(dependencyNotation = "dev.rikka.rikkax.core:core-ktx:1.4.1")
    implementation(dependencyNotation = "dev.rikka.rikkax.insets:insets:1.3.0")
    implementation(dependencyNotation = "dev.rikka.rikkax.layoutinflater:layoutinflater:1.3.0")
    implementation(dependencyNotation = "dev.rikka.rikkax.material:material:2.7.0")
    implementation(dependencyNotation = "dev.rikka.rikkax.material:material-preference:2.0.0")
    implementation(dependencyNotation = "dev.rikka.rikkax.parcelablelist:parcelablelist:2.0.1")
    implementation(dependencyNotation = "dev.rikka.rikkax.recyclerview:recyclerview-ktx:1.3.1")
    implementation(dependencyNotation = "dev.rikka.rikkax.widget:borderview:1.1.0")
    implementation(dependencyNotation = "dev.rikka.rikkax.widget:mainswitchbar:1.0.2")
    // AndroidHiddenApiBypass: https://github.com/LSPosed/AndroidHiddenApiBypass
    implementation(dependencyNotation = "org.lsposed.hiddenapibypass:hiddenapibypass:4.3")
    // JetBrains 官方库
    implementation(dependencyNotation = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.21")
    implementation(dependencyNotation = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.21")
    // Google 官方库
    implementation(dependencyNotation = "androidx.annotation:annotation:1.6.0")
    implementation(dependencyNotation = "androidx.browser:browser:1.5.0")
    implementation(dependencyNotation = "androidx.preference:preference-ktx:1.2.0")
    implementation(dependencyNotation = "androidx.viewpager2:viewpager2:1.0.0")
    implementation(dependencyNotation = "androidx.recyclerview:recyclerview:1.3.0")
    implementation(dependencyNotation = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation(dependencyNotation = "androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation(dependencyNotation = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation(dependencyNotation = "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation(dependencyNotation = "androidx.activity:activity-compose:1.7.2")
    implementation(dependencyNotation = "com.google.android.gms:play-services-base:18.2.0")
    implementation(dependencyNotation = "com.google.accompanist:accompanist-webview:0.30.1")
    implementation(dependencyNotation = "com.google.accompanist:accompanist-systemuicontroller:0.30.1")
    implementation(dependencyNotation = "com.google.accompanist:accompanist-adaptive:0.30.1")
    implementation(dependencyNotation = platform(notation = "androidx.compose:compose-bom:2023.03.00"))
    implementation(dependencyNotation = "androidx.compose.ui:ui")
    implementation(dependencyNotation = "androidx.compose.ui:ui-graphics")
    implementation(dependencyNotation = "androidx.compose.ui:ui-tooling-preview")
    implementation(dependencyNotation = "androidx.compose.material3:material3")
    implementation(dependencyNotation = "androidx.compose.material:material-icons-core:1.4.3")
    implementation(dependencyNotation = "androidx.compose.material:material-icons-extended:1.4.3")
    implementation(dependencyNotation = "androidx.navigation:navigation-compose:2.6.0")
    // 测试和调试
    testImplementation(dependencyNotation = "junit:junit:4.13.2")
    androidTestImplementation(dependencyNotation = "androidx.test.ext:junit:1.1.5")
    androidTestImplementation(dependencyNotation = "androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(dependencyNotation = platform(notation = "androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation(dependencyNotation = "androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(dependencyNotation = "androidx.navigation:navigation-testing:2.6.0")
    debugImplementation(dependencyNotation = "androidx.compose.ui:ui-tooling")
    debugImplementation(dependencyNotation = "androidx.compose.ui:ui-test-manifest")
}

configurations.all {
    exclude(group = "androidx.annotation", module = "annotation")
    exclude(group = "androidx.appcompat", module = "appcompat")
    exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk7")
    exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk8")
}