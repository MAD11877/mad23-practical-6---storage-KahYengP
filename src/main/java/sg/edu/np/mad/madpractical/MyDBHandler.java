package sg.edu.np.mad.madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MyDBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_FOLLOWED = "followed";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_USERNAME + " TEXT,"
                    + COLUMN_DESCRIPTION + " TEXT, "
                    + COLUMN_FOLLOWED + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory,
                       int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

        for (int i = 1; i < 22; i++) {
            Random r = new Random();
            int randomUsername = r.nextInt(1000000000);
            int randomDescription = r.nextInt(1000000000);

            String name = "Name-" + randomUsername;
            String description = "Description-" + randomDescription;
            int id = i;
            boolean followed = false;

            ContentValues values = new ContentValues();
            values.put(COLUMN_USERNAME, name);
            values.put(COLUMN_DESCRIPTION, description);
            values.put(COLUMN_ID, id);
            values.put(COLUMN_FOLLOWED, followed);

            db.insert(TABLE_NAME, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();

        try (SQLiteDatabase db = getReadableDatabase(); Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)) {
            while (cursor.moveToNext()) {
                String name = cursor.getString((int) cursor.getColumnIndex(COLUMN_USERNAME));
                String description = cursor.getString((int) cursor.getColumnIndex(COLUMN_DESCRIPTION));
                int id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
                boolean followed = Boolean.parseBoolean(cursor.getString((int) cursor.getColumnIndex(COLUMN_FOLLOWED)));

                User user = new User(name, description, id, followed);
                userList.add(user);
            }
        }

        return userList;
    }

     public void updateUser(User user) {
         try (SQLiteDatabase db = getWritableDatabase()) {
             ContentValues values = new ContentValues();
             values.put(COLUMN_USERNAME, user.getUserName());
             values.put(COLUMN_DESCRIPTION, user.getDescription());
             values.put(COLUMN_FOLLOWED, user.isFollowed());

             db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(user.getId())});
         }
     }
}


