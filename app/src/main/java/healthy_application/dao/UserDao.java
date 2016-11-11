package healthy_application.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import healthy_application.models.User;

/**
 * Created by Home on 28/10/2016.
 */

public class UserDao extends SQLiteOpenHelper implements IDao<User> {
    public static final String DATABASE_NAME = "ManagerDreamUser.db";
    public static final String TABLE_NAME = "user_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "LOGIN";
    public static final String COL_3 = "PASSWORD";

    public UserDao(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" ("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT" +
                ","+COL_2+" INTEGER" + ","+ COL_3 + "TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    @Override
    public boolean insert(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,user.getLogin());
        contentValues.put(COL_3, user.getPassword());

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    @Override
    public boolean update(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,user.getId());
        contentValues.put(COL_2,user.getLogin());
        contentValues.put(COL_3, user.getPassword());

        String idToString = Integer.toString(user.getId());
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { idToString });
        return true;
    }

    @Override
    public Cursor search() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    @Override
    public int delete(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        String idToString = Integer.toString(user.getId());
        return db.delete(TABLE_NAME, "ID = ?",new String[] {idToString});
    }
}