package ir.vtj.ormy;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

import ir.vtj.ormy.OrmyException.OrmyException;
import ir.vtj.ormy.OrmyType.PrimaryKey;

import static com.google.gson.reflect.TypeToken.getParameterized;

/**
 * Created by JavaDroid on 4/26/2017 in MEEDC-GIS department.
 * hi@JavaDroid.ir
 */

public class Ormy {
    private static Application application;
    private static String dbName;

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
        application = app;
        dbName = databaseName;
        try {
            makeFile(getFileName());
        } catch (OrmyException.cantCreateDb cantCreateDb) {
            cantCreateDb.printStackTrace();
        }
    }

    private static void write(String text) {
        try {
            PrintWriter writer = new PrintWriter(getFileName(), "UTF-8");
            writer.println(text);
            writer.close();
        } catch (IOException e) {
            Log.e("Ormy", e.getMessage());
        }
    }

    private static void makeFile(String sFileName) throws OrmyException.cantCreateDb {
        try {
            File root = new File(sFileName.substring(0, sFileName.indexOf("/")));
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new OrmyException.cantCreateDb("can't create " + getFileName());
        }
    }

    private static String read() throws OrmyException.databaseNotFound {
        StringBuilder text = new StringBuilder();
        try {
            File file = new File(getFileName());

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            throw new OrmyException.databaseNotFound(getFileName() + "not found!");
        }
        return text.toString();
    }

    public static void save(Object object) {
        Gson gson = new Gson();
        String a = gson.toJson(object);
        write(a);

        Class test = object.getClass();
        for (Field field : test.getFields()) {
            PrimaryKey ormyTypeAnnotation = field.getAnnotation(PrimaryKey.class);
            // if (ormyTypeAnnotation != null)
            //ormyTypeAnnotation.isPrimaryKey()
        }
    }


    public static <E> List<E> getList(final Type type) throws OrmyException.databaseNotFound {
        String db = read();
        return new Gson().fromJson(db, getParameterized(List.class, type).getType());
    }


    public static Object fetchSingleData(Class clazz) throws OrmyException.tableNotFound, OrmyException.databaseNotFound {
        String db = read();
        if (db.length() > 0) {
            Gson gson = new Gson();
            return gson.fromJson(db, clazz);
        } else {
            throw new OrmyException.tableNotFound("table " + clazz.getName() + " not found!");
        }
    }


}
