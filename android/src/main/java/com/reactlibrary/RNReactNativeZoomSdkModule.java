package com.reactlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facetec.zoom.sdk.AuditTrailType;
import com.facetec.zoom.sdk.ZoomCustomization;
import com.facetec.zoom.sdk.ZoomExternalImageSetVerificationResult;
import com.facetec.zoom.sdk.ZoomFaceBiometricMetrics;
import com.facetec.zoom.sdk.ZoomLivenessResult;
import com.facetec.zoom.sdk.ZoomSDK;
import com.facetec.zoom.sdk.ZoomSDKStatus;
import com.facetec.zoom.sdk.ZoomVerificationActivity;
import com.facetec.zoom.sdk.ZoomVerificationResult;
import com.facetec.zoom.sdk.ZoomVerificationStatus;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
//import static com.facetec.zoom.sdk.ZoomAuthenticationStatus.USER_WAS_AUTHENTICATED;
//import static com.facetec.zoom.sdk.ZoomAuthenticationStatus.USER_WAS_AUTHENTICATED_WITH_FALLBACK_STRATEGY;
//import static com.facetec.zoom.sdk.ZoomEnrollmentStatus.USER_WAS_ENROLLED;
//import static com.facetec.zoom.sdk.ZoomEnrollmentStatus.USER_WAS_ENROLLED_WITH_FALLBACK_STRATEGY;

public class RNReactNativeZoomSdkModule extends ReactContextBaseJavaModule {

  private static final String TAG = "RNReactNativeZoomSdk";
  private final ReactApplicationContext reactContext;
  private Promise verificationPromise;
  private boolean initialized;

  private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener() {
    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
      super.onActivityResult(activity, requestCode, resultCode, data);
      if (requestCode != ZoomSDK.REQUEST_CODE_VERIFICATION) return;
      if (verificationPromise == null) return;

      ZoomVerificationResult result = data.getParcelableExtra(ZoomSDK.EXTRA_VERIFY_RESULTS);
      WritableMap resultObj = convertZoomVerificationResult(result);
      verificationPromise.resolve(resultObj);
      verificationPromise = null;

//    AsyncTask.execute(new Runnable() {
//      @Override
//      public void run() {
//        // preload sdk resources so the UI is snappy (optional)
//        ZoomSDK.preload(reactContext.getApplicationContext());
//      }
//    });
    }
  };


  public RNReactNativeZoomSdkModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    this.verificationPromise = null;
    this.initialized = false;
    reactContext.addActivityEventListener(mActivityEventListener);
  }

  @Override
  public String getName() {
    return "RNReactNativeZoomSdk";
  }

  @ReactMethod
  public void initialize(final ReadableMap opts, final Promise promise) {
    Log.d(TAG, "initializing");
    final String appToken = opts.getString("appToken");

    final Context context = reactContext.getApplicationContext();

    AsyncTask.execute(new Runnable() {
      @Override
      public void run() {
        ZoomCustomization currentCustomization = new ZoomCustomization();
        ZoomSDK.setAuditTrailType(AuditTrailType.HEIGHT_640);
        currentCustomization.showZoomIntro = opts.getBoolean("showZoomIntro");
        currentCustomization.showPreEnrollmentScreen = opts.getBoolean("showPreEnrollmentScreen");
        currentCustomization.showUserLockedScreen = opts.getBoolean("showUserLockedScreen");
        currentCustomization.showSuccessScreen = opts.getBoolean("showSuccessScreen");
        currentCustomization.showFailureScreen = opts.getBoolean("showFailureScreen");
        ZoomSDK.setCustomization(currentCustomization);
        ZoomSDK.initialize(getCurrentActivity(), appToken, new ZoomSDK.InitializeCallback() {
          @Override
          public void onCompletion(boolean successful) {
            WritableMap map = Arguments.createMap();
            map.putBoolean("success", successful);
            if (successful) {
              initialized = true;
            } else {
              map.putString("status", getSdkStatusString());
            }

            Log.d(TAG, String.format("initialized: %b", successful));
            promise.resolve(map);
          }
        });

        // preload sdk resources so the UI is snappy (optional)
        ZoomSDK.preload(context);
      }
    });
  }

  // private void enroll(JSONArray args, final CallbackContext callbackContext) throws JSONException {
  //     String userId = args.getString(0);
  //     String secret = args.getString(1);

  //     Intent enrollmentIntent = new Intent(this.cordova.getActivity(), ZoomEnrollmentActivity.class);
  //     enrollmentIntent.putExtra(ZoomSDK.EXTRA_ENROLLMENT_USER_ID, userId);
  //     enrollmentIntent.putExtra(ZoomSDK.EXTRA_USER_ENCRYPTION_SECRET, secret);

  //     pendingCallbackContext = callbackContext;

  //     this.cordova.startActivityForResult(this, enrollmentIntent, ZoomSDK.REQUEST_CODE_ENROLLMENT);
  // }

  // private void authenticate(JSONArray args, final CallbackContext callbackContext) throws JSONException {
  //     String userId = args.getString(0);
  //     String secret = args.getString(1);

  //     Intent authenticationIntent = new Intent(this.cordova.getActivity(), ZoomAuthenticationActivity.class);
  //     authenticationIntent.putExtra(ZoomSDK.EXTRA_AUTHENTICATION_USER_ID, userId);
  //     authenticationIntent.putExtra(ZoomSDK.EXTRA_USER_ENCRYPTION_SECRET, secret);

  //     pendingCallbackContext = callbackContext;

  //     this.cordova.startActivityForResult(this, authenticationIntent, ZoomSDK.REQUEST_CODE_AUTHENTICATION);
  // }

  @ReactMethod
  public void verify(ReadableMap opts, final Promise promise) {
    verificationPromise = promise;
    Activity activity = getCurrentActivity();
    Intent verificationIntent = new Intent(activity.getBaseContext(), ZoomVerificationActivity.class);
//    Intent authenticationIntent = new Intent(reactContext.getApplicationContext(), ZoomVerificationActivity.class);
//    authenticationIntent.putExtra(ZoomSDK.EXTRA_RETRIEVE_ZOOM_BIOMETRIC, true);
    activity.startActivityForResult(verificationIntent, ZoomSDK.REQUEST_CODE_VERIFICATION);
  }

