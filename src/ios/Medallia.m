/********* Medallia.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>
#import <MedalliaDigitalSDK/MedalliaDigitalSDK-Swift.h>

@interface Medallia : CDVPlugin {
  // Member variables go here.
}

- (void)sdkInit: (CDVInvokedUrlCommand*)command;
- (void)showForm: (CDVInvokedUrlCommand*)command;
- (void)updateCustomLocale: (CDVInvokedUrlCommand*)command;
- (void)setCustomParameters: (CDVInvokedUrlCommand*)command;
- (void)revertStopSDK: (CDVInvokedUrlCommand*)command;
@end

@implementation Medallia

- (void)sdkInit: (CDVInvokedUrlCommand*)command
{
    [self.commandDelegate runInBackground:^{
    CDVPluginResult* pluginResult = nil;
    NSString* SDK_API_TOKEN = [command.arguments objectAtIndex:0];

    [MedalliaDigital sdkInitWithToken:SDK_API_TOKEN success:^{
            //Handle successful initiation of Medallia SDK
            NSLog(@"Init Success");
        } failure:^(MDExternalError * _Nonnull error) {
            //Handle failure to initiate Medallia SDK
            NSLog(@"Init Failure");
        }];
        }];
}

- (void)showForm: (CDVInvokedUrlCommand*)command
{
    [self.commandDelegate runInBackground:^{
        CDVPluginResult* pluginResult = nil;
        NSString* FORM_ID = [command.arguments objectAtIndex:0];
        [MedalliaDigital showForm:FORM_ID success:^{
                //Handle successfuly showing a form to your user
                NSLog(@"Show Form Success");
            } failure:^(MDExternalError * _Nonnull error) {
                //Handle failure to show a form to your user
                NSLog(@"Show Form Failure");
            }];
    }];
}

- (void)updateCustomLocale: (CDVInvokedUrlCommand*)command
{
    [self.commandDelegate runInBackground:^{
    CDVPluginResult* pluginResult = nil;
    NSString* LOCALE = [command.arguments objectAtIndex:0];

    [MedalliaDigital updateCustomLocale:LOCALE success:^(NSString * _Nullable message) {
        NSLog(@"updateCustomLocale Success");
    } failure:^(MDExternalError * _Nonnull error) {
        NSLog(@"updateCustomLocale Failure");
    }];
    }];

//    if (echo != nil && [echo length] > 0) {
//        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:echo];
//    } else {
//        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
//    }
//    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)setCustomParameters: (CDVInvokedUrlCommand*)command
{
    [self.commandDelegate runInBackground:^{
    CDVPluginResult* pluginResult = nil;
    NSError *error = nil;
    NSDictionary *dict = [command.arguments objectAtIndex:0];
    NSJSONSerialization* params = [command.arguments objectAtIndex:0];
    NSData* paramsData = [NSJSONSerialization dataWithJSONObject:params options:NSJSONWritingPrettyPrinted error:&error];
    id result = [NSJSONSerialization JSONObjectWithData:paramsData options:kNilOptions error:&error];
//    NSArray *resultArray = [NSJSONSerialization JSONObjectWithData:[command.arguments objectAtIndex:0]; options:NSJSONReadingAllowFragments error:nil];
//    for(NSDictionary *param in params) {
//        NSString *array = [param objectForKey:@"albumtitle"];
//        NSLog(@"albumtitle:%@", array);
//    }
    //for (NSDictionary *dict in resultArray) {
      //  NSLog(@"albumtitle:%@", resultArray);
    //}
    NSLog(@"medalliaLog:%@", dict);
    for (NSString *key in dict) {
        NSLog(@"%@ = %@", key, [dict objectForKey:key]);
        [MedalliaDigital setCustomParameterWithName:key value: [dict objectForKey:key]];
    }
//    [MedalliaDigital setCustomParameters:@{@"OSType" : [result objectForKey:@"OSType"],
//    @"OSVersion" : [result objectForKey:@"OSVersion"],
//    @"PhoneVendor" : [result objectForKey:@"PhoneVendor"],
//    @"PhoneModel" : [result objectForKey:@"PhoneModel"],
//    @"SanctVersion" : [result objectForKey:@"SanctVersion"],
//    @"NumDevices" : [result objectForKey:@"NumDevices"],
//    @"DeviceType" : [result objectForKey:@"DeviceType"],
//    @"DeviceName" : [result objectForKey:@"DeviceName"],
//    @"LoginStatus" : [result objectForKey:@"LoginStatus"],
//    @"VisitNumber" : [result objectForKey:@"VisitNumber"],
//    @"TestMode" : [result objectForKey:@"TestMode"],
//    @"CountryCode" : [result objectForKey:@"CountryCode"]}];
}];
}

- (void)revertStopSdk: (CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* FORM_ID = [command.arguments objectAtIndex:0];

    [MedalliaDigital revertStopSDK];
}

@end
