package github.ryuunoakaihitomi.tetheringdetectiondeceiver;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static github.ryuunoakaihitomi.tetheringdetectiondeceiver.C.TAG;

class TetherHelper {

    static String[] getTetheredFaces(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            @SuppressWarnings("JavaReflectionMemberAccess")
            Method getTetheredIfaces = ConnectivityManager.class.getMethod("getTetheredIfaces");
            return (String[]) getTetheredIfaces.invoke(manager);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            Log.e(TAG, "getTetheredFaces: ", e);
        }
        return null;
    }

    static boolean isTethering(Context context) {
        String[] tetheredFaces = getTetheredFaces(context);
        return tetheredFaces != null && tetheredFaces.length > 0;
    }
}
