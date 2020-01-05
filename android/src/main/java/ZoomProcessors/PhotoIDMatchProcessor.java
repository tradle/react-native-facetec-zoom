// Demonstrates performing a ZoOm Session, proving Liveness, then scanning the ID and performing a Photo ID Match

package ZoomProcessors;

import android.content.Context;
import org.json.JSONObject;
import static java.util.UUID.randomUUID;

import com.facetec.zoom.sdk.*;

public class PhotoIDMatchProcessor extends Processor implements ZoomFaceMapProcessor, ZoomIDScanProcessor {
    ZoomFaceMapResultCallback zoomFaceMapResultCallback;
    ZoomSessionResult latestZoomSessionResult;

    ZoomIDScanResultCallback zoomIDScanResultCallback;
    ZoomIDScanResult latestZoomIDScanResult;
    private boolean _isSuccess = false;

    public PhotoIDMatchProcessor(Context context) {
        // For demonstration purposes, generate a new uuid for each Photo ID Match.  Enroll this in the DB and compare against the ID after it is scanned.
        ZoomGlobalState.randomUsername = "android_sample_app_" + randomUUID();
        ZoomGlobalState.isRandomUsernameEnrolled = false;

        // Launch the ZoOm Session.
        ZoomSessionActivity.createAndLaunchZoomSession(context, this, this);
    }

    public boolean isSuccess() {
        return _isSuccess;
    }

    // Required function that handles calling ZoOm Server to get result and decides how to continue.
    public void processZoomSessionResultWhileZoomWaits(final ZoomSessionResult zoomSessionResult, final ZoomFaceMapResultCallback zoomFaceMapResultCallback) {
        this.latestZoomSessionResult = zoomSessionResult;
        this.zoomFaceMapResultCallback = zoomFaceMapResultCallback;

        // Cancel last request in flight.  This handles case where processing is is taking place but cancellation or Context Switch occurs.
        // Our handling here ends the latest in flight request and simply re-does the normal logic, which will cancel out.
        NetworkingHelpers.cancelPendingRequests();

        // cancellation, timeout, etc.
        if (zoomSessionResult.getStatus() != ZoomSessionStatus.SESSION_COMPLETED_SUCCESSFULLY) {
            zoomFaceMapResultCallback.cancel();
            this.zoomFaceMapResultCallback = null;
            return;
        }

        // Create and parse request to ZoOm Server.  Note here that for Photo ID Match, onFaceMapResultSucceed sends you to the next phase (ID Scan) rather than completing.
        NetworkingHelpers.getEnrollmentResponseFromZoomServer(zoomSessionResult, this.zoomFaceMapResultCallback, new FaceTecManagedAPICallback() {
            @Override
            public void onResponse(JSONObject responseJSON) {
                UXNextStep nextStep = ServerResultHelpers.getEnrollmentNextStep(responseJSON);
                if (nextStep == UXNextStep.Succeed) {
                    // Dynamically set the success message.
                    ZoomCustomization.overrideResultScreenSuccessMessage = "Liveness\nConfirmed";
                    zoomFaceMapResultCallback.succeed();
                }
                else if (nextStep == UXNextStep.Retry) {
                    zoomFaceMapResultCallback.retry();
                }
                else {
                    zoomFaceMapResultCallback.cancel();
                }
            }
        });

    }

    // Required function that handles calling ZoOm Server to get result and decides how to continue.
    public void processZoomIDScanResultWhileZoomWaits(ZoomIDScanResult zoomIDScanResult, final ZoomIDScanResultCallback zoomIDScanResultCallback) {
        this.latestZoomIDScanResult = zoomIDScanResult;
        this.zoomIDScanResultCallback = zoomIDScanResultCallback;

        // Cancel last request in flight.  This handles case where processing is is taking place but cancellation or Context Switch occurs.
        // Our handling here ends the latest in flight request and simply re-does the normal logic, which will cancel out.
        NetworkingHelpers.cancelPendingRequests();

        // cancellation, timeout, etc.
        if(zoomIDScanResult.getZoomIDScanStatus() != ZoomIDScanStatus.SUCCESS) {
            zoomIDScanResultCallback.cancel();
            this.zoomIDScanResultCallback = null;
            return;
        }

        if(zoomIDScanResult.getIDScanMetrics() == null) {
            zoomIDScanResultCallback.cancel();
            this.zoomIDScanResultCallback = null;
            return;
        }

        if(zoomIDScanResult.getIDScanMetrics().getIDScan() == null) {
            zoomIDScanResultCallback.cancel();
            this.zoomIDScanResultCallback = null;
            return;
        }

        // Create and parse request to ZoOm Server.
        NetworkingHelpers.getPhotoIDMatchResponseFromZoomServer(zoomIDScanResult, zoomIDScanResultCallback, new FaceTecManagedAPICallback() {
            @Override
            public void onResponse(JSONObject responseJSON) {
                UXNextStep nextStep = ServerResultHelpers.getPhotoIDMatchNextStep(responseJSON);
                if (nextStep == UXNextStep.Succeed) {
                    _isSuccess = true;
                    // Dynamically set the success message.
                    ZoomCustomization.overrideResultScreenSuccessMessage = "Your 3D Face\nMatched Your ID";
                    zoomIDScanResultCallback.succeed();
                }
                else if (nextStep == UXNextStep.Retry) {
                    zoomIDScanResultCallback.retry(ZoomIDScanRetryMode.FRONT);
                }
                else {
                    zoomIDScanResultCallback.cancel();
                }
            }
        });
    }
}
