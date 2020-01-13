const statuses = [
  /**
   * completed but somethinf went wrong
   */
  "SessionCompletedSuccessfully",
  /**
   The ZoOm Session was not performed successfully and a FaceMap was not generated.  In general, other statuses will be sent to the developer for specific unsuccess reasons.
  */
  "SessionUnsuccessful",
  /**
   The user pressed the cancel button and did not complete the ZoOm Session.
  */
  "UserCancelled",
  /**
   This status will never be returned in a properly configured or production app.
  This status is returned if your license is invalid or network connectivity issues occur during a session when the application is not in production.
  */
  "NonProductionModeLicenseInvalid",
  /**
   The camera access is prevented because either the user has explicitly denied permission or the user's device is configured to not allow access by a device policy.
  For more information on restricted by policy case, please see the the Apple Developer documentation on AVAuthorizationStatus.restricted.
  */
  "CameraPermissionDenied",
  /**
   The ZoOm Session was cancelled due to the app being terminated, put to sleep, an OS notification, or the app was placed in the background.
  */
  "ContextSwitch",
  /**
   The ZoOm Session was cancelled because device is in landscape mode.
  The user experience of devices in these orientations is poor and thus portrait is required.
  */
  "LandscapeModeNotAllowed",
  /**
   The ZoOm Session was cancelled because device is in reverse portrait mode.
  The user experience of devices in these orientations is poor and thus portrait is required.
  */
  "ReversePortraitNotAllowed",
  /**
   The ZoOm Session was cancelled because the user was unable to complete a ZoOm Session in the default allotted time or the timeout set by the developer.
  */
  "Timeout",
  /**
   The ZoOm Session was cancelled due to memory pressure.
  */
  "LowMemory",
  /**
   The ZoOm Session was cancelled because your App is not in production and requires a network connection.
  */
  "NonProductionModeNetworkRequired",
  /**
   The ZoOm Session was cancelled because your License needs to be validated again.
  */
  "GracePeriodExceeded",
  /**
   The ZoOm Session was cancelled because the developer-configured encryption key was not valid.
  */
  "EncryptionKeyInvalid",
  /**
   The ZoOm Session was cancelled because not all guidance images were configured.
  */
  "MissingGuidanceImages",
  /**
   The ZoOm Session was cancelled because ZoOm was unable to start the camera on this device.
  */
  "CameraInitializationIssue",
  /**
   The ZoOm Session was cancelled because the user was in a locked out state.
  */
  "LockedOut",
  /**
   The ZoOm Session was cancelled because of an unknown and unexpected error.  ZoOm leverages a variety of iOS APIs including camera, storage, security, networking, and more.
  This return value is a catch-all for errors experienced during normal usage of these APIs.
  */
  "UnknownInternalError"
]

export default idx => statuses[idx]
