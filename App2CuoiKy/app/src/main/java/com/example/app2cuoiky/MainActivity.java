package com.example.app2cuoiky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtId, edtName, edtPhoneNumber;
    RadioButton radMale, radFemale;

    Button btnDelete, btnAdd, btnUpdate, btnShowDatabase;

    GridView gv_display;


    static final String AUTHORITY = "com.example.onthicuoikysqlitecontenprovider";
    static final String CONTENT_PROVIDER = "contentprovider";
    static final String URL = "content://" + AUTHORITY + "/" + CONTENT_PROVIDER;

    static final Uri CONTENT_URI = Uri.parse(URL);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);

        radMale = findViewById(R.id.radMale);
        radFemale = findViewById(R.id.radFemale);

        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnShowDatabase = findViewById(R.id.btnShowDatabase);

        gv_display = findViewById(R.id.gv_display);

        gv_display.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = (Math.round(i/4) * 4);
                edtId.setText(adapterView.getItemAtPosition(id).toString());
                edtName.setText(adapterView.getItemAtPosition(id+1).toString());

                if (adapterView.getItemAtPosition(id+2).toString().equals("Nam") )
                    radMale.setChecked(true);
                if (adapterView.getItemAtPosition(id+2).toString().equals("Nữ")) {
                    radFemale.setChecked(true);
                }
                edtPhoneNumber.setText(adapterView.getItemAtPosition(id+3).toString());
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues values = new ContentValues();
                values.put("id_employee",edtId.getText().toString());
                values.put("name_employee",edtName.getText().toString());

                if (radMale.isChecked())
                    values.put("gioiTinh_employee","Nam");
                if (radFemale.isChecked()) {
                    values.put("gioiTinh_employee","Nữ");
                }

                values.put("phone_number_employee",edtPhoneNumber.getText().toString());
                Uri insert_uri = getContentResolver().insert(CONTENT_URI,values);
                Toast.makeText(MainActivity.this, "Luu Thanh Cong", Toast.LENGTH_SHORT).show();
            }
        });

        btnShowDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list_string = new ArrayList<>();

                Cursor cursor = getContentResolver().query(CONTENT_URI,null,null,null,"name_employee");
                if(cursor != null){
                    cursor.moveToFirst();
                    do{
                        list_string.add(cursor.getInt(0) + "");
                        list_string.add(cursor.getString(1));
                        list_string.add(cursor.getString(2));
                        list_string.add(cursor.getInt(3)+"");
                    }while (cursor.moveToNext());
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list_string);
                    gv_display.setAdapter(adapter);
                }else {
                    Toast.makeText(MainActivity.this, "Khong co ket qua", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id_employee = Integer.parseInt(edtId.getText().toString());
                int count = getContentResolver().delete(CONTENT_URI, "id_employee = ? ", new String[]{ id_employee +""});
                if(count > 0) {
                    Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(MainActivity.this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    ContentValues values = new ContentValues();
                    values.put("id_employee",edtId.getText().toString());
                    values.put("name_employee",edtName.getText().toString());

                    if (radMale.isChecked())
                        values.put("gioiTinh_employee","Nam");
                    if (radFemale.isChecked()) {
                        values.put("gioiTinh_employee","Nữ");
                    }

                    values.put("phone_number_employee",edtPhoneNumber.getText().toString());

                    int count = getContentResolver().update(CONTENT_URI, values, "id_employee = ? " , new String[]{edtId.getText().toString()});
                    if(count > 0) {
                        Toast.makeText(MainActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}