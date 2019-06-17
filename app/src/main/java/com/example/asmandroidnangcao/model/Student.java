package com.example.asmandroidnangcao.model;

public class Student {

    public static final String TABLE_STUDENT_NAME = "student_manager";
    public static final String ID_STUDENT = "id";
    public static final String STUDENT_NAME = "student_name";
    public static final String BIRTHDAY = "birthday";
    public static final String KEY_CLASS = "key_class";

    private int mID;
    private String mName;
    private String mBirthday;
    private int mIdClass;

    public static final String SQL_STUDENT = "CREATE TABLE "+ TABLE_STUDENT_NAME+" ("+
            ID_STUDENT + " integer primary key autoincrement, "+
            STUDENT_NAME + " TEXT, " +
            BIRTHDAY + " TEXT, "+
            KEY_CLASS + " integer, "+
          " FOREIGN KEY ( "+ KEY_CLASS +" ) REFERENCES "+ Class_model.TABLE_CLASS_NAME +" ( "+ Class_model.ID_CLASS +" ))";

    public Student(int mID, String mName, String mBirthday, int mIdClass) {
        this.mID = mID;
        this.mName = mName;
        this.mBirthday = mBirthday;
        this.mIdClass = mIdClass;
    }

    public Student(String mName, String mBirthday, int mIdClass) {
        this.mName = mName;
        this.mBirthday = mBirthday;
        this.mIdClass = mIdClass;
    }

    public Student(int key_id, String keyClass, String nameClass, int[] classId) {
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmBirthday() {
        return mBirthday;
    }

    public void setmBirthday(String mBirthday) {
        this.mBirthday = mBirthday;
    }

    public int getmIdClass() {
        return mIdClass;
    }

    public void setmIdClass(int mIdClass) {
        this.mIdClass = mIdClass;
    }
}
