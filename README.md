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

This library has been tested with version 6.8.0 of the SDK

First, download `ZoomAuthenticationHybrid.framework` from one of these sources:

- [Zoom SDK](https://dev.zoomlogin.com/zoomsdk/#/ios-guide)
- [app.tradle.io](https://s3.amazonaws.com/app.tradle.io/sdk/ZoomAuthenticationHybrid.framework-6.8.0.zip)

Unzip the file, locate `ZoomAuthenticationHybrid.framework` and place it in `node_modules/react-native-facetec-zoom/ios/`

### Android

in `android/setings.gradle` add two lines *before* added by `react-native link`. Your final `settings.gradle` should look like this:

```gradle
...
// added by  you manually
include ':zoom-authentication-hybrid-6.8.0'
project(':zoom-authentication-hybrid-6.8.0').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-facetec-zoom/android/zoom-authentication-hybrid-6.8.0')
// added by react-native link
include ':react-native-facetec-zoom'
project(':react-native-facetec-zoom').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-facetec-zoom/android')
...
```

## Usage

```js
import Zoom from 'react-native-facetec-zoom'

const verifyLiveness = async () => {
  // ensure zoom is initialized
  // this only needs to be done once
  const { success, status } = await Zoom.initialize({
    appToken: '.. get this from https://dev.zoomlogin.com/ ..',
    // optional customization options
    // see defaults.js for the full list
    showZoomIntro: false,
    showPreEnrollmentScreen: false,
    showUserLockedScreen: false,
    showSuccessScreen: false,
    showFailureScreen: false,
  })

  if (!success) {
    // see constants.js SDKStatus for explanations of various
    // reasons why initialize might not have gone through
    throw new Error(`failed to init. SDK status: ${status}`)
  }  

  // launch Zoom's verification process
  const result = await Zoom.verify({
    // no options at this point
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
