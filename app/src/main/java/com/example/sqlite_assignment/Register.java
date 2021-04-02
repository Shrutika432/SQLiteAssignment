package com.example.sqlite_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    DBHelper dbh;
    EditText reg_sno, reg_sname, reg_sage;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        dbh = new DBHelper(getApplicationContext());
        SQLiteDatabase db = dbh.getReadableDatabase();

        reg_sno = findViewById(R.id.et1);
        reg_sname = findViewById(R.id.et2);
        reg_sage = findViewById(R.id.et3);
        btnRegister = findViewById(R.id.BtnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sno = reg_sno.getText().toString();
                String sname = reg_sname.getText().toString();
                String sage = reg_sage.getText().toString();
                if (sno == "" || sname == "" || sage == "") {
                    Toast.makeText(getApplicationContext(), "All fields are required.", Toast.LENGTH_SHORT).show();
                } else {
                    String i = dbh.insert(sname, sage) ? "Inserted Successfully" : "Something went Wrong";
                    Toast.makeText(getApplicationContext(), i, Toast.LENGTH_LONG).show();
                    reg_sname.setText("");
                    reg_sage.setText("");
                    startActivity(new Intent(getApplicationContext(), Viewpage.class));
                }
            }
        });
    }
}