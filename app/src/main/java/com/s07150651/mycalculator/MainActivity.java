package com.s07150651.mycalculator;

import android.content.DialogInterface;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button calcultorButton;
    private EditText weightEdittext;
    private RadioButton manRadioButton;
    private RadioButton womanRadioButton;
    private TextView resultTextView;
    private EditText WeightEdittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calcultorButton = (Button) findViewById(R.id.calculator);
        weightEdittext  = (EditText) findViewById(R.id.weight);
        manRadioButton = (RadioButton) findViewById(R.id.man);
        womanRadioButton  = (RadioButton) findViewById(R.id.woman);
        resultTextView = (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }


    private void registerEvent(){
        calcultorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!weightEdittext.getText().toString().trim().equals("")){
                    if (manRadioButton.isChecked()||womanRadioButton.isChecked()){
                        Double weight = Double.parseDouble(weightEdittext.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("------评估结果------\n");
                        if (manRadioButton.isChecked()){
                            sb.append("男性标准身高：");
                            double result = evaluateHeight(weight,"男");
                            sb.append((int)result+"厘米");
                        }
                        if (womanRadioButton.isChecked()){
                            sb.append("女性标准身高：");
                            double result = evaluateHeight(weight,"女");
                            sb.append((int)result+"厘米");
                        }
                        resultTextView.setText(sb.toString());
                    }else{
                        showMessage("请选择性别！");
                    }

                }else {
                    showMessage("请输入体重：");
                }
            }
        });
    }

    private double evaluateHeight(double weight,String sex){
        double height;
        if (sex.equals("男")){
            height = 170-(62-weight)/0.6;

        }else {
            height = 158-(52-weight)/0.5;
        }
        return height;
    }


    private void showMessage(String message){
        AlertDialog alertDialog =  new AlertDialog.Builder(this).create();
        alertDialog.setTitle("系统信息");
        alertDialog.setMessage(message);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"确定",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,1,Menu.NONE,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
