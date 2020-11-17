/********* Medallia.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>
#import <MedalliaDigitalSDK/MedalliaDigitalSDK-Swift.h>

@interface Medallia : CDVPlugin {
  // Member variables go here.
}

- (void)sdkInit: (CDVInvokedUrlCommand*)command;
- (void)showForm: (CDVInvokedUrlCommand*)command;
- (void)setCustomPatameters: (CDVInvokedUrlCommand*)command;
@end

@implementation Medallia

- (void)sdkInit: (CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* SDK_API_TOKEN = [command.arguments objectAtIndex:0];

    [MedalliaDigital sdkInitWithToken:SDK_API_TOKEN success:^{
            //Handle successful initiation of Medallia SDK
            NSLog(@"Init Success");
        } failure:^(MDExternalError * _Nonnull error) {
            //Handle failure to initiate Medallia SDK
            NSLog(@"Init Failure");
        }];
}

- (void)showForm: (CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* FORM_ID = [command.arguments objectAtIndex:0];

    [MedalliaDigital showForm:FORM_ID success:^{
            //Handle successfuly showing a form to your user
            NSLog(@"Show Form Success");
        } failure:^(MDExternalError * _Nonnull error) {
            //Handle failure to show a form to your user
            NSLog(@"Show Form Failure");
        }];
}

- (void)updateCustomLocale: (CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* LOCALE = [command.arguments objectAtIndex:0];

    [MedalliaDigital updateCustomLocale:LOCALE success:^(NSString * _Nullable message) {
        NSLog(@"updateCustomLocale Success");
    } failure:^(MDExternalError * _Nonnull error) {
        NSLog(@"updateCustomLocale Failure");
    }];

//    if (echo != nil && [echo length] > 0) {
//        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:echo];
//    } else {
//        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
//    }
//    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
