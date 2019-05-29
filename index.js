import { NativeModules } from 'react-native'
import { SDKStatus, VerificationStatus } from './constants'
import * as defaults from './defaults'
import { VerificationPendingError, NotInitializedError } from './errors'

export const status = {
  sdk: SDKStatus,
  verification: VerificationStatus,
}

const wrapNative = native => {
  let initialized
  let verifying

  const initialize = async opts => {
    initialized = false

    const result = await native.initialize({
      ...defaults.initialize,
      ...opts,
    })

    initialized = result.success
    return result
  }

  const getVersion = () => native.getVersion()
  const verify = async (opts = {}) => {
    if (!initialized) {
      throw new NotInitializedError('initialize me first!')
    }

    if (verifying) {
      throw new VerificationPendingError('only one verification can be done at a time')
    }

    try {
      return await native.verify({
        ...defaults.verify,
        ...opts,
      })
    } finally {
      verifying = false
    }
  }

  return {
    preload: native.preload,
    initialize,
    verify,
    getVersion,
  }
}

const zoomAuth = wrapNative(NativeModules.RNReactNativeZoomSdk)
export default zoomAuth
