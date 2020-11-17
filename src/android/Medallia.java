package org.apache.cordova.medallia;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Method;
import android.app.Application;
import android.util.Log;

import com.medallia.digital.mobilesdk.MDExternalError;
import com.medallia.digital.mobilesdk.MDResultCallback;
import com.medallia.digital.mobilesdk.MedalliaDigital;
import com.medallia.digital.mobilesdk.*;

/**
 * This class echoes a string called from JavaScript.
 */
public class Medallia extends CordovaPlugin {

    public static Application mApplication;

    public static void init(Application application){
        mApplication = application;
    }

    public static Application getApplication(){
        if (mApplication == null) {
            mApplication = getApplicationInner();
        }
        return mApplication;
    }

    public static Application getApplicationInner() {
        try {
            Class<?> activityThread = Class.forName("android.app.ActivityThread");

            Method currentApplication = activityThread.getDeclaredMethod("currentApplication");
            Method currentActivityThread = activityThread.getDeclaredMethod("currentActivityThread");

            Object current = currentActivityThread.invoke((Object)null);
            Object app = currentApplication.invoke(current);

            return (Application)app;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        System.out.println("sdkInit");
        if ("sdkInit".equals(action)) {
            String sdkApiToken = args.getString(0);
            System.out.println(sdkApiToken);
            this.sdkInit(getApplication(), sdkApiToken, callbackContext);
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
        }
        return false;
    }

    private void sdkInit(Application application, String sdkApiToken, CallbackContext callbackContext) {
        MedalliaDigital.init(application, sdkApiToken, new MDResultCallback() {
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

    private void showForm(String formId, CallbackContext callbackContext) {
        MedalliaDigital.showForm(formId, new MDResultCallback() {
            @Override
            public void onSuccess() {
                Log.i(getClass().getSimpleName(),
                        "Handle successful showing a form");
            }
            @Override
            public void onError(MDExternalError error) {
                Log.i(getClass().getSimpleName(),
                        "Handle failure to show a form");
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
}