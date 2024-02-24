import org.apache.tools.ant.util.JavaEnvUtils.VERSION_1_8

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-android")
    id ("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.jikisan.phheroesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jikisan.phheroesapp"
        minSdk = 25
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
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

kotlin{
    ext["compose_version"] = "1.1.1"
    ext["accompanist_version"] = "0.24.0-beta"
    ext["kotlin_version"] = "1.6.21"
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.wear.compose:compose-material:1.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //Compose Nav
    implementation("androidx.navigation:navigation-compose:2.7.7")

    //Room components
    implementation ("androidx.room:room-runtime:2.6.1")
//    kapt("androidx.room:room-compiler:2.6.1") // Use kapt for Gradle
    implementation ("androidx.room:room-ktx:2.6.1")


// Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

    // Paging 3.0
    implementation("androidx.paging:paging-compose:3.3.0-alpha03")

    // KotlinX Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // DataStore Preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Dagger - Hilt
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0-rc01")
    implementation("com.google.dagger:hilt-android:2.49")
    implementation("androidx.room:room-paging:2.5.0")
    runtimeOnly("androidx.hilt:hilt-navigation-compose:1.1.0")
    kapt("com.google.dagger:hilt-compiler:2.44")
    ksp("androidx.room:room-compiler:2.5.0")
//    kapt("com.google.dagger:hilt-android-compiler:2.38.1")
//    kapt("androidx.hilt:hilt-compiler:1.1.0")
//    kapt ("com.google.dagger:hilt-android-compiler:1.1.0")

    // Coil
    implementation("io.coil-kt:coil-compose:1.3.2")

    // Accompanist
//    implementation("com.google.accompanist:accompanist-pager:0.24.0-beta")
//    implementation("com.google.accompanist:accompanist-pager-indicators:0.24.0-beta")
//    implementation("com.google.accompanist:accompanist-swiperefresh:0.24.0-beta")
//    implementation("com.google.accompanist:accompanist-microcontroller:0.24.0-beta")
    runtimeOnly("com.google.accompanist:accompanist-pager:0.30.1")
    runtimeOnly("com.google.accompanist:accompanist-pager-indicators:0.31.5-beta")
    runtimeOnly("com.google.accompanist:accompanist-swiperefresh:0.32.0")
    runtimeOnly("com.google.accompanist:accompanist-systemuicontroller:0.32.0")

    // For PageIndicator
//    implementation("androidx.compose.material:material-indicators:1.3.0-beta01")
    runtimeOnly("org.jetbrains.compose.material:material:1.6.0-rc03")
    runtimeOnly("com.google.android.material:material:1.11.0")
    implementation("androidx.compose.material:material:1.7.0-alpha03")
    implementation("androidx.compose.material3:material3:1.1.2")

    runtimeOnly("com.google.android.material:material:1.11.0")


    // Palette API
    implementation("androidx.palette:palette-ktx:1.0.0")
//
//    // Testing
//    androidTestImplementation("androidx.test:runner:1.5.2")
//    androidTestImplementation("androidx.test:rules:1.5.0")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.1")
//    //noinspection GradleDependency
//    debugImplementation("androidx.compose.ui:ui-test-manifest:1.1.1")
//
//    testImplementation("junit:junit:4.13.2")
//    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.9.22")
//    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

}