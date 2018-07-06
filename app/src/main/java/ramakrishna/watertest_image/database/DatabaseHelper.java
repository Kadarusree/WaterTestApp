package ramakrishna.watertest_image.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ravi on 15/03/18.
 */
 
public class DatabaseHelper extends SQLiteOpenHelper {
 
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "result_db";
 
 
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
 
        // create notes table
        db.execSQL(Result.CREATE_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Result.TABLE_NAME);
 
        // Create tables again
        onCreate(db);
    }

    public long insertResult(Result mResult) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Result.COLUMN_TEST_ID, mResult.getTest_id());
        values.put(Result.COLUMN_NAME, mResult.getTestName());
        values.put(Result.COLUMN_RESULT, mResult.getTestResult());
        values.put(Result.COLUMN_UNIT, mResult.getUnit());
        values.put(Result.COLUMN_COLOR, mResult.getColor());



        // insert row
        long id = db.insert(Result.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public  int clearDB(){
        SQLiteDatabase db = this.getWritableDatabase();
       int count = db.delete(Result.TABLE_NAME,null,null);
       return count;
    }


    public List<Result> getTestResults(int id) {
        List<Result> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Result.TABLE_NAME + " WHERE "+Result.COLUMN_TEST_ID+" = "+id+ " ORDER BY " +
                Result.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Result note = new Result();
                note.setId(cursor.getInt(cursor.getColumnIndex(Result.COLUMN_ID)));
                note.setTest_id(cursor.getInt(cursor.getColumnIndex(Result.COLUMN_TEST_ID)));
                note.setTestName(cursor.getString(cursor.getColumnIndex(Result.COLUMN_NAME)));
                note.setTestResult(cursor.getString(cursor.getColumnIndex(Result.COLUMN_RESULT)));
                note.setUnit(cursor.getString(cursor.getColumnIndex(Result.COLUMN_UNIT)));
                note.setColor(cursor.getString(cursor.getColumnIndex(Result.COLUMN_COLOR)));
                note.setTimeStamp(cursor.getString(cursor.getColumnIndex(Result.COLUMN_TIMESTAMP)));


                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public List<Result> getAllNotes() {
        List<Result> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Result.TABLE_NAME + " ORDER BY " +
                Result.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Result note = new Result();
                note.setId(cursor.getInt(cursor.getColumnIndex(Result.COLUMN_ID)));
                note.setTest_id(cursor.getInt(cursor.getColumnIndex(Result.COLUMN_TEST_ID)));
                note.setTestName(cursor.getString(cursor.getColumnIndex(Result.COLUMN_NAME)));
                note.setTestResult(cursor.getString(cursor.getColumnIndex(Result.COLUMN_RESULT)));
                note.setUnit(cursor.getString(cursor.getColumnIndex(Result.COLUMN_UNIT)));
                note.setColor(cursor.getString(cursor.getColumnIndex(Result.COLUMN_COLOR)));
                note.setTimeStamp(cursor.getString(cursor.getColumnIndex(Result.COLUMN_TIMESTAMP)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }


    public List<Result> getmailNotes() {
        List<Result> notes = new ArrayList<>();


        String selectQuery =   "SELECT "+Result.COLUMN_ID+", "+Result.COLUMN_TEST_ID+", "+Result.COLUMN_NAME+", "+Result.COLUMN_RESULT+", "+Result.COLUMN_UNIT+", "+ Result.COLUMN_COLOR+", MAX(timestamp) as timestamp FROM results GROUP BY testname";


        System.out.println(selectQuery);



        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Result note = new Result();
                note.setId(cursor.getInt(cursor.getColumnIndex(Result.COLUMN_ID)));
                note.setTest_id(cursor.getInt(cursor.getColumnIndex(Result.COLUMN_TEST_ID)));
                note.setTestName(cursor.getString(cursor.getColumnIndex(Result.COLUMN_NAME)));
                note.setTestResult(cursor.getString(cursor.getColumnIndex(Result.COLUMN_RESULT)));
                note.setUnit(cursor.getString(cursor.getColumnIndex(Result.COLUMN_UNIT)));
                note.setColor(cursor.getString(cursor.getColumnIndex(Result.COLUMN_COLOR)));
                note.setTimeStamp(cursor.getString(cursor.getColumnIndex(Result.COLUMN_TIMESTAMP)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }
}