//  @ReactMethod
//  public void handleVerificationSuccessResult(ZoomVerificationResult successResult) {
//    // retrieve the ZoOm facemap as byte[]
//    if (successResult.getFaceMetrics() != null) {
//      // this is the raw biometric data which can be uploaded, or may be
//      // base64 encoded in order to handle easier at the cost of processing and network usage
//      byte[] zoomFacemap = successResult.getFaceMetrics().getZoomFacemap();
//      // handle facemap
//    }
//  }
//
  // private void getUserEnrollmentStatus(JSONArray args, final CallbackContext callbackContext) throws JSONException {
  //     final Context context = this.cordova.getActivity().getApplicationContext();
  //     final String userId = args.getString(0);

  //     cordova.getThreadPool().execute(new Runnable() {
  //         @Override
  //         public void run() {
  //             ZoomSDK.UserEnrollmentStatus status = ZoomSDK.getUserEnrollmentStatus(context, userId);
  //             switch (status) {
  //                 case USER_ENROLLED:
  //                     callbackContext.success("Enrolled");
  //                     break;
  //                 case USER_INVALIDATED:
  //                     callbackContext.success("Invalidated");
  //                     break;
  //                 case USER_NOT_ENROLLED:
  //                 default:
  //                     callbackContext.success("NotEnrolled");
  //             }
  //         }
  //     });
  // }

