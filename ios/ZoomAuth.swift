//
//  ZoomAuth.swift
//  ZoomSdkExample
//
//  Created by Willian Angelo on 25/01/2018.
//  Copyright © 2018 Facebook. All rights reserved.
//

import UIKit
import ZoomAuthentication

@objc(ZoomAuth)
class ZoomAuth:  RCTViewManager, ProcessingDelegate {

  var verifyResolver: RCTPromiseResolveBlock? = nil
  var verifyRejecter: RCTPromiseRejectBlock? = nil
  var returnBase64: Bool = false
  var initialized = false
  var licenseKey: String!

  func getRCTBridge() -> RCTBridge
  {
    let root = UIApplication.shared.keyWindow!.rootViewController!.view as! RCTRootView;
    return root.bridge;
  }

  // React Method
  @objc func verify(_ options: Dictionary<String, Any>, // options not used at the moment
                      resolver resolve: @escaping RCTPromiseResolveBlock,
                      rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
    self.verifyResolver = resolve
    self.verifyRejecter = reject
    self.returnBase64 = (options["returnBase64"] as? Bool)!
    DispatchQueue.main.async {
      let root = UIApplication.shared.keyWindow!.rootViewController!
      var optionsWithKey = options
      optionsWithKey["licenseKey"] = self.licenseKey
      let _ = LivenessCheckProcessor(delegate: self, fromVC: root, options: optionsWithKey)
    }
  }

  // Show the final result and transition back into the main interface.
  func onProcessingComplete(isSuccess: Bool, zoomSessionResult: ZoomSessionResult?) {
    let statusCode = zoomSessionResult?.status.rawValue ?? -1
    var resultJson:[String:Any] = [
      "success": isSuccess,
      "status": statusCode
    ]

    if (!isSuccess) {
      self.sendResult(resultJson)
      return
    }

    resultJson["countOfZoomSessionsPerformed"] = zoomSessionResult?.countOfZoomSessionsPerformed ?? 1
    resultJson["sessionId"] = zoomSessionResult?.sessionId ?? ""

    if zoomSessionResult?.faceMetrics == nil {
      self.sendResult(resultJson)
      return
    }

    let face = zoomSessionResult?.faceMetrics
    var faceMetrics: [String:Any] = [:]
    if self.returnBase64 && face?.faceMapBase64 != nil {
      faceMetrics["facemap"] = face!.faceMapBase64
    }

//    resultJson["faceMetrics"] = faceMetrics
    if face?.auditTrail == nil {
      self.sendResult(resultJson)
      return
    }

    let auditTrailImages = face!.auditTrail!
    var auditTrail:[String] = []
    if self.returnBase64 {
      if let auditTrailBase64 = face?.auditTrailCompressedBase64 {
        faceMetrics["auditTrail"] = auditTrailBase64
      }
      
      resultJson["faceMetrics"] = faceMetrics
      self.sendResult(resultJson)
      return
    }

    var togo = face?.auditTrailCompressedBase64?.count ?? 0
    if let faceMap = face?.faceMap {
      togo += 1
      storeDataInImageStore(faceMap) { (tag) in
        faceMetrics["facemap"] = tag
        togo -= 1
        if togo == 0 {
          resultJson["faceMetrics"] = faceMetrics
          self.sendResult(resultJson)
        }
      }
    }

    for image in auditTrailImages {
      uiImageToImageStoreKey(image) { (tag) in
        if (tag != nil) {
          auditTrail.append(tag!)
        }

        togo -= 1
        if togo == 0 {
          faceMetrics["auditTrail"] = auditTrail
          resultJson["faceMetrics"] = faceMetrics
          self.sendResult(resultJson)
        }
      }
    }

//    EXAMPLE: retrieve facemap
//    if let zoomFacemap = result.faceMetrics?.zoomFacemap {
//      // handle ZoOm Facemap
//    }

  }
  
  // Show the final result and transition back into the main interface.
  func onProcessingComplete(isSuccess: Bool, zoomSessionResult: ZoomSessionResult?, zoomIDScanResult: ZoomIDScanResult?) {
  }
  
  func sendResult(_ result: [String:Any]) -> Void {
    if (self.verifyResolver == nil) {
      return
    }

    self.verifyResolver!(result)
    self.cleanUp()
  }

