# General rules
-keep class com.sumit.daggerHiltStructure.** { *; }
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

# Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keepclassmembers class ** {
    @dagger.hilt.android.lifecycle.HiltViewModel *;
}
-keep class dagger.** { *; }
-keep class com.google.dagger.** { *; }

# Retrofit & Gson
-keep interface com.squareup.retrofit2.** { *; }
-keep class com.google.gson.** { *; }
-keep class **$$JsonAdapter { *; }
-keepclassmembers class * {
    @retrofit2.http.* <methods>;
}

# Room
-keep class androidx.room.** { *; }
-keep @androidx.room.* public class * { *; }
-keepclassmembers class * {
    @androidx.room.Database *;
    @androidx.room.Entity *;
    @androidx.room.Dao *;
}

# Coroutines
-keepclassmembers class kotlinx.coroutines.** { *; }

# Navigation
-keep class androidx.navigation.** { *; }

# AndroidX
-keep class androidx.** { *; }

# Debugging
-keepattributes SourceFile,LineNumberTable
