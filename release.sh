#!/bin/bash

# Build signed release APK and AAB
# Assumes signing is already configured in app/build.gradle

echo "Building release App Bundle and APK..."
./gradlew assembleRelease bundleRelease

echo "Done!"
echo "APK location: app/build/outputs/apk/release/"
echo "AAB location: app/build/outputs/bundle/release/"
