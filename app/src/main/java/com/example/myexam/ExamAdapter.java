package com.example.myexam;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class ExamAdapter extends ArrayAdapter {
    Activity context;
    List<ExamModel> examList;
    public ExamAdapter(@NonNull Context context, @NonNull List <ExamModel> examList) {
        super(context,R.layout.mylist,examList);
        this.context=(Activity) context;
        this.examList=examList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
     View view = layoutInflater.inflate(R.layout.mylist,null);
        TextView examName = (TextView)view.findViewById(R.id.exam_name);
        TextView examDate= (TextView)view.findViewById(R.id.exam_date);
        TextView examTotalMarks=(TextView)view.findViewById(R.id.total_marks);
        TextView examMarksObt=(TextView)view.findViewById(R.id.marks_obtained);
        TextView examGrade=(TextView)view.findViewById(R.id.grades) ;
        ExamModel examModel=examList.get(position);
        examName.setText(examModel.getmExamName());
       examDate.setText("Exam Date:"+examModel.getmExamDate());
      examTotalMarks.setText(examModel.getmExamTotalMarks());
     examMarksObt.setText("/"+examModel.getmExamMarksObt());
        examGrade.setText("Grade: "+examModel.getmExamGrade());
        return view;
    }
}

