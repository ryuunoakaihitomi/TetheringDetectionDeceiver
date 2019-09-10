package github.ryuunoakaihitomi.tetheringdetectiondeceiver;

import android.content.pm.ApplicationInfo;
import android.net.ConnectivityManager;
import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static github.ryuunoakaihitomi.tetheringdetectiondeceiver.C.TAG;

public class MainHook implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if (lpparam.packageName.equals(BuildConfig.APPLICATION_ID)) {
            XposedHelpers.setStaticBooleanField(
                    XposedHelpers.findClass(BuildConfig.APPLICATION_ID + ".HookHelper", lpparam.classLoader),
                    "isXposedActive", true);
            return;
        }

        XposedHelpers.findAndHookMethod(ConnectivityManager.class, "getTetheredIfaces", new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                if ((lpparam.appInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {
                    Log.d(TAG, "beforeHookedMethod: " + lpparam.packageName + " is a system app.Skipping...");
                    return;
                }

                Log.i(TAG, "beforeHookedMethod: ConnectivityManager.getTetheredIfaces() intercepted! " +
                        "pkg=" + lpparam.packageName);
                param.setResult(new String[0]);
            }
        });
    }
}
