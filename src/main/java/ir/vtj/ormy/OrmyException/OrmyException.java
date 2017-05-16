package ir.vtj.ormy.OrmyException;

/**
 * Created by JavaDroid on 4/30/2017 in MEEDC-GIS department.
 * hi@JavaDroid.ir
 */

public class OrmyException {

    public static class tableNotFound extends Exception {
        public tableNotFound(String message) {
            super(message);
        }
    }

    public static class databaseNotFound extends Exception {
        public databaseNotFound(String message) {
            super(message);
        }
    }

    public static class cantCreateDb extends Exception {
        public cantCreateDb(String message) {
            super(message);
        }
    }

    public static class ORMYexception extends Exception {
        public ORMYexception(String message) {
            super(message);
        }
    }


}
