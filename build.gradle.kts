import java.net.URI

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.0")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = URI("https://dl.bintray.com/rikkaw/Libraries") }
    }
}

task("clean", type = Delete::class) {
    delete(rootProject.buildDir)
}

gradle.projectsEvaluated {
    for ( variant in listOf("Release", "Debug") ) {
        project("module").tasks["assemble$variant"]
                .dependsOn(project(":app").tasks["assemble$variant"])
    }
}