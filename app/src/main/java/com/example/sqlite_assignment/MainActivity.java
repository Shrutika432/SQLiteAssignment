package com.example.sqlite_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHelper dbh;
    Button btnLogin, btnRegister;
    EditText sno, sname, sage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbh = new DBHelper(getApplicationContext());
        SQLiteDatabase db = dbh.getReadableDatabase();

        sno = findViewById(R.id.etSno);
        sname = findViewById(R.id.etSname);
        sage = findViewById(R.id.etSage);

        btnLogin = findViewById(R.id.BtnLog);
        btnRegister = findViewById(R.id.BtnR);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int stdno = Integer.parseInt(sno.getText().toString());
                String stdname = sname.getText().toString();
                int stdage = Integer.parseInt(sage.getText().toString());
                if (sno.getText().toString() == "" || stdname == "" || sage.getText().toString() == "") {
                    Toast.makeText(getApplicationContext(), "All fields are required.", Toast.LENGTH_SHORT).show();
                } else {
                    dbh = new DBHelper(getApplicationContext());
                    Cursor c = dbh.display(stdno);
                    if (c.getCount() == 0) {
                        Toast.makeText(getApplicationContext(), "No Record Found", Toast.LENGTH_SHORT).show();
                    } else {
                        StringBuffer sb = new StringBuffer();
                        c.moveToNext();
                        sb.append("Student No: " + c.getString(0) + "\n");
                        sb.append("Student Name: " + c.getString(1) + "\n");
                        sb.append("Student Age: " + c.getString(2) + "\n\n");
                        if (c.getInt(0) == stdno) {
                            startActivity(new Intent(getApplicationContext(), UpdateDelete.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid Inputs. Please Check you Input again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

    }
}