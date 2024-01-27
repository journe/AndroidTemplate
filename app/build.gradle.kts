plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id("kotlin-kapt")
	id("com.google.dagger.hilt.android")
	id("com.google.devtools.ksp")
}

android {
	namespace = "tech.jour.template"

	compileSdk = 34

	defaultConfig {
		applicationId = "tech.jour.template"
		minSdk = 26
		targetSdk = 33
		versionCode = 1
		versionName = "1.0"
	}

	buildTypes {
		debug {
			isMinifyEnabled = false
			buildConfigField("String", "VERSION_TYPE", "\"VERSION_STATUS_ALPHA\"")
		}
		release {
			isMinifyEnabled = true
			buildConfigField("String", "VERSION_TYPE", "\"VERSION_STATUS_RELEASE\"")
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
		viewBinding = true
		buildConfig = true
	}

}

dependencies {

	implementation("androidx.core:core-ktx:1.12.0")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("com.google.android.material:material:1.11.0")
	implementation("androidx.activity:activity-ktx:1.8.2")
	implementation("androidx.fragment:fragment-ktx:1.6.2")
	implementation("androidx.annotation:annotation:1.7.1")
	implementation("com.google.code.gson:gson:2.10")
	implementation("androidx.collection:collection-ktx:1.4.0")

	val navVersion = "2.7.6"

	// Kotlin
	implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
	implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

	implementation("androidx.viewpager2:viewpager2:1.0.0")
	implementation("androidx.recyclerview:recyclerview:1.3.2")
	implementation("androidx.constraintlayout:constraintlayout:2.1.4")

	implementation("androidx.paging:paging-runtime-ktx:3.2.1")

	val lifecycleVersion = "2.7.0"

	implementation("androidx.lifecycle:lifecycle-process:$lifecycleVersion")
	implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
	implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")
	implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
	implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

	val roomVersion = "2.6.1"
	implementation("androidx.room:room-runtime:$roomVersion")
	annotationProcessor("androidx.room:room-compiler:$roomVersion")
	// To use Kotlin annotation processing tool (kapt)
	kapt("androidx.room:room-compiler:$roomVersion")
	// To use Kotlin Symbol Processing (KSP)
//	ksp("androidx.room:room-compiler:$roomVersion")

	// optional - Kotlin Extensions and Coroutines support for Room
	implementation("androidx.room:room-ktx:$roomVersion")
	// optional - Paging 3 Integration
	implementation("androidx.room:room-paging:$roomVersion")

	implementation("com.google.dagger:hilt-android:2.44")
	kapt("com.google.dagger:hilt-compiler:2.44")

	implementation("com.google.auto.service:auto-service:1.0")
	kapt("com.google.auto.service:auto-service-annotations:1.0")

	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.retrofit2:converter-gson:2.9.0")
	implementation("com.squareup.okhttp3:okhttp:4.9.3")
	implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

	implementation("io.coil-kt:coil:2.2.2")
	implementation("io.coil-kt:coil-gif:2.2.2")

	implementation("cat.ereza:customactivityoncrash:2.3.0")
	implementation("com.github.journe:Android-logger:v2.2.1")

	debugImplementation("com.guolindev.glance:glance:1.1.0")
	implementation("com.guolindev.permissionx:permissionx:1.7.1")

	implementation("com.github.li-xiaojun:XPopup:2.7.7")

	implementation("com.blankj:utilcodex:1.31.1")
	implementation("com.github.lygttpod:SuperTextView:2.4.6")
	implementation("com.github.journe:FlycoTabLayout:2.1.0")
	implementation("io.github.jeremyliao:live-event-bus-x:1.8.0")
}
kapt {
	correctErrorTypes = true
}