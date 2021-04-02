package com.example.sqlite_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDelete extends AppCompatActivity {
    DBHelper dbh;
    Button btnUpdate, btnDelete, btnlogout;
    EditText ud_sno, ud_sname, ud_sage;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        btnUpdate = findViewById(R.id.BtnUpdate);
        btnDelete = findViewById(R.id.BtnDelete);
        btnlogout = findViewById(R.id.BtnLogout);
        ud_sno = findViewById(R.id.et4);
        ud_sname = findViewById(R.id.et5);
        ud_sage = findViewById(R.id.et6);

        ud_sno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                btnUpdate.setText("Fetch Data");
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ud_stdno = Integer.parseInt(ud_sno.getText().toString());
                if (flag == 0) {
                    dbh = new DBHelper(getApplicationContext());
                    Cursor c = dbh.display(ud_stdno);

                    if (c.getCount() == 0) {
                        Toast.makeText(getApplicationContext(), "No Record Found", Toast.LENGTH_SHORT).show();
                        ud_sname.setText("");
                        ud_sage.setText("");
                    } else {
                        c.moveToNext();
                        ud_sname.setText(c.getString(1));
                        ud_sage.setText(c.getString(2));

                        flag = 1;
                        btnUpdate.setText("Update Now");
                    }
                } else {
                    String ud_stdname = ud_sname.getText().toString();
                    int ud_stdage = Integer.parseInt(ud_sage.getText().toString());

                    if (ud_sage.getText().toString() == "" || ud_stdname == "") {
                        Toast.makeText(getApplicationContext(), "All fields are required.", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean b = dbh.update(ud_stdno, ud_stdname, ud_stdage);
                        if (b == true) {
                            flag = 0;
                            btnUpdate.setText("Fetch Data");
                            startActivity(new Intent(getApplicationContext(), Viewpage.class));
                            ud_sno.setText("");
                            ud_sname.setText("");
                            ud_sage.setText("");
                        }
                    }
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ud_stdno = Integer.parseInt(ud_sno.getText().toString());
                if(ud_sno.getText().toString() == ""){
                    Toast.makeText(getApplicationContext(), "All fields are required.", Toast.LENGTH_SHORT).show();
                } else {
                    boolean b = dbh.delete(ud_stdno);
                    if (b == true) {
                        startActivity(new Intent(getApplicationContext(), Viewpage.class));
                        ud_sno.setText("");
                        ud_sname.setText("");
                        ud_sage.setText("");
                    } else{
                        Toast.makeText(getApplicationContext(), "Data not deleted", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(UpdateDelete.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}