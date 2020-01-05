// Demonstrates performing a Liveness Check.

package ZoomProcessors;

import android.content.Context;
import org.json.JSONObject;

import com.facetec.zoom.sdk.*;

public class LivenessCheckProcessor extends Processor implements ZoomFaceMapProcessor {
    ZoomFaceMapResultCallback zoomFaceMapResultCallback;
    ZoomSessionResult latestZoomSessionResult;
    private boolean _isSuccess = false;
    private final String licenseKey;

    public LivenessCheckProcessor(Context context, String licenseKey) {
        // Launch the ZoOm Session.
        this.licenseKey = licenseKey;
        ZoomSessionActivity.createAndLaunchZoomSession(context, this);
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

        // Create and parse request to ZoOm Server.
        NetworkingHelpers.getLivenessCheckResponseFromZoomServer(licenseKey, zoomSessionResult, this.zoomFaceMapResultCallback, new FaceTecManagedAPICallback() {
            @Override
            public void onResponse(JSONObject responseJSON) {
                UXNextStep nextStep = ServerResultHelpers.getLivenessNextStep(responseJSON);

                if (nextStep == UXNextStep.Succeed) {
                    _isSuccess = true;
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
}
