package org.apache.cordova.medallia;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;
import java.util.HashMap;

//import java.lang.reflect.Method;
//import android.app.Application;
import android.util.Log;

import com.medallia.digital.mobilesdk.MDExternalError;
import com.medallia.digital.mobilesdk.MDResultCallback;
import com.medallia.digital.mobilesdk.MedalliaDigital;
import com.medallia.digital.mobilesdk.*;
import android.annotation.SuppressLint;

/**
 * This class echoes a string called from JavaScript.
 */
public class Medallia extends CordovaPlugin {

//    public static Application mApplication;
//
//    public static void init(Application application){
//        mApplication = application;
//    }
//
//    public static Application getApplication(){
//        if (mApplication == null) {
//            mApplication = getApplicationInner();
//        }
//        return mApplication;
//    }
//
//    public static Application getApplicationInner() {
//        try {
//            Class<?> activityThread = Class.forName("android.app.ActivityThread");
//
//            Method currentApplication = activityThread.getDeclaredMethod("currentApplication");
//            Method currentActivityThread = activityThread.getDeclaredMethod("currentActivityThread");
//
//            Object current = currentActivityThread.invoke((Object)null);
//            Object app = currentApplication.invoke(current);
//
//            return (Application)app;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        System.out.println(action);
        if ("sdkInit".equals(action)) {
            String sdkApiToken = args.getString(0);
            System.out.println(sdkApiToken);
//            this.sdkInit(sdkApiToken, sdkApiToken, callbackContext);
//            this.initFormListeners();
            return true;
        } else if ("showForm".equals(action)) {
            String formId = args.getString(0);
            System.out.println(formId);
            this.showForm(formId, callbackContext);
            return true;
        } else if ("updateCustomLocale".equals(action)) {
            String locale = args.getString(0);
            System.out.println(locale);
            this.updateCustomLocale(locale, callbackContext);
            return true;
        } else if ("setCustomParameters".equals(action)) {
            this.setCustomParameters(args);
            return true;
        } else if ("revertStopSDK".equals(action)) {
            this.revertStopSDK();
            return true;
        }
        return false;
    }

    private void sdkInit(String sdkApiToken1, String sdkApiToken, CallbackContext callbackContext) {
        MedalliaDigital.init(this.cordova.getActivity().getApplication(), sdkApiToken, new MDResultCallback() {
            @Override
            public void onSuccess() {
                Log.i(getClass().getSimpleName(),
                        "MedalliaDigital SDK was initialized successfully");
            }

            @Override
            public void onError(MDExternalError error) {
                Log.e(getClass().getSimpleName(),
                        "Failed to initialize MedalliaDigital SDK : " + error.getMessage());
            }
        });
//        if (sdkApiToken != null && sdkApiToken.length() > 0) {
//            callbackContext.success(sdkApiToken);
//        } else {
//            callbackContext.error("Expected one non-empty string argument.");
//        }
    }

    private void initFormListeners() {
        MedalliaDigital.setFormListener(new MDFormListenerV2() {
            @Override
            public void onFormSubmitted(long timestamp, String formId,
                                        FormTriggerType formTriggerType) {
                System.out.println("onFormSubmitted");
//                printMessage(String.format(getString(R.string.form_listener_format),
//                        formId, formTriggerType.name(), "submitted"));
            }

            @Override
            public void onFormDismissed(long timestamp, String formId,
                                        FormTriggerType formTriggerType) {
                System.out.println("onFormDismissed");
//                printMessage(String.format(getString(R.string.form_listener_format),
//                        formId, formTriggerType.name(), "dismissed"));
            }

            @Override
            public void onFormClosed(long timestamp, String formId,
                                     FormTriggerType formTriggerType) {
                System.out.println("onFormClosed");
//                printMessage(String.format(getString(R.string.form_listener_format),
//                        formId, formTriggerType.name(), "closed"));
            }

            @SuppressLint("StringFormatMatches")
            @Override
            public void onFormDisplayed(long timestamp, String formId,
                                        FormTriggerType formTriggerType,
                                        String formLocaleSet, String formLocaleDisplay) {
                System.out.println("onFormDisplayed");
//                printMessage(String.format(getString(R.string.form_listener_format),
//                        formId, formTriggerType.name(), formLocaleSet,
//                        formLocaleDisplay, "displayed"));
            }

            @Override
            public void onFormLinkSelected(long timestamp,
                                           String formId,
                                           FormTriggerType formTriggerType,
                                           String url, boolean isBlocked) {
                System.out.println("onFormLinkSelected");
//                if (isBlocked) {
//                    printMessage(String.format(getString(R.string.form_listener_format),
//                            formId, formTriggerType.name(), "blocked by internal url : " + url));
//                } else {
//                    printMessage(String.format(getString(R.string.form_listener_format),
//                            formId, formTriggerType.name(), url));
//                }
            }

            @Override
            public void onFormThankYouPrompt(long timestamp, String formId,
                                             FormTriggerType formTriggerType) {
                System.out.println("onFormThankYouPrompt");
//                printMessage(String.format(getString(R.string.form_listener_format),
//                        formId, formTriggerType.name(), "thankYouPrompt"));
            }
        });

    }

    private void showForm(String formId, CallbackContext callbackContext) {
        MedalliaDigital.showForm(formId, new MDResultCallback() {
            @Override
            public void onSuccess() {
                Log.i(getClass().getSimpleName(),
                        "Handle successful showing form " + formId);
            }
            @Override
            public void onError(MDExternalError error) {
                System.out.println(error.getMessage());
                Log.i(getClass().getSimpleName(),
                        "Handle failure to show form " + formId);
            }
        });
    }

    private void updateCustomLocale(String locale, CallbackContext callbackContext) {
        MedalliaDigital.updateCustomLocale(locale, new MDCallback() {
            @Override
            public void onSuccess(String message) {
                Log.i(getClass().getSimpleName(),
                        "updateCustomLocale successful");
            }
            @Override
            public void onError(MDExternalError error) {
                Log.i(getClass().getSimpleName(),
                        "updateCustomLocale failure");
            }
        });
    }

    private void setCustomParameters(JSONArray args) throws JSONException {
        HashMap<String, Object> values = new HashMap<>();
        System.out.println(args.getJSONObject(0));
        Iterator iterator = args.getJSONObject(0).keys();
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            values.put(key, args.getJSONObject(0).get(key));
            System.out.println("Key : " + key + ", value : "
                    + args.getJSONObject(0).get(key));
        }

        MedalliaDigital.setCustomParameters(values);
    }

    private void revertStopSDK() {
        MedalliaDigital.revertStopSDK();
    }
}