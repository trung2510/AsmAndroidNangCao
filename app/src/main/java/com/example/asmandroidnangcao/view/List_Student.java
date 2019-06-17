package com.example.asmandroidnangcao.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asmandroidnangcao.R;
import com.example.asmandroidnangcao.adapter.CustomAdapter;
import com.example.asmandroidnangcao.adapter.CustomAdapterForStudent;
import com.example.asmandroidnangcao.data.ClassDAO;
import com.example.asmandroidnangcao.data.StudentDAO;
import com.example.asmandroidnangcao.model.Class_model;
import com.example.asmandroidnangcao.model.Student;

import java.util.List;

public class List_Student extends AppCompatActivity {
    private List<Student> brr;
    private List<Student> listStudentFiltered;
    private CustomAdapter customAdapter;
    public ListView lv;
    private static final String selectQuery = "SELECT * FROM " + Student.TABLE_STUDENT_NAME;
    private static final String TAG = "List_Student";
    private EditText edtName;
    private EditText edtBirthday;
    private Button btnSave;
    private StudentDAO studentDAO;
    private CustomAdapterForStudent customAdapterForStudent;
    private Spinner spnClass,spnClassUpdate;
    private List<String> listNameClass;
    private List_Class listClass;
    private ClassDAO classDAO;
    private Button btnClearForm;
    private Button btnSaveForm;
    private EditText edtClassName;
    private EditText edtClassKey;
    private static final String selectQueryClass = "SELECT "+ Class_model.CLASS_NAME +" FROM " + Class_model.TABLE_CLASS_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_student_listview);
        addWidget();
        spinerClass();
        setAdapter();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = createStudent();
                if (student == null){

                }else {
                    studentDAO.addStudent(student);
                    refresh(studentDAO);
                    clearForm();
                    Toast.makeText(List_Student.this, "Thêm Sinh Viên Thành Công", Toast.LENGTH_SHORT).show();
                }

            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int key_id = brr.get(position).getmID();
                int key_Class = brr.get(position).getmIdClass();
                showAlertDialog(studentDAO,key_id,key_Class,listNameClass);
            }
        });
    }
    public void clearForm(){
        edtName.setText("");
        edtBirthday.setText("");
    }

    public void spinerClass(){
        listNameClass = classDAO.getNameClass(selectQueryClass);
        listNameClass.add("Show_All");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, listNameClass);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnClass.setAdapter(adapter);
        spnClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int classId = classDAO.getIdClass(spnClass.getSelectedItem().toString());
                setAdapter();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void showAlertDialog(final StudentDAO studentDAO, final int key_id, final int key_Class, final List<String> list){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Chọn Delete Hoặc Update: ");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                studentDAO.delteStudent(key_id);
                refresh(studentDAO);
                Toast.makeText(List_Student.this, "Xóa OK", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showDialogUpdate(key_id,studentDAO,key_Class,list);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showDialogUpdate(final int key_id, final StudentDAO studentDAO, final int key_Class, List<String> list){
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(true);
        dialog.setTitle("UPDATE STUDENT");
        dialog.setContentView(R.layout.dialog_update_student);
        dialog.show();
        btnClearForm = (Button) dialog.findViewById(R.id.btn_dialog_clear1);
        btnSaveForm = (Button) dialog.findViewById(R.id.btn_dialog_save1);
        edtClassKey = (EditText) dialog.findViewById(R.id.edt_key_class1);
        edtClassName = (EditText) dialog.findViewById(R.id.edt_name_class1);
        spnClassUpdate = (Spinner) dialog.findViewById(R.id.spn_class1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(List_Student.this,android.R.layout.simple_spinner_item, listNameClass);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnClassUpdate.setAdapter(adapter);
        final int[] classId = {0};
        spnClassUpdate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classId[0] = classDAO.getIdClass(spnClassUpdate.getSelectedItem().toString());
                //Toast.makeText(List_Student.this, classId[0] +"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSaveForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyClass = edtClassKey.getText().toString();
                String NameClass = edtClassName.getText().toString();
                if (TextUtils.isEmpty(keyClass)){
                    Toast.makeText(List_Student.this, "Mã Lớp Không Được Trống", Toast.LENGTH_SHORT).show();
                    dialog.setCancelable(true);
                }else if (TextUtils.isEmpty(NameClass)){
                    Toast.makeText(List_Student.this, "Tên Lớp Không Được Trống ", Toast.LENGTH_SHORT).show();
                    dialog.setCancelable(true);
                }else {
                    Student student = new Student(key_id,keyClass,NameClass, classId[0]);
                    studentDAO.updateClass(student);
                    refresh(studentDAO);
                    Toast.makeText(List_Student.this, "Update Thành Công", Toast.LENGTH_SHORT).show();
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

    public Student createStudent(){
        String name = edtName.getText().toString();
        String birthday = edtBirthday.getText().toString();
        int classId = classDAO.getIdClass(spnClass.getSelectedItem().toString());
        if (TextUtils.isEmpty(name)){
            Toast.makeText(List_Student.this, "Tên Sinh Viên Không Được Để Trống", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(birthday)){
            Toast.makeText(List_Student.this, "Ngày Sinh Không Được Để Trống", Toast.LENGTH_SHORT).show();
        }else {
            Student student = new Student(name,birthday,classId);
            return student;
        }
        return null;
    }

    public void refresh(StudentDAO studentDAO){
        brr.clear();
        brr.addAll(studentDAO.getAllStudent(selectQuery));
        setAdapter();
    }

    public void showAlertDialog(final int key_id, final ClassDAO classDAO){
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setCancelable(true);

        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void addWidget(){
        spnClass = (Spinner) findViewById(R.id.spn_class);
        edtName = (EditText) findViewById(R.id.edt_student_tensv);
        edtBirthday = (EditText) findViewById(R.id.edt_student_ngaysinh);
        btnSave = (Button) findViewById(R.id.btn_student_themsv);
        lv = (ListView) findViewById(R.id.lv_student_listview);
        studentDAO = new StudentDAO(this);
        classDAO = new ClassDAO(this);
        listClass = new List_Class();
        brr = studentDAO.getAllStudent(selectQuery);
    }


    public void setAdapter(){
        int classId = classDAO.getIdClass(spnClass.getSelectedItem().toString());
        if (classId == -1) {
            customAdapterForStudent = new CustomAdapterForStudent(this, R.layout.row_item_listview, brr);
            lv.setAdapter(customAdapterForStudent);
        }else {
            listStudentFiltered = studentDAO.getStudentFiltered(classId);
            customAdapterForStudent = new CustomAdapterForStudent(this, R.layout.row_item_listview, listStudentFiltered);
            lv.setAdapter(customAdapterForStudent);
        }
    }
}