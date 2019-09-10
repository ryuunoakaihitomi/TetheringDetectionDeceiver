package github.ryuunoakaihitomi.tetheringdetectiondeceiver;

import android.content.Context;
import android.net.ConnectivityManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class TetheringHelper {

    static String[] getTetheredFaces(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            @SuppressWarnings("JavaReflectionMemberAccess")
            Method getTetheredIfaces = ConnectivityManager.class.getMethod("getTetheredIfaces");
            return (String[]) getTetheredIfaces.invoke(manager);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    static boolean isTetheringWorking(Context context) {
        String[] tetheredFaces = getTetheredFaces(context);
        return tetheredFaces != null && tetheredFaces.length > 0;
    }
}
