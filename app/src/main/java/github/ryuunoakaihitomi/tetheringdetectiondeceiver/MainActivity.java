package github.ryuunoakaihitomi.tetheringdetectiondeceiver;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast toast = Toast.makeText(this, debugMsg(), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        finish();
    }

    private String debugMsg() {
        return "debugMsg" + C.LS +
                "Logcat Tag: " + C.TAG + C.LS +
                "XHook Enable: " + HookHelper.isXposedActive + C.LS +
                "isTetheringWorking: " + TetheringHelper.isTetheringWorking(this) + C.LS +
                "getTetheredFaces: " + Arrays.toString(TetheringHelper.getTetheredFaces(this));
    }
}
