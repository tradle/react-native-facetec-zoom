# react-native-facetec-zoom

unofficial react-native bridge for Facetec's [Zoom SDK](https://dev.zoomlogin.com/).

## Functions

- liveness detection
- ...TODO

## Install

### Common

```sh
npm i -S tradle/react-native-facetec-zoom
react-native link react-native-facetec-zoom
```

### iOS

This library has been tested with version 7.0.8 of the SDK

First, download `ZoomAuthenticationHybrid.framework` from one of these sources:

- [Zoom SDK](https://dev.zoomlogin.com/zoomsdk/#/ios-guide)
- [app.tradle.io](https://s3.amazonaws.com/app.tradle.io/sdk/ZoomAuthenticationHybrid.framework-7.0.8.zip)

Unzip the file, locate `ZoomAuthenticationHybrid.framework` and add it to your project (`Copy items if needed` should be checked)

Add a Copy File phase to your Xcode project and have `ZoomAuthenticationHybrid.framework` copied to Destination `Frameworks`

### Android

in `android/settings.gradle` add two lines *before* added by `react-native link`. Your final `settings.gradle` should look like this:

```gradle
...
// added by  you manually
include ':zoom-authentication-hybrid-7.0.8'
project(':zoom-authentication-hybrid-7.0.8').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-facetec-zoom/android/zoom-authentication-hybrid-7.0.8')
// added by react-native link
include ':react-native-facetec-zoom'
project(':react-native-facetec-zoom').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-facetec-zoom/android')
...
```

In your project's build.gradle (android/build.gradle), add the maven block below:

```gradle
allprojects {
  repositories {
//  ...
    maven {
        url 'http://maven.facetec.com'
    }
}
```

This module depends on [react-native-image-store](https://github.com/tradle/react-native-image-store), so you'll need to npm install and react-native link that one too.

## Usage

```js
import Zoom from 'react-native-facetec-zoom'

Zoom.preload() // as early as possible for best performance

const verifyLiveness = async () => {
  // ensure zoom is initialized
  // this only needs to be done once
  const { success, status } = await Zoom.initialize({
    appToken: '.. get this from https://dev.zoomlogin.com/ ..',
    // optional customization options
    // see defaults.js for the full list
    showPreEnrollmentScreen: false,
    showUserLockedScreen: false,
    showRetryScreen: false,
    enableLowLightMode: false,
    centerFrame: true,
  })

  if (!success) {
    // see constants.js SDKStatus for explanations of various
    // reasons why initialize might not have gone through
    throw new Error(`failed to init. SDK status: ${status}`)
  }  

  // launch Zoom's verification process
  const result = await Zoom.verify({
    // default to storing in ImageStoreManager to avoid sending base64 over bridge
    returnBase64: false,
    useOverlay: false,
  })

  // result looks like this:
  // {
  //   "countOfZoomSessionsPerformed": 1,
  //   "sessionId": "45D5D648-3B14-46B1-86B0-55A91AB9E7DD",
  //   "faceMetrics": {
  //     "livenessResult": "Alive",
  //     "livenessScore": 86.69999694824219,
  //     "auditTrail": [
  //       "..base64 image 1..",
  //       "..base64 image 2..",
  //       "..base64 image 3.."
  //     ],
  //     "externalImageSetVerificationResult": "CouldNotDetermineMatch"
  //   }
  // }
}
```
