import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.example.footballmanager"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.footballmanager"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        // Load the values from .properties file if it exists, otherwise use environment variables
        val keystoreFile = project.rootProject.file("local.properties")
        val properties = Properties()

        if (keystoreFile.exists()) {
            properties.load(keystoreFile.inputStream())
        }

        // Return empty key in case something goes wrong or use environment variables
        val rapidApiKey = properties.getProperty("RAPIDAPI_KEY") ?: System.getenv("RAPIDAPI_KEY") ?: ""
        val rapidApiHost = properties.getProperty("RAPIDAPI_HOST") ?: System.getenv("RAPIDAPI_HOST") ?: ""
        val baseUrl = properties.getProperty("BASE_URL") ?: System.getenv("BASE_URL") ?: ""


        buildConfigField(
            type = "String",
            name = "RAPIDAPI_KEY",
            value = rapidApiKey
        )
        buildConfigField(
            type = "String",
            name = "RAPIDAPI_HOST",
            value = rapidApiHost
        )
        buildConfigField(
            type = "String",
            name = "BASE_URL",
            value = baseUrl
        )
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
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Retrofit
    implementation(libs.retrofit)
    //Serialization
    implementation(libs.converter.gson)
    //Scalars
    implementation(libs.converter.scalars)
    //viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    //loading images from url
    implementation(libs.coil.compose)
    //Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.runtime.livedata)
    kapt(libs.hilt.compiler)
    //hilt fragment
    implementation(libs.androidx.hilt.navigation.compose)
    //navigation bar
    implementation(libs.androidx.navigation.compose)
    //room
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //compose
    debugImplementation(libs.ui.test.manifest)
    debugImplementation(libs.ui.tooling)
}
