import { NativeModules } from 'react-native'
import { SDKStatus, VerificationStatus } from './constants'
import { customization } from './defaults'
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
      ...customization,
      ...opts,
    })

    initialized = result.success
    return result
  }

  const getVersion = () => native.getVersion()
  const verify = async (opts={}) => {
    if (!initialized) {
      throw new NotInitializedError('initialize me first!')
    }

    if (verifying) {
      throw new VerificationPendingError('only one verification can be done at a time')
    }

    try {
      return await native.verify({
        ...customization,
        ...opts,
      })
    } finally {
      verifying = false
    }
  }

  return {
    initialize,
    verify,
  }
}

const zoomAuth = wrapNative(NativeModules.RNReactNativeZoomSdk)
export default zoomAuth
