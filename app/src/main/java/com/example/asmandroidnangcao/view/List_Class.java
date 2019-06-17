package com.example.asmandroidnangcao.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asmandroidnangcao.R;
import com.example.asmandroidnangcao.adapter.CustomAdapter;
import com.example.asmandroidnangcao.data.ClassDAO;
import com.example.asmandroidnangcao.model.Class_model;

import java.util.List;


public class List_Class extends AppCompatActivity {
    private List<Class_model> arr;
    private CustomAdapter customAdapter;
    public ListView lv;
    private static final String selectQuery = "SELECT * FROM "+ Class_model.TABLE_CLASS_NAME;
    private Button btnClearForm;
    private Button btnSaveForm;
    private EditText edtClassName;
    private EditText edtClassKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_show_listclass);
        final ClassDAO classDAO = new ClassDAO(this);
        arr = classDAO.getAllClass(selectQuery);
        addWidget();
        setAdapter();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int key_id = arr.get(position).getmId();
                showAlertDialog(key_id,classDAO);
            }
        });

    }
    public void refresh(ClassDAO classDAO){
        arr.clear();
        arr.addAll(classDAO.getAllClass(selectQuery));
        setAdapter();
    }

    public void showAlertDialog(final int key_id, final ClassDAO classDAO){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Chọn Delete Hoặc Update: ");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                classDAO.deleteClass(key_id);
                refresh(classDAO);
                Toast.makeText(List_Class.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showDialogUpdate(key_id,classDAO);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showDialogUpdate(final int key_id, final ClassDAO classDAO){
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(true);
        dialog.setTitle("UPDATE");
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
                    Toast.makeText(List_Class.this, "Mã lớp không được để trống", Toast.LENGTH_SHORT).show();
                    dialog.setCancelable(true);
                }else if (TextUtils.isEmpty(NameClass)){
                    Toast.makeText(List_Class.this, "Tên lớp không được để trống ", Toast.LENGTH_SHORT).show();
                    dialog.setCancelable(true);
                }else {
                    Class_model classModel = new Class_model(key_id ,keyClass,NameClass);
                    classDAO.updateClass(classModel);
                    refresh(classDAO);
                    Toast.makeText(List_Class.this, "Update Thành Công", Toast.LENGTH_SHORT).show();
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

    private void addWidget(){
        lv = (ListView) findViewById(R.id.lv_listview);
    }

    public void setAdapter(){
        customAdapter = new CustomAdapter(this,R.layout.row_item_listview,arr);
        lv.setAdapter(customAdapter);
    }
}
