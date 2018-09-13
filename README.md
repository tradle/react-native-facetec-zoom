# react-native-facetec-zoom

this is a minimal wrapper around the [Zoom SDK](https://dev.zoomlogin.com/), that currently only runs the liveness detection check.

## Install

### Common

```sh
npm i -S tradle/react-native-facetec-zoom
react-native link react-native-facetec-zoom
```

### iOS

This library has been tested with version 6.8.0 of the SDK

First, download `ZoomAuthenticationHybrid.framework` from one of these sources:

- [Zoom SDK](https://dev.zoomlogin.com/zoomsdk/#/ios-guide)
- [app.tradle.io](https://s3.amazonaws.com/app.tradle.io/sdk/ZoomAuthenticationHybrid.framework-6.8.0.zip)

Unzip the file, locate `ZoomAuthenticationHybrid.framework` and place it in `node_modules/react-native-facetec-zoom/ios/`

### Android

in `android/setings.gradle` add these two lines in *addition* to the ones added by `react-native link`:

```gradle
include ':zoom-authentication-hybrid-6.8.0'
project(':zoom-authentication-hybrid-6.8.0').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-facetec-zoom/android/zoom-authentication-hybrid-6.8.0')
```
