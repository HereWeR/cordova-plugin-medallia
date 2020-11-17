# cordova-plugin-medallia
cordova plugin for medallia

## iOS
#### 1. Add sdk to Podfile

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

#### 2. cordova plugin add cordova-plugin-medallia

#### then run

```sh
$ pod install
```

## Android(Maven)

#### 1. android/build.gradle
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
#### 2. app/src/build.gradle

```shell script
dependencies {
 ...
 implementation 'com.medallia.digital.mobilesdk:android-sdk:3.6.1'
}
```

#### 3. cordova plugin add cordova-plugin-medallia

## Usage

```shell script
cordova.plugins.Medallia.sdkInit("SDK_API_TOKEN");
cordova.plugins.Medallia.updateCustomLocale("LOCALE");
cordova.plugins.Medallia.showForm("FORM_ID");
```