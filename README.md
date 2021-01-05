# cordova-plugin-medallia
cordova plugin for medallia

## iOS(CocoaPods)
- Add sdk to Podfile

```shell script
source 'https://cdn.cocoapods.org/'
platform :ios, '11.0'
use_frameworks!
target 'ProjectName' do
	project 'ProjectName.xcodeproj'
	...
	pod 'MedalliaDigitalSDK', :http => 'https://repository.medallia.com/digital-generic/com/medallia/digital/ios-sdk/3.6.0/ios-sdk-3.6.0-11.6.zip' #MedalliaSDK
end
```

- then run

```sh
$ pod install
```

- cordova plugin add cordova-plugin-medallia

## Android(Maven)

- android/build.gradle
```shell script
allprojects {
    repositories {
        ...
        maven {
            url "https://repository.medallia.com/digital-maven/"
        }
    }
}
```
- app/src/build.gradle

```shell script
dependencies {
 ...
 implementation 'com.medallia.digital.mobilesdk:android-sdk:3.6.1'
}
```

- cordova plugin add cordova-plugin-medallia

## Usage

```shell script
cordova.plugins.Medallia.sdkInit("SDK_API_TOKEN");
cordova.plugins.Medallia.updateCustomLocale("LOCALE");
cordova.plugins.Medallia.showForm("FORM_ID");
cordova.plugins.Medallia.setCustomParameters({"key": "value", "key2": true, "key3": 1});
```