package com.example.onthicuoikysqlitecontenprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class InsertQuery extends AppCompatActivity {

    EditText edtIdIQ, edtNameIQ, edtPhoneNumberIQ;
    RadioButton radMaleIQ, radFemaleIQ;

    Button btnAddIQ, btnSelectIQ;

    ListView lv_displayIQ;
    AdapterCT adapter;

    DBHelper dbHelper;

    ArrayList<Employee> list = new ArrayList<>();
    Employee e;

    static final String uri = "content://com.example.onthicuoikysqlitecontenprovider";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_query);

        edtIdIQ = findViewById(R.id.edtIdIQ);
        edtNameIQ = findViewById(R.id.edtNameIQ);
        edtPhoneNumberIQ = findViewById(R.id.edtPhoneNumberIQ);

        radMaleIQ = findViewById(R.id.radMaleIQ);
        radFemaleIQ = findViewById(R.id.radFemaleIQ);

        btnAddIQ = findViewById(R.id.btnAddIQ);
        btnSelectIQ = findViewById(R.id.btnSelectIQ);

        lv_displayIQ = findViewById(R.id.lv_displayIQ);
        dbHelper = new DBHelper(this);

        btnAddIQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (check()) {
//                    Employee e = new Employee();
//                    e.setId(Integer.parseInt(edtIdIQ.getText().toString()));
//                    e.setName(edtNameIQ.getText().toString());
//                    if (radMaleIQ.isChecked())
//                        e.setGioiTinh("Nam");
//                    if (radFemaleIQ.isChecked()) {
//                        e.setGioiTinh("Nữ");
//                    }
//                    e.setPhoneNumber(Integer.parseInt(edtPhoneNumberIQ.getText().toString()));
//                    dbHelper.insertOneEmployee(e);
//                    Toast.makeText(InsertQuery.this, "Thêm thành công", Toast.LENGTH_SHORT).show();

                    ContentValues values = new ContentValues();
                    values.put("id_employee",edtIdIQ.getText().toString());
                    values.put("name_employee",edtNameIQ.getText().toString());

                    if (radMaleIQ.isChecked())
                        values.put("gioiTinh_employee","Nam");
                    if (radFemaleIQ.isChecked()) {
                        values.put("gioiTinh_employee","Nữ");
                    }

                    values.put("phone_number_employee",edtPhoneNumberIQ.getText().toString());

                    Uri product = Uri.parse(uri);

                    Uri insert_uri = getContentResolver().insert(product,values);

                    Toast.makeText(InsertQuery.this, "Luu Thanh Cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSelectIQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
//                list = dbHelper.getAllEmployee();

                Uri product = Uri.parse(uri);

                Cursor cursor = getContentResolver().query(product,null,null,null,"name_employee");
                if(cursor != null){
                    cursor.moveToFirst();
                    do{
                        list.add(new Employee(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3)));
                    }while (cursor.moveToNext());
                    adapter = new AdapterCT(InsertQuery.this, R.layout.row, list);
                    lv_displayIQ.setAdapter(adapter);
                }else {
                    Toast.makeText(InsertQuery.this, "Khong co ket qua", Toast.LENGTH_SHORT).show();
                }


//                adapter = new AdapterCT(InsertQuery.this, R.layout.row, list);
//                lv_displayIQ.setAdapter(adapter);


            }
        });

    }
    public void showkq() {
        adapter = new AdapterCT(this, R.layout.row, list);
        lv_displayIQ.setAdapter(adapter);
    }
    public boolean check() {
        if (edtIdIQ.getText().toString().length() == 0) {
            Toast.makeText(this, "Mã không để trống!!!", Toast.LENGTH_SHORT).show();
            edtIdIQ.requestFocus();
        }
        if (edtNameIQ.getText().toString().length() == 0) {
            Toast.makeText(this, "Tên không để trống!!!", Toast.LENGTH_SHORT).show();
            edtNameIQ.requestFocus();
        }
        if (edtPhoneNumberIQ.getText().toString().length() == 0) {
            Toast.makeText(this, "Địa chỉ không để trống!!!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}