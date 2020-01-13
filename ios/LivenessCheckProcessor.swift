// Demonstrates performing a Liveness Check.

import UIKit
import Foundation
import ZoomAuthentication

class LivenessCheckProcessor: NSObject, URLSessionDelegate, ZoomFaceMapProcessorDelegate, ZoomSessionDelegate {
    var licenseKey: String!
    var delegate: ProcessingDelegate
    var latestZoomSessionResult: ZoomSessionResult?
    var isSuccess: Bool

    init(delegate: ProcessingDelegate, fromVC: UIViewController, options: Dictionary<String, Any>) {
        self.delegate = delegate
        self.isSuccess = false

        super.init()
        
        self.licenseKey = options["licenseKey"] as? String

        // Launch the ZoOm Session.
        let sessionVC = Zoom.sdk.createSessionVC(delegate: self, faceMapProcessorDelegate: self)
        if (options["useOverlay"] as? Bool)! {
            sessionVC.modalPresentationStyle = .overCurrentContext
        }
        
        fromVC.present(sessionVC, animated: true, completion: nil)
    }
    
    // Required function that handles calling ZoOm Server to get result and decides how to continue.
    func processZoomSessionResultWhileZoomWaits(zoomSessionResult: ZoomSessionResult, zoomFaceMapResultCallback: ZoomFaceMapResultCallback) {
        self.latestZoomSessionResult = zoomSessionResult
        // cancellation, timeout, etc.
        if zoomSessionResult.status != .sessionCompletedSuccessfully || zoomSessionResult.faceMetrics?.faceMap == nil {
            zoomFaceMapResultCallback.onFaceMapResultCancel();
            return
        }
        
        // Create and parse request to ZoOm Server.
        NetworkingHelpers.getLivenessCheckResponseFromZoomServer(
            urlSessionDelegate: self,
            licenseKey: self.licenseKey,
            zoomSessionResult: zoomSessionResult,
            resultCallback: { nextStep in
                if nextStep == .Succeed {
                    // Dynamically set the success message.
//                    ZoomCustomization.setOverrideResultScreenSuccessMessage("Liveness\nConfirmed")
                    zoomFaceMapResultCallback.onFaceMapResultSucceed()
                    self.isSuccess = true
                }
                else if nextStep == .Retry {
                    zoomFaceMapResultCallback.onFaceMapResultRetry()
                }
                else {
                    zoomFaceMapResultCallback.onFaceMapResultCancel()
                }
            }
        )
    }
    
    // iOS way to get upload progress and update ZoOm UI.
    func urlSession(_ session: URLSession, task: URLSessionTask, didSendBodyData bytesSent: Int64, totalBytesSent: Int64, totalBytesExpectedToSend: Int64) {
//        let uploadProgress: Float = Float(totalBytesSent) / Float(totalBytesExpectedToSend)
    }
    
    // The final callback ZoOm SDK calls when done with everything.
    func onZoomSessionComplete() {
        delegate.onProcessingComplete(isSuccess: isSuccess, zoomSessionResult: latestZoomSessionResult)
    }
}
