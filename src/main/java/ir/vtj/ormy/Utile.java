package ir.vtj.ormy;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by JavaDroid on 4/26/2017 in MEEDC-GIS department.
 * hi@JavaDroid.ir
 */

public class Utile {

    public static void toast(String text) {
        Toast.makeText(Ormy.context, text, Toast.LENGTH_SHORT).show();
        Log.e("Log", text);
    }
}
