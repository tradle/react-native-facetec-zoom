//
//  Phoenix.h
//  Wrapper for openbr,opencv
//
//  Created by FaceTec, Inc. on 7/27/15.
//  Copyright (c) 2015 FaceTec, Inc.. All rights reserved.
//

#ifndef phoenix_h
#define phoenix_h

#import <Foundation/Foundation.h>

#import "TargetConditionals.h"
#if TARGET_OS_IPHONE

#import <UIKit/UIKit.h>
typedef UIImage NUIImage;

#else

#import <AppKit/AppKit.h>
typedef NSImage NUIImage;

#endif

#ifdef __cplusplus

extern "C" {
#endif
    
    void phoenix_lPf27TbPkLq1uIfS(void);
    void phoenix_lKj82WAS6aQz5WcC(NSString* a);
    BOOL phoenix_iaajaZhWzvj3MKPm(void);
    void phoenix_CnKQPBSbIbK9gKCX(NSString* a, NSString* b);
    NSArray<NUIImage*>* phoenix_GX53wOD1Ndj5QdEe(void);
    bool phoenix_OicSjtrsMLbAJKJD(void);
    float phoenix_Xo2YYpFu986epKaQ(void);
    void phoenix_gSo5F8tVxCpN0Sm0(NSString* a, int b);
    void phoenix_kX4ywfBvjrbEK5Qz(NSString* a, double b);
    BOOL _phoenix_ndVMt1Yw5YFQyJyu(void);
    NSString* phoenix_Pz3YScm0u5tV6gPx(int a);
    void phoenix_QtheL4IKBJ2d9MQO(NSString* a, NSString* c);
    void phoenix_Yirn4qr6XrvbRLup(BOOL a);
    void phoenix_549XoTFelIGn5LUp(NSInteger a, NSArray<NUIImage*>* b, NSArray<NSString*>* c);
    NSInteger phoenix_7YWMK4DSedwa6lZm(void);
    NSInteger phoenix_72u0AoxArPBLU7Do(void);
    NSInteger phoenix_pnroj51UjitkR3fh(void);
    NSInteger phoenix_okbwpkWJ51xGqrux(void);
    NSData* phoenix_cKy4Hl7o1cY23iGa(void);
    NSData* phoenix_hWonTX79o0Z1BCKv(void);
    void phoenix_ve4qqM3veuLFXJmK(double a);
    void phoenix_nYonFIN6tpvYhf4o(BOOL a);
    void phoenix_alsOAKSKJghPt0L6(int a);
    void phoenix_BWflNwOjel38kaLC(int a);
    void phoenix_eMa2UmYP10ekkccY(BOOL a, BOOL b);
    void phoenix_ofUxBWAfjWzIGozb(BOOL a);
    void phoenix_Pi2sj1Rv8ws6xLUZ(double a, double b);
    void phoenix_uislDN4WMA2nDWR1(BOOL a);
    void phoenix_4farrhek1DkZ28JU(CVImageBufferRef a);
    NSInteger phoenix_ZdkPQDDlbZuYCoeS(void);
    void phoenix_xNIhGJKiUZKJ416P(BOOL a);
    void phoenix_t7MzyV3EY3BMpBtd(NSString* a, NSString *b);
    NSInteger phoenix_E34piztUVqT9M4S9(void);
    float phoenix_jCmBQLcRRPka4PnV(void);
    NSInteger phoenix_WopRt6E2e61sIfdZ(void);
    void phoenix_TVjGD3XNZHCjokvd(void);
    void phoenix_uGqcXddpi7BFbArG(NSString* a);
    NSInteger phoenix_9MLryc5ohgpmTPeJ(void);
    BOOL phoenix_9Die872hGDSijwAX(void);
    BOOL phoenix_0fKa70gh7TTNtIln(void);
    BOOL phoenix_C2N7QuQ33F5qwBMX(BOOL a);
    void phoenix_qBaluj1xswH6CjWs(void);
    void phoenix_K5CjmjHpAG27j6Nn(void);
    void phoenix_RfI7f5xa0rr8KQ2e(NSString* a);
    NSDictionary* phoenix_OeQX8rqgh0wFbMjm(NSString*a);
    BOOL phoenix_VzQG0bJTlce99BF3(NSData *data, NSData *signature, NSString *publicKeyStr);
    NSData* phoenix_DHBOv5YbjkL84VpP(NSData *keyData, NSData *plainTextData);
    
#ifdef __cplusplus
}
#endif

#endif
