package com.example.hhhh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText mEtInput;
    private Button mBtnSubmit, mBtnXianshi;
    private FloatingActionButton mBtnDelete;
    private final String mFileName = "test.txt";
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEtInput = (EditText) findViewById(R.id.et_name);
        mBtnSubmit = (Button) findViewById(R.id.btn_submit);
        mBtnXianshi = (Button) findViewById(R.id.btn_xianshi);
        mBtnDelete = (FloatingActionButton) findViewById(R.id.btn_delete);
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = true;
                save(mEtInput.getText().toString());
                mEtInput.setText("");
            }
        });
        mBtnXianshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = true;
                Toast.makeText(MainActivity.this, "嘤嘤嘤~", Toast.LENGTH_SHORT).show();
                mEtInput.setText(read());
            }
        });
        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = true;
                Toast.makeText(MainActivity.this, "滴 ~ 一键删除！", Toast.LENGTH_SHORT).show();
                mEtInput.setText("");
                save("");
            }
        });
    }

    //存储数据
    private void save(String content) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(mFileName, MODE_PRIVATE);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //读取数据
    private String read() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput(mFileName);
            byte[] buff = new byte[1024];
            StringBuilder sb = new StringBuilder("");
            int len = 0;
            while ((len = fileInputStream.read(buff)) > 0) {
                sb.append(new String(buff, 0 , len));
            }
            fileInputStream.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (flag == true) {
            if (keyCode == KeyEvent.KEYCODE_HOME) {
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_BACK) {
                save(mEtInput.getText().toString());
                Toast.makeText(this, "再点击一次返回即可退出，文字内容将自动保存！", Toast.LENGTH_SHORT).show();
                flag = false;
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}