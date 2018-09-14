//
//  ZoomAuth.swift
//  ZoomSdkExample
//
//  Created by Willian Angelo on 25/01/2018.
//  Copyright © 2018 Facebook. All rights reserved.
//

import UIKit
import ZoomAuthenticationHybrid

@objc(ZoomAuth)
class ZoomAuth:  RCTViewManager, ZoomVerificationDelegate {

  var verifyResolver: RCTPromiseResolveBlock? = nil
  var verifyRejecter: RCTPromiseRejectBlock? = nil
  var initialized = false

  // React Method
  @objc func verify(_ options: Dictionary<String, Any>, // options not used at the moment
                      resolver resolve: @escaping RCTPromiseResolveBlock,
                      rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
    self.verifyResolver = resolve
    self.verifyRejecter = reject
    DispatchQueue.main.async {
      let verificationVC = ZoomSDK.createVerificationVC(
        delegate: self,
        retrieveZoomBiometric: false
        //      retrieveZoomBiometric: true
      )

      let root = UIApplication.shared.keyWindow!.rootViewController!;
      root.present(verificationVC, animated: true, completion: nil)
    }
  }

  func getExternalImageSetVerificationResult(result: ZoomExternalImageSetVerificationResult) -> String {
    switch (result) {
      case .CouldNotDetermineMatch:
        return "CouldNotDetermineMatch"
      case .LowConfidenceMatch:
        return "LowConfidenceMatch"
      case .Match:
        return "Match";
    }
  }

  // surely there's an easier way...
  func getVerificationResultStatus(result: ZoomVerificationResult) -> String {
    switch(result.status) {
      case .FailedBecauseAppTokenNotValid:
        return "FailedBecauseAppTokenNotValid"
      case .FailedBecauseCameraPermissionDeniedByAdministrator:
        return "FailedBecauseCameraPermissionDeniedByAdministrator"
      case .UserProcessedSuccessfully:
        return "UserProcessedSuccessfully"
      case .UserNotProcessed:
        return "UserNotProcessed"
      case .FailedBecauseUserCancelled:
        return "FailedBecauseUserCancelled"
      case .FailedBecauseCameraPermissionDeniedByUser:
        return "FailedBecauseCameraPermissionDeniedByUser"
      case .FailedBecauseOfOSContextSwitch:
        return "FailedBecauseOfOSContextSwitch"
      case .FailedBecauseOfTimeout:
        return "FailedBecauseOfTimeout"
      case .FailedBecauseOfLowMemory:
        return "FailedBecauseOfLowMemory"
      case .FailedBecauseOfDiskWriteError:
        return "FailedBecauseOfDiskWriteError"
      case .FailedBecauseNoConnectionInDevMode:
        return "FailedBecauseNoConnectionInDevMode"
      case .FailedBecauseOfflineSessionsExceeded:
        return "FailedBecauseOfflineSessionsExceeded"
      case .FailedBecauseEncryptionKeyInvalid:
        return "FailedBecauseEncryptionKeyInvalid"
    }
  }

  func onZoomVerificationResult(result: ZoomVerificationResult) {
    print("\(result.status)")

    // CASE: user performed a ZoOm and passed the liveness check
    if result.status == .UserProcessedSuccessfully {
      let externalImageSetVerificationResult = result.faceMetrics?.externalImageSetVerificationResult
      var externalImageSetVerificationResultStr = "Unsupported"
      if externalImageSetVerificationResult != nil {
        externalImageSetVerificationResultStr = getExternalImageSetVerificationResult(result: externalImageSetVerificationResult!)
      }

      let faceMetrics:[String:Any] = [
        "externalImageSetVerificationResult": externalImageSetVerificationResultStr,
        "auditTrail": result.faceMetrics!.auditTrail?.map { uiImageToBase64(image: $0) } ?? [],
        "externalImageSetVerificationResult": externalImageSetVerificationResultStr,
        "livenessResult": result.faceMetrics!.livenessResult.description,
        "livenessScore": result.faceMetrics!.livenessScore
      ]

      let resultJson:[String:Any] = [
        "status": getVerificationResultStatus(result: result),
        "countOfZoomSessionsPerformed": result.countOfZoomSessionsPerformed,
        "faceMetrics": faceMetrics,
        "sessionId": result.sessionId
//        "description": result.description
      ]

      self.verifyResolver!(resultJson)
    }
    else {
      // handle other error
      self.verifyResolver!([
        "status": getVerificationResultStatus(result: result)
      ])
    }

    self.verifyResolver = nil
    self.verifyRejecter = nil

//    EXAMPLE: retrieve facemap
//    if let zoomFacemap = result.faceMetrics?.zoomFacemap {
//      // handle ZoOm Facemap
//    }
  }

  func uiImageToBase64 (image: UIImage) -> String {
    let imageData = UIImageJPEGRepresentation(image, 0.9)! as NSData;
    return imageData.base64EncodedString(options: [])
  }

  // React Method
  @objc func getVersion(_ resolve: RCTPromiseResolveBlock,
                        rejecter reject: RCTPromiseRejectBlock) -> Void {

      let result: String = ZoomSDK.version

      if ( !result.isEmpty ) {
          resolve([
              result: result
          ])
      } else {
          let errorMsg = "SDK Errror"
          let err: NSError = NSError(domain: errorMsg, code: 0, userInfo: nil)
          reject("getVersion", errorMsg, err)
      }
  }

  // React Method
  @objc func initialize(_ options: Dictionary<String, Any>,
                        resolver resolve: @escaping RCTPromiseResolveBlock,
                        rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {

    ZoomSDK.auditTrailType = .Height640
    let currentCustomization: ZoomCustomization = ZoomCustomization()
    currentCustomization.showZoomIntro = options["showZoomIntro"] as! Bool
    currentCustomization.showPreEnrollmentScreen = options["showPreEnrollmentScreen"] as! Bool
    currentCustomization.showUserLockedScreen = options["showUserLockedScreen"] as! Bool
    currentCustomization.showSuccessScreen = options["showSuccessScreen"] as! Bool
    currentCustomization.showFailureScreen = options["showFailureScreen"] as! Bool

//    currentCustomization.brandingLogo
    ZoomSDK.setCustomization(interfaceCustomization: currentCustomization)

    ZoomSDK.initialize(
      appToken: options["appToken"] as! String,
      completion: { (appTokenValidated: Bool) -> Void in
        //
        // We want to ensure that App Token is valid before enabling verification
        //
        if appTokenValidated {
          self.initialized = true
          let message = "AppToken validated successfully"
          print(message)
          resolve([
            "success": true
          ])
        }
        else {
          resolve([
            "success": false,
            "status": ZoomSDK.getStatus()
          ])

//          let errorMsg = "AppToken did not validate.  If Zoom ViewController's are launched, user will see an app token error state"
//          print(errorMsg)
//          let err: NSError = NSError(domain: errorMsg, code: 0, userInfo: nil)
//          reject("initialize", errorMsg, err)
        }
      }
    )
  }
}
