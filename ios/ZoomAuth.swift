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

  var APP_TOKEN: String = ""
  var APP_STORE: String = ""
  var APP_USER: String = ""
  var APP_SECRET: String = ""
  var verifyResolver: RCTPromiseResolveBlock? = nil
  var verifyRejecter: RCTPromiseRejectBlock? = nil
  var initialized = false

//    func checkAuth() -> Bool {
//        return ZoomSDK.isUserEnrolled(userID: APP_USER)
//    }

  // React Method
  @objc func verify(_ resolve: @escaping RCTPromiseResolveBlock,
                      rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
    if self.verifyResolver != nil {
      let errorMsg = "one verify() at a time"
      let err: NSError = NSError(domain: errorMsg, code: 0, userInfo: nil)
      reject("VerifyPending", errorMsg, err)
      return
    }

    if !self.initialized {
      let errorMsg = "initialize() before verify()"
      let err: NSError = NSError(domain: errorMsg, code: 0, userInfo: nil)
      reject("NotInitialized", errorMsg, err)
      return
    }

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

  func onZoomVerificationResult(result: ZoomVerificationResult) {
    print("\(result.status)")

    if result.status == .FailedBecauseEncryptionKeyInvalid {
      let errorMsg = "you probably did not set a public key before attempting to retrieve a facemap. Retrieving facemaps requires that you generate a public/private key pair per the instructions at https://dev.zoomlogin.com/zoomsdk/#/hybrid-guide"
      let err: NSError = NSError(domain: errorMsg, code: 0, userInfo: nil)
      self.verifyRejecter!("EncryptionKeyInvalid", errorMsg, err)
    }
    // CASE: user performed a ZoOm and passed the liveness check
    else if result.status == .UserProcessedSuccessfully {
      let externalImageSetVerificationResult:[String:Any] = [
        "description": result.faceMetrics?.externalImageSetVerificationResult.description ?? ""
      ]

      let faceMetrics:[String:Any] = [
        "auditTrail": result.faceMetrics!.auditTrail?.map { uiImageToBase64(image: $0) } ?? [],
        "externalImageSetVerificationResult": externalImageSetVerificationResult,
        "livenessResult": result.faceMetrics!.livenessResult.description,
        "livenessScore": result.faceMetrics!.livenessScore
      ]

      let resultJson:[String:Any] = [
        "countOfZoomSessionsPerformed": result.countOfZoomSessionsPerformed,
        "faceMetrics": faceMetrics,
        "sessionId": result.sessionId
//        "description": result.description
      ]

      self.verifyResolver!(resultJson)
    }
    else {
      // handle other error
    }

    self.verifyResolver = nil
    self.verifyRejecter = nil
//    verifyResolver([
//      status: result.status,
//      countOfZoomSessions: result.countOfZoomSessionsPerformed
//    ])

    // EXAMPLE: retrieve audit trail and facemap
//    if let auditTrail = result.faceMetrics?.auditTrail {
//      print("Audit trail image count: (auditTrail.count)")
//      auditTrailImages = auditTrail
//    }
//
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

    if options["appToken"] == nil {
      let errorMsg = "expected appToken option"
      let err: NSError = NSError(domain: errorMsg, code: 0, userInfo: nil)
      reject("ExpectedToken", errorMsg, err)
      return
    }

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
          resolve(message)
        }
        else {
          let errorMsg = "AppToken did not validate.  If Zoom ViewController's are launched, user will see an app token error state"
          print(errorMsg)
          let err: NSError = NSError(domain: errorMsg, code: 0, userInfo: nil)
          reject("initialize", errorMsg, err)
        }
      }
    )
  }

}

