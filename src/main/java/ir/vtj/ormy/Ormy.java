package ir.vtj.ormy;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by JavaDroid on 4/26/2017 in MEEDC-GIS department.
 * hi@JavaDroid.ir
 */

public class Ormy {
    public static Context context;
    public static Application application;
    public static String dbName;

    private static String getFileName() {
        PackageManager m = application.getPackageManager();
        String s = application.getPackageName();
        PackageInfo p = new PackageInfo();
        try {
            p = m.getPackageInfo(s, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return p.applicationInfo.dataDir + "/Ormy/" + dbName + ".ormy";
    }

    public static void Init(Application app, String databaseName) {
        context = app.getApplicationContext();
        application = app;
        dbName = databaseName;
        makeFile(getFileName());
    }

    private static void write(String text) {
        try {
            PrintWriter writer = new PrintWriter(getFileName(), "UTF-8");
            writer.println(text);
            writer.close();
        } catch (IOException e) {
            Utile.toast(e.getMessage());
        }
    }

    private static void makeFile(String sFileName) {
        try {
            File root = new File(sFileName.substring(0, sFileName.indexOf("/")));
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            //writer.append(sBody);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save(Object object) {
        Gson gson = new Gson();
        String a = gson.toJson(object);
        write(a);

       /* Class test = object.getClass();
        Utile.toast("Fields" + test.getFields().length);

        for (Field field : test.getFields()) {
            OrmyType ormyTypeAnnotation = field.getAnnotation(OrmyType.class);
            if (ormyTypeAnnotation != null)
                ir.vtj.ormy.Utile.toast(ormyTypeAnnotation.isPrimaryKey() ? "True" : "False");
            else
                ir.vtj.ormy.Utile.toast("isNull");
        }*/
    }


}
