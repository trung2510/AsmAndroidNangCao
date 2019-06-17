package com.example.asmandroidnangcao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asmandroidnangcao.R;
import com.example.asmandroidnangcao.model.Student;

import java.util.List;


public class CustomAdapterForStudent extends ArrayAdapter<Student> {
    private Context context;
    private int resource;
    private List<Student> studentList;

    public CustomAdapterForStudent(@NonNull Context context, int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.studentList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.row_item_listview,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.mId = (TextView) convertView.findViewById(R.id.tv_listclass_id);
            viewHolder.mName = (TextView) convertView.findViewById(R.id.tv_listclass_keyclass);
            viewHolder.mBirthday = (TextView) convertView.findViewById(R.id.tv_listclass_classname);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Student student = studentList.get(position);
        viewHolder.mId.setText(student.getmID()+"");
        viewHolder.mName.setText(student.getmName());
        viewHolder.mBirthday.setText(student.getmBirthday());

        return convertView;
    }
    public class ViewHolder{
        private TextView mId;
        private TextView mName;
        private TextView mBirthday;
    }
}
