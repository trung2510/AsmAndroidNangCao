package com.example.asmandroidnangcao.model;

public class Class_model {

    public static final String TABLE_CLASS_NAME = "class_manager";
    public static final String ID_CLASS = "id";
    public static final String KEY_CLASS = "key_class";
    public static final String CLASS_NAME = "class_name";

    private int mId;
    private String mKeyClass;
    private String mClassName;

    public static final String SQL_CLASS = "CREATE TABLE "+ TABLE_CLASS_NAME+" ("+
            ID_CLASS + " integer primary key autoincrement, "+
            KEY_CLASS + " TEXT, " +
            CLASS_NAME + " TEXT) ";

    public Class_model() {
    }

    public Class_model(String mKeyClass, String mClassName) {
        this.mKeyClass = mKeyClass;
        this.mClassName = mClassName;
    }

    public Class_model(int mId, String mKeyClass, String mClassName) {
        this.mId = mId;
        this.mKeyClass = mKeyClass;
        this.mClassName = mClassName;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmKeyClass() {
        return mKeyClass;
    }

    public void setmKeyClass(String mKeyClass) {
        this.mKeyClass = mKeyClass;
    }

    public String getmClassName() {
        return mClassName;
    }

    public void setmClassName(String mClassName) {
        this.mClassName = mClassName;
    }
}
