package com.example.asmandroidnangcao.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmandroidnangcao.model.Class_model;

import java.util.ArrayList;
import java.util.List;


public class ClassDAO {
    private SQLiteDatabase db;
    private DbHelper dbHelper;

    public ClassDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();

    }

    public List<Class_model> getAllClass(String sql) {
        // lay danh sach lop trong bang roi dua vao list;
        List<Class_model> classList = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Class_model newClass = new Class_model(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            classList.add(newClass);
            cursor.moveToNext();
        }
        return classList;
    }

    public List<String> getNameClass(String sql) {
        List<String> listName = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            //String name = cursor.getString(cursor.getColumnIndex(Class_model.CLASS_NAME));
            // getColumIndex(ten_cot) la get cac phan tu cua cot dua vao ten_cot;
            String name = new String(cursor.getString(0));
            listName.add(name);
            cursor.moveToNext();
        }
        return listName;
    }

    public int getIdClass(String className) {
        int classId;
        String selectQuery = "SELECT "+ Class_model.ID_CLASS +" FROM "+ Class_model.TABLE_CLASS_NAME +
                " WHERE "+ Class_model.CLASS_NAME +" = '"+className+"'"; // dieu kien
        db = dbHelper.getReadableDatabase();
        if (className.equals("Show_All")){
            classId = -1;
        }else {
            Cursor cursor = db.rawQuery(selectQuery, null);
            cursor.moveToFirst();
            classId = cursor.getInt(cursor.getColumnIndex(Class_model.ID_CLASS));
            //classId = new Integer(cursor.getInt(0));
        }

        return classId;
    }

    public void addClass(Class_model classModel) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(classModel.KEY_CLASS, classModel.getmKeyClass());
        values.put(classModel.CLASS_NAME, classModel.getmClassName());
        db.insert(classModel.TABLE_CLASS_NAME, null, values);
        db.close();
    }

    public void deleteClass(int key_id) {
        db = dbHelper.getReadableDatabase();
        db.delete(Class_model.TABLE_CLASS_NAME, Class_model.ID_CLASS + " = ?", new String[]{String.valueOf(key_id)});
        db.close();
    }

    public void updateClass(Class_model classModel){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(classModel.KEY_CLASS, classModel.getmKeyClass());
        values.put(classModel.CLASS_NAME, classModel.getmClassName());
        db.update(Class_model.TABLE_CLASS_NAME,values, Class_model.ID_CLASS+" = ? ",new String[]{String.valueOf(classModel.getmId())});
        db.close();
    }

}
