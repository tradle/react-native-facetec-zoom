export const SDKStatus = {
  /**
   Initialize was never attempted
   */
  NeverInitialized: 'NeverInitialized'

  /**
   The app token provided was verified
   */
  Initialized: 'Initialized'

  /**
   The app token could not be verified
   */
  NetworkIssues: 'NetworkIssues'

  /**
   The app token provided was invalid
   */
  InvalidToken: 'InvalidToken'

  /**
   The current version of the SDK is deprecated
   */
  VersionDeprecated: 'VersionDeprecated'

  /**
   The app token needs to be verified again
   */
  OfflineSessionsExceeded: 'OfflineSessionsExceeded'

  /**
   An unknown error occurred
   */
  UnknownError: 'UnknownError'
}

export const Verification = {
  // from wrapper

  NeverInitialized: 'NeverInitialized',

  VerifyPending: 'VerifyPending',

  // from SDK

  /**
   The user was successfully processed.
   */
  UserProcessedSuccessfully: 'UserProcessedSuccessfully',

  /**
   The user was not processed successuflly.
   */
  UserNotProcessed: 'UserNotProcessed',

  /**
   The user prematurely cancelled out of the enrollment session
   */
  FailedBecauseUserCancelled: 'FailedBecauseUserCancelled',

  /**
   The operation could not be completed because the SDK was not validated prior to use.
   */
  FailedBecauseAppTokenNotValid: 'FailedBecauseAppTokenNotValid',

  /**
   The camera access is prevented by user setting
   */
  FailedBecauseCameraPermissionDeniedByUser: 'FailedBecauseCameraPermissionDeniedByUser',

  /**
   The camera access is prevented by administrator policy
   */
  FailedBecauseCameraPermissionDeniedByAdministrator: 'FailedBecauseCameraPermissionDeniedByAdministrator',

  /**
   Verification was terminated due to the app being terminated or put to sleep
   */
  FailedBecauseOfOSContextSwitch: 'FailedBecauseOfOSContextSwitch',

  /**
   Verification failed due to user exceeding the timeout limit
   */
  FailedBecauseOfTimeout: 'FailedBecauseOfTimeout',

  /**
   Verification failed due to low memory
   */
  FailedBecauseOfLowMemory: 'FailedBecauseOfLowMemory',

  /**
   An error occurred while writing the biometric to disk -- this most likely occurs if user device storage is full
   */
  FailedBecauseOfDiskWriteError: 'FailedBecauseOfDiskWriteError',

  /**
   Verification failed because there was no network connection in development mode
   */
  FailedBecauseNoConnectionInDevMode: 'FailedBecauseNoConnectionInDevMode',

  /**
   Verification failed because too many sessions have occurred since the AppToken was last validated
   */
  FailedBecauseOfflineSessionsExceeded: 'FailedBecauseOfflineSessionsExceeded',

  /**
   Verification failed because the Zoom Hybrid encryption key is required but not set correctly.
   */
  FailedBecauseEncryptionKeyInvalid: 'FailedBecauseEncryptionKeyInvalid',

}
