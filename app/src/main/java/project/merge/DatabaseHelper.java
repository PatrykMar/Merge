package project.merge;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


import project.merge.model.Items;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "scoreManager";
    private static final String TABLE_SCORE = "score_table";
    private static final String KEY_NAME = "name";
    private static final String KEY_SCORE = "score";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SCORE_TABLE = "CREATE TABLE   " + TABLE_SCORE + " (" + KEY_NAME + " TEXT," + KEY_SCORE + " TEXT" + ")";
        db.execSQL(CREATE_SCORE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);
        onCreate(db);
    }

    void addScore(Items name, Items score) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name.getName());
        values.put(KEY_SCORE, score.getScore());

        db.insert(TABLE_SCORE, null, values);
        db.close();
    }

    public ArrayList<Items> getAllScores() {
        ArrayList<Items> itemsList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_SCORE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {

            String name = cursor.getString(0);
            String score = cursor.getString(1);
            Items newItem = new Items(name, score);

            itemsList.add(newItem);
        }
        return itemsList;
    }


    public int updateScore(Items score) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SCORE, score.getScore());

        return db.update(TABLE_SCORE, values, KEY_NAME + "=?", new String[]{score.getName()});
    }
}