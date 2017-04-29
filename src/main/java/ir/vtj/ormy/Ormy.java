package ir.vtj.ormy;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.google.gson.Gson;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Created by JavaDroid on 4/26/2017 in MEEDC-GIS department.
 * hi@JavaDroid.ir
 */

public class Ormy {
    public File mainFolder;
    public static Context context;

    public static void Init(Application app) {
        context = app.getApplicationContext();
        PackageManager m = app.getPackageManager();
        String s = app.getPackageName();
        PackageInfo p = null;
        try {
            p = m.getPackageInfo(s, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        s = p.applicationInfo.dataDir;
        // Utile.toast("The Directory is: " + s);
    }

    public static void save(Object object) {
        Gson gson = new Gson();
        String a = gson.toJson(object);
        Utile.toast(a);

        Class test = object.getClass();
        Utile.toast("Fields" + test.getFields().length);

        for (Field field : test.getFields()) {
            OrmyType ormyTypeAnnotation = field.getAnnotation(OrmyType.class);
            if (ormyTypeAnnotation != null)
                ir.vtj.ormy.Utile.toast(ormyTypeAnnotation.isPrimaryKey() ? "True" : "False");
            else
                ir.vtj.ormy.Utile.toast("isNull");
        }
    }


}
