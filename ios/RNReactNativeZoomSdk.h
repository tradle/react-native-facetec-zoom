
#if __has_include("RCTBridgeModule.h")
#import "RCTBridgeModule.h"
#import <RCTImageStoreManager.h>
#import <RCTRootView.h>
#else
#import <React/RCTBridgeModule.h>
#import <React/RCTImageStoreManager.h>
#import <React/RCTRootView.h>
#endif

@interface RNReactNativeZoomSdk : NSObject <RCTBridgeModule>

@end
  
