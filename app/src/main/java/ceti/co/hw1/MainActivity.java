package ceti.co.hw1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText strName, strAge;
    TextView strGender, strHobby, strBirth, strPrint, strGenderLbl, strHobbyLbl, strBirthLbl;
    Button btPrint;
    String s1, s2, s3, s4, s5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        strName = (EditText) findViewById(R.id.etName);
        strAge = (EditText) findViewById(R.id.etAge);
        strGender = (TextView) findViewById(R.id.tvGender);
        strGenderLbl = (TextView) findViewById(R.id.tvGenderLbl);
        strHobby = (TextView) findViewById(R.id.tvHobby);
        strHobbyLbl = (TextView) findViewById(R.id.tvHobbyLbl);
        strBirth = (TextView) findViewById(R.id.tvBirth);
        strBirthLbl = (TextView) findViewById(R.id.tvBirthLbl);
        btPrint = (Button) findViewById(R.id.btPrint);
        strPrint = (TextView) findViewById(R.id.tvPrint);

        strGenderLbl.setOnClickListener(this);
        strHobbyLbl.setOnClickListener(this);
        strBirthLbl.setOnClickListener(this);
        btPrint.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btPrint) {
            String temp="";

            s1 = "이름 : " + strName.getText() + "\n";
            s2 = "나이 : " + strAge.getText() + "\n";

            temp += s1;
            temp += s2;
            temp += "성별 : " + s3 + "\n";
            temp += "취미 : " + s4 + "\n";
            temp += "생일 : " + s5 + "\n";

            strPrint.setText(temp);
        } else if (view == strGenderLbl) {

            final String[] versionArray = new String[]{"남성", "여성"};
            AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
            dlg.setTitle("성별을 선택하시오");
            dlg.setIcon(R.mipmap.ic_launcher);
            dlg.setSingleChoiceItems(versionArray, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    s3 = versionArray[i];
                }
            });
            dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    strGender.setText(s3);
                }
            });
            dlg.show();
        } else if (view == strHobbyLbl) {
            final ArrayList<String> selectedItem = new ArrayList<String>();
            final String[] versionArray = new String[]{"잠자기", "음악감상", "독서"};
            final boolean[] checkArray = new boolean[]{false, false, false};
            AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
            dlg.setTitle("취미는 무엇인가요?");
            dlg.setIcon(R.mipmap.ic_launcher);
            dlg.setMultiChoiceItems(versionArray, checkArray, new DialogInterface.OnMultiChoiceClickListener() {
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    if(isChecked)
                        selectedItem.add(versionArray[which]);
                    else
                        selectedItem.remove(versionArray[which]);
                }
            });
            dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    s4 = selectedItem.toString();
                    strHobby.setText(s4);
                }
            });
            dlg.show();
        } else if (view == strBirthLbl) {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dateDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    s5 = i + "년 " + (i1+1) + "월 " + i2 + "일";
                    strBirth.setText(s5);
                }
            }, year, month, day);
            dateDialog.show();
        }
    }
}
