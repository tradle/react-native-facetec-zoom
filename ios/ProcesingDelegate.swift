// Helpful interfaces and enums

import ZoomAuthentication

protocol ProcessingDelegate: class {
    func onProcessingComplete(isSuccess: Bool, zoomSessionResult: ZoomSessionResult?)
    func onProcessingComplete(isSuccess: Bool, zoomSessionResult: ZoomSessionResult?, zoomIDScanResult: ZoomIDScanResult?)
}
