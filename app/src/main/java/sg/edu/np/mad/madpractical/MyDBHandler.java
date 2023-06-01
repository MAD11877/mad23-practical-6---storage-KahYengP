package sg.edu.np.mad.madpractical;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    public static final String TABLE_USER = "User";

    public static final String COLUMN_USERNAME = "username";

    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ID = "id";

    public static final boolean COLUMN_FOLLOW = Boolean.parseBoolean("follow");

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_USER_TABLE = "CREATE TABLE " +
                TABLE_USER +
                "(" + COLUMN_USERNAME + " TEXT,"
                + COLUMN_DESCRIPTION
                + " TEXT," + COLUMN_ID + " INTEGER," + COLUMN_FOLLOW + "BOOLEAN,)";
        db.execSQL(CREATE_USER_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUserName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_ID, user.getId());
        values.put(String.valueOf(COLUMN_FOLLOW), user.getFollowed());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_USER, null, values);
        db.close();
    }


}

