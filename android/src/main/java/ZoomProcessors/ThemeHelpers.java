package ZoomProcessors;

import android.graphics.Color;

//import com.facetec.zoom.sampleapp.R;
import com.facetec.zoom.sdk.ZoomCustomization;
import com.facetec.zoom.sdk.ZoomSDK;

public class ThemeHelpers {

    public static void setAppTheme(String theme) {
        if(theme.equals("ZoOm Theme")) {
            ZoomGlobalState.currentCustomization = new ZoomCustomization();
        }
        else if(theme.equals("Well-Rounded")) {
            int primaryColor = Color.parseColor("#09B5A3"); // green
            int backgroundColor = Color.parseColor("#FFFFFF"); // white

            // Overlay Customization
            ZoomGlobalState.currentCustomization.getOverlayCustomization().backgroundColor = Color.parseColor("#00000000");
            // ZoomGlobalState.currentCustomization.getOverlayCustomization().brandingImage = R.drawable.blank_image;
            // Guidance Customization
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().backgroundColors = backgroundColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().foregroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonTextNormalColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonBackgroundNormalColor = primaryColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonTextHighlightColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonBackgroundHighlightColor = primaryColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonBorderColor = Color.parseColor("#00000000");
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonBorderWidth = 0;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonCornerRadius = 30;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().readyScreenOvalFillColor = Color.parseColor("#4D09B5A3");
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().readyScreenTextBackgroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().readyScreenTextBackgroundCornerRadius = 5;
            // Guidance Image Customization
            // ZoomGlobalState.currentCustomization.getGuidanceCustomization().getImageCustomization().cameraPermissionsScreenImage = R.drawable.camera_green;
            // ID Scan Customization
            ZoomGlobalState.currentCustomization.getIdScanCustomization().showSelectionScreenBrandingImage = false;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().selectionScreenBackgroundColors = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenBackgroundColors = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenForegroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenForegroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().selectionScreenForegroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonTextNormalColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonBackgroundNormalColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonTextHighlightColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonBackgroundHighlightColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonBorderColor = Color.parseColor("#00000000");
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonBorderWidth = 0;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonCornerRadius = 5;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenTextBackgroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenTextBackgroundBorderColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenTextBackgroundBorderWidth = 2;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenTextBackgroundCornerRadius = 5;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenTextBackgroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenTextBackgroundBorderColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenTextBackgroundBorderWidth = 2;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenTextBackgroundCornerRadius = 5;
            // ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenPassportFrameImage = R.drawable.zoom_passport_frame_green;
            // ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenIDCardFrameImage = R.drawable.zoom_id_card_frame_green;
            // Result Screen Customization
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().backgroundColors = backgroundColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().foregroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().activityIndicatorColor = primaryColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().resultAnimationBackgroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().resultAnimationForegroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().uploadProgressTrackColor = Color.parseColor("#33000000");
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().uploadProgressFillColor = primaryColor;
            // Feedback Customization
            ZoomGlobalState.currentCustomization.getFeedbackCustomization().backgroundColors = primaryColor;
            ZoomGlobalState.currentCustomization.getFeedbackCustomization().textColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getFeedbackCustomization().cornerRadius = 5;
            // Frame Customization
            ZoomGlobalState.currentCustomization.getFrameCustomization().backgroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getFrameCustomization().borderColor = Color.parseColor("#00000000");
            ZoomGlobalState.currentCustomization.getFrameCustomization().borderWidth = 0;
            ZoomGlobalState.currentCustomization.getFrameCustomization().cornerRadius = 20;
            // Oval Customization
            ZoomGlobalState.currentCustomization.getOvalCustomization().strokeColor = primaryColor;
            ZoomGlobalState.currentCustomization.getOvalCustomization().progressColor1 = primaryColor;
            ZoomGlobalState.currentCustomization.getOvalCustomization().progressColor2 = primaryColor;
            // Cancel Button Customization
            // ZoomGlobalState.currentCustomization.getCancelButtonCustomization().customImage = R.drawable.cancel_round_green;
            // ZoomGlobalState.currentCustomization.getCancelButtonCustomization().customImageLowLight = R.drawable.cancel_round_green;

        }
        else if(theme.equals("Dark-Side")) {
            int primaryColor = Color.parseColor("#00AEED"); // blue
            int backgroundColor = Color.parseColor("#000000"); // black

            // Overlay Customization
            ZoomGlobalState.currentCustomization.getOverlayCustomization().backgroundColor = backgroundColor;
            // ZoomGlobalState.currentCustomization.getOverlayCustomization().brandingImage = R.drawable.blank_image;
            // Guidance Customization
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().backgroundColors = backgroundColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().foregroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonTextNormalColor = primaryColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonBackgroundNormalColor = Color.parseColor("#00000000");
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonTextHighlightColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonBackgroundHighlightColor = primaryColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonBorderColor = primaryColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonBorderWidth = 1;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonCornerRadius = 2;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().readyScreenOvalFillColor = Color.parseColor("#4D00AEED");
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().readyScreenTextBackgroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().readyScreenTextBackgroundCornerRadius = 5;
            // Guidance Image Customization
            // ZoomGlobalState.currentCustomization.getGuidanceCustomization().getImageCustomization().cameraPermissionsScreenImage = R.drawable.camera_blue;
            // ID Scan Customization
            ZoomGlobalState.currentCustomization.getIdScanCustomization().showSelectionScreenBrandingImage = false;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().selectionScreenBackgroundColors = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenBackgroundColors = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenForegroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenForegroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().selectionScreenForegroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonTextNormalColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonBackgroundNormalColor = Color.parseColor("#00000000");
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonTextHighlightColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonBackgroundHighlightColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonBorderColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonBorderWidth = 1;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonCornerRadius = 2;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenTextBackgroundColor = Color.parseColor("#00000000");
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenTextBackgroundBorderColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenTextBackgroundBorderWidth = 2;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenTextBackgroundCornerRadius = 8;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenTextBackgroundColor = Color.parseColor("#00000000");
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenTextBackgroundBorderColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenTextBackgroundBorderWidth = 2;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenTextBackgroundCornerRadius = 8;
            // ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenPassportFrameImage = R.drawable.zoom_passport_frame_blue;
            // ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenIDCardFrameImage = R.drawable.zoom_id_card_frame_blue;
            // Result Screen Customization
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().backgroundColors = backgroundColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().foregroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().activityIndicatorColor = primaryColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().resultAnimationBackgroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().resultAnimationForegroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().uploadProgressTrackColor = Color.parseColor("#33FFFFFF");
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().uploadProgressFillColor = primaryColor;
            // Feedback Customization
            ZoomGlobalState.currentCustomization.getFeedbackCustomization().backgroundColors = primaryColor;
            ZoomGlobalState.currentCustomization.getFeedbackCustomization().textColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getFeedbackCustomization().cornerRadius = 5;
            // Frame Customization
            ZoomGlobalState.currentCustomization.getFrameCustomization().backgroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getFrameCustomization().borderColor = primaryColor;
            ZoomGlobalState.currentCustomization.getFrameCustomization().borderWidth = 2;
            ZoomGlobalState.currentCustomization.getFrameCustomization().cornerRadius = 5;
            // Oval Customization
            ZoomGlobalState.currentCustomization.getOvalCustomization().strokeColor = primaryColor;
            ZoomGlobalState.currentCustomization.getOvalCustomization().progressColor1 = Color.parseColor("#BF00AEED");
            ZoomGlobalState.currentCustomization.getOvalCustomization().progressColor2 = Color.parseColor("#BF00AEED");
            // Cancel Button Customization
            // ZoomGlobalState.currentCustomization.getCancelButtonCustomization().customImage = R.drawable.double_chevron_left_blue;
            // ZoomGlobalState.currentCustomization.getCancelButtonCustomization().customImageLowLight = R.drawable.double_chevron_left_blue;
        }
        else if(theme.equals("Bitcoin Exchange")) {
            int primaryColor = Color.parseColor("#F79634"); // orange
            int secondaryColor = Color.parseColor("#FFFF1E"); // yellow
            int backgroundColor = Color.parseColor("#424242"); // dark grey

            // Overlay Customization
            ZoomGlobalState.currentCustomization.getOverlayCustomization().backgroundColor = Color.parseColor("#00000000");
            // ZoomGlobalState.currentCustomization.getOverlayCustomization().brandingImage = R.drawable.bitcoin_exchange_logo;
            // Guidance Customization
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().backgroundColors = backgroundColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().foregroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonTextNormalColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonBackgroundNormalColor = primaryColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonTextHighlightColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonBackgroundHighlightColor = primaryColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonBorderColor = Color.parseColor("#00000000");
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonBorderWidth = 0;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonCornerRadius = 5;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().readyScreenOvalFillColor = Color.parseColor("#4DF79634");
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().readyScreenTextBackgroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().readyScreenTextBackgroundCornerRadius = 5;
            // Guidance Image Customization
            // ZoomGlobalState.currentCustomization.getGuidanceCustomization().getImageCustomization().cameraPermissionsScreenImage = R.drawable.camera_orange;
            // ID Scan Customization
            ZoomGlobalState.currentCustomization.getIdScanCustomization().showSelectionScreenBrandingImage = false;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().selectionScreenBackgroundColors = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenBackgroundColors = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenForegroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenForegroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().selectionScreenForegroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonTextNormalColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonBackgroundNormalColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonTextHighlightColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonBackgroundHighlightColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonBorderColor = Color.parseColor("#00000000");
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonBorderWidth = 0;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonCornerRadius = 5;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenTextBackgroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenTextBackgroundBorderColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenTextBackgroundBorderWidth = 0;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenTextBackgroundCornerRadius = 8;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenTextBackgroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenTextBackgroundBorderColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenTextBackgroundBorderWidth = 0;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenTextBackgroundCornerRadius = 8;
            // ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenPassportFrameImage = R.drawable.zoom_passport_frame_orange;
            // ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenIDCardFrameImage = R.drawable.zoom_id_card_frame_orange;
            // Result Screen Customization
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().backgroundColors = backgroundColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().foregroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().activityIndicatorColor = primaryColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().resultAnimationBackgroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().resultAnimationForegroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().uploadProgressTrackColor = Color.parseColor("#33000000");
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().uploadProgressFillColor = primaryColor;
            // Feedback Customization
            ZoomGlobalState.currentCustomization.getFeedbackCustomization().backgroundColors = primaryColor;
            ZoomGlobalState.currentCustomization.getFeedbackCustomization().textColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getFeedbackCustomization().cornerRadius = 5;
            // Frame Customization
            ZoomGlobalState.currentCustomization.getFrameCustomization().backgroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getFrameCustomization().borderColor = secondaryColor;
            ZoomGlobalState.currentCustomization.getFrameCustomization().borderWidth = 0;
            ZoomGlobalState.currentCustomization.getFrameCustomization().cornerRadius = 5;
            // Oval Customization
            ZoomGlobalState.currentCustomization.getOvalCustomization().strokeColor = primaryColor;
            ZoomGlobalState.currentCustomization.getOvalCustomization().progressColor1 = secondaryColor;
            ZoomGlobalState.currentCustomization.getOvalCustomization().progressColor2 = secondaryColor;
            // Cancel Button Customization
            // ZoomGlobalState.currentCustomization.getCancelButtonCustomization().customImage = R.drawable.back_orange;
            // ZoomGlobalState.currentCustomization.getCancelButtonCustomization().customImageLowLight = R.drawable.back_orange;
        }
        else if(theme.equals("eKYC")) {
            int primaryColor = Color.parseColor("#ED1C24"); // red
            int secondaryColor = Color.parseColor("#000000"); // black
            int backgroundColor = Color.parseColor("#FFFFFF"); // white

            // Overlay Customization
            ZoomGlobalState.currentCustomization.getOverlayCustomization().backgroundColor = Color.parseColor("#00000000");
            // ZoomGlobalState.currentCustomization.getOverlayCustomization().brandingImage = R.drawable.ekyc_logo;
            // Guidance Customization
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().backgroundColors = backgroundColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().foregroundColor = secondaryColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonTextNormalColor = primaryColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonBackgroundNormalColor = Color.parseColor("#00000000");
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonTextHighlightColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonBackgroundHighlightColor = primaryColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonBorderColor = primaryColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonBorderWidth = 2;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonCornerRadius = 8;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().readyScreenOvalFillColor = Color.parseColor("#4DF79634");
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().readyScreenTextBackgroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().readyScreenTextBackgroundCornerRadius = 3;
            // Guidance Image Customization
            // ZoomGlobalState.currentCustomization.getGuidanceCustomization().getImageCustomization().cameraPermissionsScreenImage = R.drawable.camera_red;
            // ID Scan Customization
            // ZoomGlobalState.currentCustomization.getIdScanCustomization().selectionScreenBrandingImage = R.drawable.ekyc_logo;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().showSelectionScreenBrandingImage = true;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().selectionScreenBackgroundColors = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenBackgroundColors = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenForegroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenForegroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().selectionScreenForegroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonTextNormalColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonBackgroundNormalColor = Color.parseColor("#00000000");
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonTextHighlightColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonBackgroundHighlightColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonBorderColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonBorderWidth = 2;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonCornerRadius = 8;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenTextBackgroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenTextBackgroundBorderColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenTextBackgroundBorderWidth = 0;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenTextBackgroundCornerRadius = 2;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenTextBackgroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenTextBackgroundBorderColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenTextBackgroundBorderWidth = 0;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenTextBackgroundCornerRadius = 2;
            // ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenPassportFrameImage = R.drawable.zoom_passport_frame_red;
            // ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenIDCardFrameImage = R.drawable.zoom_id_card_frame_red;
            // Result Screen Customization
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().backgroundColors = backgroundColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().foregroundColor = secondaryColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().activityIndicatorColor = primaryColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().resultAnimationBackgroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().resultAnimationForegroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().uploadProgressTrackColor = Color.parseColor("#33000000");
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().uploadProgressFillColor = primaryColor;
            // Feedback Customization
            ZoomGlobalState.currentCustomization.getFeedbackCustomization().backgroundColors = secondaryColor;
            ZoomGlobalState.currentCustomization.getFeedbackCustomization().textColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getFeedbackCustomization().cornerRadius = 3;
            // Frame Customization
            ZoomGlobalState.currentCustomization.getFrameCustomization().backgroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getFrameCustomization().borderColor = primaryColor;
            ZoomGlobalState.currentCustomization.getFrameCustomization().borderWidth = 2;
            ZoomGlobalState.currentCustomization.getFrameCustomization().cornerRadius = 8;
            // Oval Customization
            ZoomGlobalState.currentCustomization.getOvalCustomization().strokeColor = primaryColor;
            ZoomGlobalState.currentCustomization.getOvalCustomization().progressColor1 = Color.parseColor("#BFED1C24");
            ZoomGlobalState.currentCustomization.getOvalCustomization().progressColor2 = Color.parseColor("#BFED1C24");
            // Cancel Button Customization
            // ZoomGlobalState.currentCustomization.getCancelButtonCustomization().customImage = R.drawable.cancel_box_red;
            // ZoomGlobalState.currentCustomization.getCancelButtonCustomization().customImageLowLight = R.drawable.cancel_box_red;
        }
        else if(theme.equals("Sample Bank")) {
            int primaryColor = Color.parseColor("#FFFFFF"); // white
            int backgroundColor = Color.parseColor("#1D174F"); // navy

            // Overlay Customization
            ZoomGlobalState.currentCustomization.getOverlayCustomization().backgroundColor = primaryColor;
            // ZoomGlobalState.currentCustomization.getOverlayCustomization().brandingImage = R.drawable.sample_bank_logo;
            // Guidance Customization
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().backgroundColors = backgroundColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().foregroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonTextNormalColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonBackgroundNormalColor = primaryColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonTextHighlightColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonBackgroundHighlightColor = Color.parseColor("#BFFFFFFF");
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonBorderColor = primaryColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonBorderWidth = 2;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().buttonCornerRadius = 2;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().readyScreenOvalFillColor = Color.parseColor("#4DFFFFFF");
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().readyScreenTextBackgroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getGuidanceCustomization().readyScreenTextBackgroundCornerRadius = 2;
            // Guidance Image Customization
            // ZoomGlobalState.currentCustomization.getGuidanceCustomization().getImageCustomization().cameraPermissionsScreenImage = R.drawable.camera_white_navy;
            // ID Scan Customization
            // ZoomGlobalState.currentCustomization.getIdScanCustomization().selectionScreenBrandingImage = R.drawable.sample_bank_logo;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().showSelectionScreenBrandingImage = true;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().selectionScreenBackgroundColors = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenBackgroundColors = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenForegroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenForegroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().selectionScreenForegroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonTextNormalColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonBackgroundNormalColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonTextHighlightColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonBackgroundHighlightColor = Color.parseColor("#BFFFFFFF");
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonBorderColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonBorderWidth = 2;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().buttonCornerRadius = 2;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenTextBackgroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenTextBackgroundBorderColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenTextBackgroundBorderWidth = 0;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenTextBackgroundCornerRadius = 2;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenTextBackgroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenTextBackgroundBorderColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenTextBackgroundBorderWidth = 0;
            ZoomGlobalState.currentCustomization.getIdScanCustomization().reviewScreenTextBackgroundCornerRadius = 2;
            // ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenPassportFrameImage = R.drawable.zoom_passport_frame_navy;
            // ZoomGlobalState.currentCustomization.getIdScanCustomization().captureScreenIDCardFrameImage = R.drawable.zoom_id_card_frame_navy;
            // Result Screen Customization
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().backgroundColors = backgroundColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().foregroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().activityIndicatorColor = primaryColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().resultAnimationBackgroundColor = primaryColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().resultAnimationForegroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().uploadProgressTrackColor = Color.parseColor("#33FFFFFF");
            ZoomGlobalState.currentCustomization.getResultScreenCustomization().uploadProgressFillColor = primaryColor;
            // Feedback Customization
            ZoomGlobalState.currentCustomization.getFeedbackCustomization().backgroundColors = primaryColor;
            ZoomGlobalState.currentCustomization.getFeedbackCustomization().textColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getFeedbackCustomization().cornerRadius = 2;
            // Frame Customization
            ZoomGlobalState.currentCustomization.getFrameCustomization().backgroundColor = backgroundColor;
            ZoomGlobalState.currentCustomization.getFrameCustomization().borderColor = primaryColor;
            ZoomGlobalState.currentCustomization.getFrameCustomization().borderWidth = 2;
            ZoomGlobalState.currentCustomization.getFrameCustomization().cornerRadius = 2;
            // Oval Customization
            ZoomGlobalState.currentCustomization.getOvalCustomization().strokeColor = Color.parseColor("#B3B3B3");
            ZoomGlobalState.currentCustomization.getOvalCustomization().progressColor1 = Color.parseColor("#BFFFFFFF");
            ZoomGlobalState.currentCustomization.getOvalCustomization().progressColor2 = Color.parseColor("#BFFFFFFF");
            // Cancel Button Customization
            // ZoomGlobalState.currentCustomization.getCancelButtonCustomization().customImage = R.drawable.cancel_white;
            // ZoomGlobalState.currentCustomization.getCancelButtonCustomization().customImageLowLight = R.drawable.cancel_navy;
        }

        ZoomSDK.setCustomization(ZoomGlobalState.currentCustomization);
    }
}
