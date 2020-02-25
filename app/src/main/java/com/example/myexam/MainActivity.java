package com.example.myexam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    LocalDatabase mdb;
    TextView displayName;
    String displayData,mExam,displayUsername,mTotalMarks,mMarksObtained,mGrade,mDate;
     Session session;
     Button logout,submit,detail;
     EditText inputExam,inputtotalmarks,inputmarksobtained,inputGrade,inputdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mdb = new LocalDatabase(MainActivity.this);
        logout = (Button) findViewById(R.id.logout);
        submit = (Button) findViewById(R.id.submit);
        detail = (Button) findViewById(R.id.detail);
        inputExam = (EditText)findViewById(R.id.examname);
        inputtotalmarks=(EditText)findViewById(R.id.examtotalmarks);
        inputmarksobtained=(EditText)findViewById(R.id.exammarksobtained);
        inputGrade =(EditText) findViewById(R.id.grade);
        inputdate=(EditText)findViewById(R.id.examdate);
        displayName=(TextView)findViewById(R.id.displayName);
        session = new Session(MainActivity.this);

        displayData = session.getName();
            displayUsername = session.getUsername();
        displayName.setText(displayData+"("+displayUsername+")");



       submit.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       mExam=inputExam.getText().toString();
                       mTotalMarks=inputtotalmarks.getText().toString();
                       mMarksObtained=inputmarksobtained.getText().toString();
                       mGrade=inputGrade.getText().toString();
                       mDate=inputdate.getText().toString();
                      boolean status;
                      status= mdb.insertexamData(displayUsername,mExam,mTotalMarks,mMarksObtained,mGrade,mDate);
                      if(status==true){
                          Toast.makeText(getApplicationContext(),"Submitted Successfully",Toast.LENGTH_SHORT).show();
                      }
                      else{
                          Toast.makeText(getApplicationContext(),"Submission Failed",Toast.LENGTH_SHORT).show();
                      }


                     }
               }
       );

          detail.setOnClickListener(
                  new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                       Intent in = new Intent(MainActivity.this,ExamDetails.class);
                       startActivity(in);

                      }
                  }
          );

           logout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        session.logout();
                        Intent inten = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(inten);
                    }

                }
        );


    }

}
