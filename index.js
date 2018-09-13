import { NativeModules } from 'react-native'

const { RNReactNativeZoomSdk } = NativeModules

const ZoomAuth = {}
const customizationDefaults = {
  showZoomIntro: false,
  showPreEnrollmentScreen: false,
  showUserLockedScreen: false,
  showSuccessScreen: false,
  showFailureScreen: false,
  // mainBackgroundColors: [UIColor]
  // mainForegroundColor: UIColor
  // buttonTextNormalColor: UIColor
  // buttonBackgroundNormalColor: UIColor
  // buttonTextHighlightColor: UIColor
  // buttonBackgroundHighlightColor: UIColor
  // resultsScreenBackgroundColor: [UIColor]
  // resultsScreenForegroundColor: UIColor
  // progressBarColor: CAGradientLayer
  // progressTextColor: UIColor
  // progressSpinnerColor1: UIColor
  // progressSpinnerColor2: UIColor
  // tabBackgroundColor: UIColor
  // tabBackgroundSelectedColor: UIColor
  // tabTextColor: UIColor
  // tabTextSelectedColor: UIColor
  // tabTextSuccessColor: UIColor
  // tabBackgroundSuccessColor: UIColor
  // brandingLogo: UIImage?
  // cancelButtonImage: UIImage?
  // cancelButtonLocation: ZoomAuthenticationHybrid.CancelButtonLocation
  // zoomInstructionsImages: ZoomAuthenticationHybrid.ZoomInstructions
}

ZoomAuth.getVersion = () => RNReactNativeZoomSdk.getVersion()

ZoomAuth.initialize = opts => RNReactNativeZoomSdk.initialize({
  ...customizationDefaults,
  ...opts,
})

ZoomAuth.verify = () => RNReactNativeZoomSdk.verify()

export default ZoomAuth