  // not used at the moment
  func sendError(_ code: String, message: String, error: Error) -> Void {
    if (self.verifyRejecter == nil) {
      return
    }

    self.verifyRejecter!(code, message, error)
    self.cleanUp()
  }

  func cleanUp () -> Void {
    self.verifyResolver = nil
    self.verifyRejecter = nil
  }

  func uiImageToBase64 (_ image: UIImage) -> String {
    let imageData = image.jpegData(compressionQuality: 0.9)! as NSData;
    return imageData.base64EncodedString(options: [])
  }

  func uiImageToImageStoreKey (_ image: UIImage, completionHandler: @escaping (String?) -> Void) -> Void {
    let bridge = getRCTBridge()
    let imageStore: RCTImageStoreManager = bridge.imageStoreManager;
    imageStore.store(image, with: completionHandler)
  }

  func storeDataInImageStore (_ data: Data, completionHandler: @escaping (String?) -> Void) -> Void {
    let bridge = getRCTBridge()
    let imageStore: RCTImageStoreManager = bridge.imageStoreManager;
    imageStore.storeImageData(data, with: completionHandler)
  }

  // React Method
  @objc func preload() -> Void {
    Zoom.sdk.preload()
  }

  // React Method
  @objc func getVersion(_ resolve: RCTPromiseResolveBlock,
                        rejecter reject: RCTPromiseRejectBlock) -> Void {

      let result: String = Zoom.sdk.version

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
    self.licenseKey = options["licenseKey"] as? String

    if (options["facemapEncryptionKey"] != nil) {
      let publicKey = options["facemapEncryptionKey"] as! String
      Zoom.sdk.setFaceMapEncryptionKey(publicKey: publicKey);
    }

    Zoom.sdk.auditTrailType = .height640 // otherwise no auditTrail images

    // Create the customization object
    let currentCustomization: ZoomCustomization = ZoomCustomization()
    // disable the "Your App Logo" section
    currentCustomization.overlayCustomization.brandingImage = nil
//    currentCustomization.overlayCustomization.blurEffectOpacity = 0
//    currentCustomization.frameCustomization.blurEffectOpacity = 0
//    currentCustomization.guidanceCustomization.showIntroScreenBrandingImage = false

    // Sample UI Customization: vertically center the ZoOm frame within the device's display
//    if (options["centerFrame"] as? Bool)! {
//      centerZoomFrameCustomization(currentZoomCustomization: currentCustomization)
//    } else if (options["topMargin"] != nil) {
//      currentCustomization.frameCustomization.topMargin = options["topMargin"] as! Int32
//    }

    // Apply the customization changes
    Zoom.sdk.setCustomization(currentCustomization)
    Zoom.sdk.initialize(
      licenseKeyIdentifier: options["licenseKey"] as! String,
      completion: { (licenseKeyValidated: Bool) -> Void in
        //
        // We want to ensure that licenseKey is valid before enabling verification
        //
        if licenseKeyValidated {
          self.initialized = true
          let message = "licenseKey validated successfully"
          print(message)
          resolve([
            "success": true
          ])
        }
        else {
          let status = Zoom.sdk.getStatus().rawValue
          resolve([
            "success": false,
            "status": status
          ])

//          let errorMsg = "AppToken did not validate.  If Zoom ViewController's are launched, user will see an app token error state"
//          print(errorMsg)
//          let err: NSError = NSError(domain: errorMsg, code: 0, userInfo: nil)
//          reject("initialize", errorMsg, err)
        }
      }
    )
  }

//  func centerZoomFrameCustomization(currentZoomCustomization: ZoomCustomization) {
//    let screenHeight: CGFloat = UIScreen.main.fixedCoordinateSpace.bounds.size.height
//    var frameHeight: CGFloat = screenHeight * CGFloat(currentZoomCustomization.frameCustomization.sizeRatio)
//    // Detect iPhone X and iPad displays
//    if UIScreen.main.fixedCoordinateSpace.bounds.size.height >= 812 {
//      let screenWidth = UIScreen.main.fixedCoordinateSpace.bounds.size.width
//      frameHeight = screenWidth * (16.0/9.0) * CGFloat(currentZoomCustomization.frameCustomization.sizeRatio)
//    }
//    let topMarginToCenterFrame = (screenHeight - frameHeight)/2.0
//
//    currentZoomCustomization.frameCustomization.topMargin = Int32(topMarginToCenterFrame)
//  }
}
