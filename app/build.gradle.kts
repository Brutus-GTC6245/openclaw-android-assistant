plugins {
    id(&quot;com.android.application&quot;)
    id(&quot;org.jetbrains.kotlin.android&quot;)
}

android {
    namespace = &quot;ai.openclaw.assistant&quot;
    compileSdk = 34

    defaultConfig {
        applicationId = &quot;ai.openclaw.assistant&quot;
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = &quot;1.0&quot;

        testInstrumentationRunner = &quot;androidx.test.runner.AndroidJUnitRunner&quot;
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile(&quot;proguard-android-optimize.txt&quot;), &quot;proguard-rules.pro&quot;)
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = &quot;1.8&quot;
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(&quot;androidx.core:core-ktx:1.12.0&quot;)
    implementation(&quot;androidx.appcompat:appcompat:1.6.1&quot;)
    implementation(&quot;com.google.android.material:material:1.11.0&quot;)
    implementation(&quot;androidx.constraintlayout:constraintlayout:2.1.4&quot;)
    
    // WebSocket
    implementation(&quot;org.java-websocket:Java-WebSocket:1.5.4&quot;)
    
    // Room DB
    val room_version = &quot;2.6.1&quot;
    implementation(&quot;androidx.room:room-runtime:$room_version&quot;)
    implementation(&quot;androidx.room:room-ktx:$room_version&quot;)
    kapt(&quot;androidx.room:room-compiler:$room_version&quot;)
    
    // Lifecycle
    implementation(&quot;androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0&quot;)
    implementation(&quot;androidx.lifecycle:lifecycle-livedata-ktx:2.7.0&quot;)
    
    // Coroutines
    implementation(&quot;org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3&quot;)
    
    testImplementation(&quot;junit:junit:4.13.2&quot;)
    androidTestImplementation(&quot;androidx.test.ext:junit:1.1.5&quot;)
    androidTestImplementation(&quot;androidx.test.espresso:espresso-core:3.5.1&quot;)
}