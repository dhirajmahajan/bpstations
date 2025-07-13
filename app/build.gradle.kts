@file:Suppress("UNUSED_EXPRESSION")

import com.android.build.api.dsl.Lint
import groovy.lang.ExpandoMetaClassCreationHandle.disable

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.bpstations"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.bpstations"
        minSdk = 31
        //noinspection EditedTargetSdkVersion
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }


    lint {
        checkReleaseBuilds = false
        disable.addAll(listOf("NullSafeMutableLiveData", "MissingTranslation"))
    }

    packaging {
        resources {
            excludes += setOf(
                "com/sun/jna/platform/win32/COM/tlb/imp/TlbFunctionVTable.template",
                "com/sun/jna/platform/win32/COM/tlb/imp/TlbFunctionDispId.template",
                "com/sun/jna/platform/win32/COM/tlb/imp/TlbInterface.template",
                "com/sun/jna/platform/win32/COM/tlb/imp/TlbPropertyPutStub.template",
                "com/sun/jna/platform/win32/COM/tlb/imp/TlbPropertyGetStub.template",
                "com/sun/jna/platform/win32/COM/tlb/imp/TlbCoClass.template",
                "com/sun/jna/platform/win32/COM/tlb/imp/TlbFunctionStub.template",
                "com/sun/jna/platform/win32/COM/tlb/imp/TlbPropertyGet.template",
                "com/sun/jna/platform/win32/COM/tlb/imp/TlbPropertyPut.template",
                "com/sun/jna/win32-x86-64/jnidispatch.dll",
                "com/sun/jna/platform/win32/COM/tlb/imp/TlbDispInterface.template",
                "com/sun/jna/win32-x86/jnidispatch.dll",
                "com/sun/jna/aix-ppc/libjnidispatch.a",
                "com/sun/jna/aix-ppc/libjnidispatch.a",
                "com/sun/jna/aix-ppc/libjnidispatch.a",
                "META-INF/LICENSE.md",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/DEPENDENCIES",
                "META-INF/AL2.0",
                "META-INF/LGPL2.1",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/NOTICE.md",
                "META-INF/NOTICE.html",
                "META-INF/NOTICE.rst",
                "xsd/catalog.xml",
                "xsd/relaxng/catalog.xml",
                "xsd/relaxng/relaxng.rnc",
                "DebugProbesKt.bin",
                "META-INF/INDEX.LIST",
                "META-INF/INDEX.LIST.LICENSE",
                "META-INF/INDEX.LIST.NOTICE",
                "META-INF/INDEX.LIST.AL2.0",
                "META-INF/INDEX.LIST.LGPL2.1",
                "com/sun/jna/aix-ppc/libjnidispatch.a",
                "com/sun/jna/platform/win32/COM/tlb/imp/TlbFunctionVTable.template"


            )
        }
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.play.services.maps)
    implementation(libs.androidx.lifecycle.viewmodel.ktx) // Often needed with ViewModels
    implementation (libs.androidx.activity.ktx) // or newer
    implementation (libs.androidx.fragment.ktx)
    implementation(libs.support.annotations) // or newer
    testImplementation(libs.junit)
    implementation(libs.gson) // For JSON parsing
//    implementation("com.android.tools.lint:lint-api:30.3.0")
//    {
//        exclude(group = "net.java.dev.jna", module = "jna")
//        exclude(group = "net.java.dev.jna", module = "jna-platform")
//    }
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}