//  @Override
//  public void onNewIntent(Intent intent) { }


  @NonNull
  private String getSdkStatusString() {
    ZoomSDKStatus status = ZoomSDK.getStatus(reactContext.getApplicationContext());

    switch (status) {
      case NEVER_INITIALIZED:
        return "NeverInitialized";
      case INITIALIZED:
        return "Initialized";
      case INVALID_TOKEN:
        return "InvalidToken";
      case VERSION_DEPRECATED:
        return "VersionDeprecated";
      case DEVICE_INSECURE:
        return "DeviceInsecure";
      case OFFLINE_SESSIONS_EXCEEDED:
        return "OfflineSessionsExceeded";
      case NETWORK_ISSUES:
      default:
        return "NetworkIssues";
    }
  }

  // private static JSONObject convertZoomEnrollmentResult(ZoomEnrollmentResult result) throws JSONException {
  //     JSONObject resultObj = new JSONObject();

  //     ZoomEnrollmentStatus status = result.getStatus();
  //     resultObj.put("successful", (status == USER_WAS_ENROLLED || status == USER_WAS_ENROLLED_WITH_FALLBACK_STRATEGY));
  //     resultObj.put("status", convertZoomEnrollmentStatus(status));
  //     resultObj.put("faceEnrollmentState", convertZoomAuthenticatorState(result.getFaceEnrollmentState()));
  //     resultObj.put("livenessResult", convertZoomLivenessResult(result.getFaceMetrics().getLiveness()));

  //     return resultObj;
  // }

  // private static JSONObject convertZoomAuthenticationResult(ZoomAuthenticationResult result) throws JSONException {
  //     JSONObject resultObj = new JSONObject();

  //     ZoomAuthenticationStatus status = result.getStatus();
  //     resultObj.put("successful", (status == USER_WAS_AUTHENTICATED || status == USER_WAS_AUTHENTICATED_WITH_FALLBACK_STRATEGY));
  //     resultObj.put("status", convertZoomAuthenticationStatus(status));
  //     resultObj.put("faceAuthenticatorState", convertZoomAuthenticatorState(result.getFaceZoomAuthenticatorState()));
  //     resultObj.put("livenessResult", convertZoomLivenessResult(result.getFaceMetrics().getLiveness()));
  //     resultObj.put("countOfFaceFailuresSinceLastSuccess", result.getCountOfFaceFailuresSinceLastSuccess());
  //     resultObj.put("consecutiveLockouts", result.getConsecutiveLockouts());

  //     return resultObj;
  // }

  private static WritableMap convertZoomVerificationResult(ZoomVerificationResult result) {
    WritableMap resultObj = Arguments.createMap();
    WritableMap externalImageSetVerificationResultObj = Arguments.createMap();
    WritableMap faceMetricsObj = Arguments.createMap();

    ZoomFaceBiometricMetrics faceMetrics = result.getFaceMetrics();
    ArrayList<Bitmap> auditTrail = faceMetrics.getAuditTrail();
    WritableArray auditTrailBase64 = Arguments.createArray();
    for (Bitmap image: auditTrail) {
      auditTrailBase64.pushString(bitmapToBase64(image, 90));
    }

    ZoomExternalImageSetVerificationResult externalImageSetVerificationResult = faceMetrics.getExternalImageSetVerificationResult();

    faceMetricsObj.putString("externalImageSetVerificationResult", convertExternalImageSetVerificationResult(externalImageSetVerificationResult));
    faceMetricsObj.putArray("auditTrail", auditTrailBase64);
    faceMetricsObj.putString("livenessResult", convertZoomLivenessResult(faceMetrics.getLiveness()));
    faceMetricsObj.putDouble("livenessScore", faceMetrics.getLivenessScore());

    String status = convertZoomVerificationStatus(result.getStatus());
    resultObj.putString("status", status);
    resultObj.putInt("countOfZoomSessionsPerformed", result.getCountOfZoomSessionsPerformed());
    resultObj.putMap("faceMetrics", faceMetricsObj);
    resultObj.putString("sessionId", result.getSessionId());

//    resultObj.put("successful", (status == USER_WAS_AUTHENTICATED || status == USER_WAS_AUTHENTICATED_WITH_FALLBACK_STRATEGY));
//    resultObj.put("status", convertZoomAuthenticationStatus(status));
//    resultObj.put("faceAuthenticatorState", convertZoomAuthenticatorState(result.getFaceZoomAuthenticatorState()));
//    resultObj.put("livenessResult", convertZoomLivenessResult(result.getFaceMetrics().getLiveness()));
//    resultObj.put("countOfFaceFailuresSinceLastSuccess", result.getCountOfFaceFailuresSinceLastSuccess());
//    resultObj.put("consecutiveLockouts", result.getConsecutiveLockouts());

    return resultObj;
  }

  private static String bitmapToBase64(Bitmap bitmap, int quality) {
    return Base64.encodeToString(toJpeg(bitmap, quality), Base64.NO_WRAP);
  }

  private static byte[] toJpeg(Bitmap bitmap, int quality) throws OutOfMemoryError {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);

    try {
      return outputStream.toByteArray();
    } finally {
      try {
        outputStream.close();
      } catch (IOException e) {
        Log.e(TAG, "problem compressing jpeg", e);
      }
    }
  }

  private static String convertZoomVerificationStatus(ZoomVerificationStatus status) {
    // Note: These string values should match exactly with the iOS implementation
    switch (status) {
      case APP_TOKEN_NOT_VALID:
        return "FailedBecauseAppTokenNotValid";
      case CAMERA_PERMISSION_DENIED:
        return "FailedBecauseCameraPermissionDeniedByUser";
      case USER_PROCESSED_SUCCESSFULLY:
        return "UserProcessedSuccessfully";
      case USER_NOT_PROCESSED:
        return "UserNotProcessed";
      case USER_CANCELLED:
        return "FailedBecauseUserCancelled";
      case FAILED_DUE_TO_OS_CONTEXT_SWITCH:
        return "FailedBecauseOfOSContextSwitch";
      case VERIFICATION_TIMED_OUT:
        return "FailedBecauseOfTimeout";
      case NETWORKING_MISSING_IN_DEV_MODE:
        return "FailedBecauseNoConnectionInDevMode";
      case OFFLINE_SESSIONS_EXCEEDED:
        return "FailedBecauseOfflineSessionsExceeded";
      case ENCRYPTION_KEY_INVALID:
        return "FailedBecauseEncryptionKeyInvalid";
      // doesn't exist in android
      //   FailedBecauseCameraPermissionDeniedByAdministrator
      //   FailedBecauseOfLowMemory
      //   FailedBecauseOfDiskWriteError
      // doesn't exist in ios
      case FAILED_DUE_TO_CAMERA_ERROR:
        return "FailedBecauseOfLowMemory";
      case FAILED_DUE_TO_INTERNAL_ERROR:
      default:
        return "FailedDueToInternalError";
    }
  }

  private static String convertExternalImageSetVerificationResult(ZoomExternalImageSetVerificationResult result) {
    // Note: These string values should match exactly with the iOS implementation
    switch (result) {
      case COULD_NOT_DETERMINE_MATCH:
        return "CouldNotDetermineMatch";
      case LOW_CONFIDENCE_MATCH:
        return "LowConfidenceMatch";
      case MATCH:
        return "Match";
      default:
        return "Unsupported";
    }
  }

  // private static String convertZoomEnrollmentStatus(ZoomEnrollmentStatus status) {
  //     // Note: These string values should match exactly with the iOS implementation
  //     switch (status) {
  //         case APP_TOKEN_NOT_VALID:
  //             return "InvalidToken";
  //         case USER_WAS_ENROLLED:
  //         case USER_WAS_ENROLLED_WITH_FALLBACK_STRATEGY:
  //             return "Enrolled";
  //         case USER_CANCELLED:
  //             return "UserCancelled";
  //         case ENROLLMENT_TIMED_OUT:
  //             return "Timeout";
  //         case FAILED_DUE_TO_CAMERA_ERROR:
  //             return "CameraError";
  //         case FAILED_DUE_TO_INTERNAL_ERROR:
  //             return "InternalError";
  //         case FAILED_DUE_TO_OS_CONTEXT_SWITCH:
  //             return "OSContextSwitch";
  //         case WIFI_NOT_ON_IN_DEV_MODE:
  //             return "WifiNotOnInDevMode";
  //         case NETWORKING_MISSING_IN_DEV_MODE:
  //             return "NoConnectionInDevMode";
  //         case CAMERA_PERMISSION_DENIED:
  //             return "CameraPermissionDenied";
  //         case USER_NOT_ENROLLED:
  //         case FAILED_BECAUSE_USER_COULD_NOT_VALIDATE_FINGERPRINT:
  //         default:
  //             return "NotEnrolled";
  //     }
  // }

  // private static String convertZoomAuthenticationStatus(ZoomAuthenticationStatus status) {
  //     // Note: These string values should match exactly with the iOS implementation
  //     switch (status) {
  //         case APP_TOKEN_NOT_VALID:
  //             return "AppTokenNotValid";
  //         case USER_WAS_AUTHENTICATED:
  //         case USER_WAS_AUTHENTICATED_WITH_FALLBACK_STRATEGY:
  //             return "Authenticated";
  //         case AUTHENTICATION_TIMED_OUT:
  //             return "Timeout";
  //         case USER_FAILED_AUTHENTICATION:
  //             return "FailedAuthentication";
  //         case WIFI_NOT_ON_IN_DEV_MODE:
  //             return "WifiNotOnInDevMode";
  //         case NETWORKING_MISSING_IN_DEV_MODE:
  //             return "NoConnectionInDevMode";
  //         case CAMERA_PERMISSIONS_DENIED:
  //             return "CameraPermissionDenied";
  //         case USER_MUST_ENROLL:
  //             return "UserMustEnroll";
  //         case USER_FAILED_AUTHENTICATION_AND_WAS_DELETED:
  //             return "FailedAndWasDeleted";
  //         case SESSION_FAILED_DUE_TO_OS_CONTEXT_SWITCH:
  //             return "OSContextSwitch";
  //         case FAILED_DUE_TO_CAMERA_ERROR:
  //             return "CameraError";
  //         case FAILED_DUE_TO_INTERNAL_ERROR:
  //             return "InternalError";
  //         case USER_CANCELLED:
  //         default:
  //             return "UserCancelled";
  //     }
  // }

  // private static String convertZoomAuthenticatorState(ZoomAuthenticatorState state) {
  //     switch (state) {
  //         case COMPLETED:
  //             return "Completed";
  //         case FAILED:
  //             return "Failed";
  //         case CANCELLED:
  //             return "Cancelled";
  //         case UNUSED:
  //         default:
  //             return "Unused";
  //     }
  // }

  private static String convertZoomLivenessResult(ZoomLivenessResult result) {
    switch (result) {
      case ALIVE:
        return "Alive";
      case LIVENESS_UNDETERMINED:
      default:
        return "Undetermined";
    }
  }
}
