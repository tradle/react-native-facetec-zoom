import { NativeModules } from 'react-native'

const { RNReactNativeZoomSdk } = NativeModules

const ZoomAuth = {}

ZoomAuth.getVersion = () => RNReactNativeZoomSdk.getVersion()

ZoomAuth.initialize = appToken => RNReactNativeZoomSdk.initialize(appToken)

ZoomAuth.verify = () => RNReactNativeZoomSdk.verify().then(result => {
  debugger
  console.log(JSON.stringify(result, null, 2))
  return result
})

export default ZoomAuth
