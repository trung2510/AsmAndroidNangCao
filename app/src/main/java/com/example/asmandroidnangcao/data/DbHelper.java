package com.example.asmandroidnangcao.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.asmandroidnangcao.model.Class_model;
import com.example.asmandroidnangcao.model.Student;



public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "assigment1_qlsv";
    private Context context;

    private static final int VERSION = 1 ;
    private static final String TAG = "DbHelper";
    private SQLiteDatabase db;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Class_model.SQL_CLASS);
        db.execSQL(Student.SQL_STUDENT);
        Log.d(TAG, "onCreate: Tạo Database Thành Công");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_class_table = "DROP TABLE IF EXISTS "+ Class_model.TABLE_CLASS_NAME;
        db.execSQL(drop_class_table);
        String drop_student_table = "DROP TABLE IF EXISTS "+ Student.TABLE_STUDENT_NAME;
        db.execSQL(drop_student_table);
        onCreate(db);
        Log.d(TAG, "Xóa Và Tạo Lại Bảng ");
    }


}
