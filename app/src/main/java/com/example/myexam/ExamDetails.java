package com.example.myexam;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ExamDetails extends AppCompatActivity {
    LocalDatabase mDb;
    Session sess;
    List<ExamModel> examList = new ArrayList<>();
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_details);
        mDb = new LocalDatabase(ExamDetails.this);
        sess = new Session(ExamDetails.this);
        listview =(ListView) findViewById(R.id.examListView);
        retriveData();
    }
    public void retriveData() {
        Cursor examData = mDb.getexamName(sess.getUsername());
        while(examData.moveToNext()){
            ExamModel examModel=new ExamModel();
            examModel.setmExamName(examData.getString(0));
            examModel.setmExamDate(examData.getString(4));
            examModel.setmExamGrade(examData.getString(3));
            examModel.setmExamTotalMarks(examData.getString(1));
            examModel.setmExamMarksObt(examData.getString(2));
            examList.add(examModel);
        }
        ExamAdapter examAdapter = new ExamAdapter(ExamDetails.this,examList);
        listview.setAdapter(examAdapter);
    }
}
