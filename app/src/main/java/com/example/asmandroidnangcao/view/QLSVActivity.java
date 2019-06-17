package com.example.asmandroidnangcao.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asmandroidnangcao.R;
import com.example.asmandroidnangcao.data.ClassDAO;
import com.example.asmandroidnangcao.data.DbHelper;
import com.example.asmandroidnangcao.model.Class_model;

public class QLSVActivity extends AppCompatActivity {
    private Button btnAddClass;
    private Button btnShowListClass;
    private Button btnQlsv;
    private Button btnClearForm;
    private Button btnSaveForm;
    private EditText edtClassName;
    private EditText edtClassKey;
    private Dialog dialog;
    private ClassDAO classDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsv);
        setTitle("Quản Lý Sinh Viên");
        addWidget();
        final DbHelper dbHelper = new DbHelper(this);
        classDAO = new ClassDAO(this);
        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(classDAO);
            }
        });
        btnShowListClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(QLSVActivity.this , List_Class.class);
                startActivity(in);
            }
        });
        btnQlsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QLSVActivity.this, List_Student.class);
                startActivity(intent);
            }
        });
    }

    public void addWidget(){
        btnAddClass = (Button) findViewById(R.id.btn_addClass);
        btnShowListClass = (Button) findViewById(R.id.btn_viewlist_class);
        btnQlsv = (Button) findViewById(R.id.btn_QLSV);
    }
     public void showDialog(final ClassDAO classDAO){
        dialog = new Dialog(QLSVActivity.this);
        dialog.setCancelable(true);
        dialog.setTitle("THÊM LỚP");
        dialog.setContentView(R.layout.dialog_add_class);
        dialog.show();
        btnClearForm = (Button) dialog.findViewById(R.id.btn_dialog_clear);
        btnSaveForm = (Button) dialog.findViewById(R.id.btn_dialog_save);
         edtClassKey = (EditText) dialog.findViewById(R.id.edt_key_class);
         edtClassName = (EditText) dialog.findViewById(R.id.edt_name_class);
         btnSaveForm.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String keyClass = edtClassKey.getText().toString();
                 String NameClass = edtClassName.getText().toString();
                 if (TextUtils.isEmpty(keyClass)){
                     Toast.makeText(QLSVActivity.this, "Mã Lớp không để trống", Toast.LENGTH_SHORT).show();
                     dialog.setCancelable(true);
                 }else if (TextUtils.isEmpty(NameClass)){
                     Toast.makeText(QLSVActivity.this, "Tên Lớp Không Được trống ", Toast.LENGTH_SHORT).show();
                     dialog.setCancelable(true);
                 }else {
                     Class_model classModel = new Class_model(keyClass,NameClass);
                     classDAO.addClass(classModel);
                     Toast.makeText(QLSVActivity.this, "Tạo lớp mới thành công", Toast.LENGTH_SHORT).show();
                     dialog.dismiss();
                 }
             }
         });
         btnClearForm.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                edtClassKey.setText("");
                edtClassName.setText("");
             }
         });

     }
     public Class_model createClass(){
        String keyClass = edtClassKey.getText().toString();
        String NameClass = edtClassName.getText().toString();
        Class_model classModel = new Class_model(keyClass,NameClass);
        return classModel;
     }
}
