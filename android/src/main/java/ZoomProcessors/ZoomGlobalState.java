package ZoomProcessors;

//import com.facetec.zoom.sampleapp.BuildConfig;
import com.facetec.zoom.sdk.ZoomCustomization;

public class ZoomGlobalState {
    // Replace string below with your license key from https://dev.zoomlogin.com/zoomsdk/#/account
//    public static String DeviceLicenseKeyIdentifier = BuildConfig.ZOOM_LICENSE_KEY_IDENTIFIER;

    // "https://api.zoomauth.com/api/v2/biometrics" for FaceTec Managed Testing API.
    // "http://localhost:8080" if running ZoOm Server SDK (Dockerized) locally.
    // Otherwise, your webservice URL.
    public static String ZoomServerBaseURL = "https://api.zoomauth.com/api/v2/biometrics";

    // The customer-controlled public key used during encryption of FaceMap data.
    // Please see https://dev.zoomlogin.com/zoomsdk/#/licensing-and-encryption-keys for more information.
    public static String PublicFaceMapEncryptionKey =
        "-----BEGIN PUBLIC KEY-----\n" +
        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5PxZ3DLj+zP6T6HFgzzk\n" +
        "M77LdzP3fojBoLasw7EfzvLMnJNUlyRb5m8e5QyyJxI+wRjsALHvFgLzGwxM8ehz\n" +
        "DqqBZed+f4w33GgQXFZOS4AOvyPbALgCYoLehigLAbbCNTkeY5RDcmmSI/sbp+s6\n" +
        "mAiAKKvCdIqe17bltZ/rfEoL3gPKEfLXeN549LTj3XBp0hvG4loQ6eC1E1tRzSkf\n" +
        "GJD4GIVvR+j12gXAaftj3ahfYxioBH7F7HQxzmWkwDyn3bqU54eaiB7f0ftsPpWM\n" +
        "ceUaqkL2DZUvgN0efEJjnWy5y1/Gkq5GGWCROI9XG/SwXJ30BbVUehTbVcD70+ZF\n" +
        "8QIDAQAB\n" +
        "-----END PUBLIC KEY-----";

    // Used for bookkeeping around demonstrating enrollment/authentication functionality of ZoOm.
//    public static String randomUsername = "";
//    public static boolean isRandomUsernameEnrolled = false;

    // This app can modify the customization to demonstrate different look/feel preferences for ZoOm.
    public static ZoomCustomization currentCustomization = new ZoomCustomization();
}
