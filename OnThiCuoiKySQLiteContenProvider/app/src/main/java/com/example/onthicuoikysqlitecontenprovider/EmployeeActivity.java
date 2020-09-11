package com.example.onthicuoikysqlitecontenprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class EmployeeActivity extends AppCompatActivity {
    EditText edtId, edtName, edtPhoneNumber;
    RadioButton radMale, radFemale;

    Button btnSave, btnDelete, btnAdd, btnExit, btnUpdate, btnShowDatabase;

    ListView listDisplay;
    AdapterCT adapter;

    DBHelper dbHelper;

    ArrayList<Employee> list = new ArrayList<>();
    Employee e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);

        radMale = findViewById(R.id.radMale);
        radFemale = findViewById(R.id.radFemale);

        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        btnAdd = findViewById(R.id.btnAdd);
        btnExit = findViewById(R.id.btnExit);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnShowDatabase = findViewById(R.id.btnShowDatabase);

        listDisplay = findViewById(R.id.lv_display);
        dbHelper = new DBHelper(this);


        listDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                e = list.get(i);

                edtId.setText(e.getId() + "");
                edtName.setText(e.getName());
                edtPhoneNumber.setText(e.getPhoneNumber() + "");
                if (e.getGioiTinh().equals("Nam")) {
                    radMale.setChecked(true);
                } else {
                    radFemale.setChecked(true);
                }
//                Toast.makeText(EmployeeActivity.this, e+"", Toast.LENGTH_SHORT).show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(new Employee(1990, "hau", "Nam", 14124));
                list.add(new Employee(1991, "hau", "Nam", 14124));
                list.add(new Employee(1992, "hau", "Nam", 14124));
                list.add(new Employee(1993, "hau", "Nam", 14124));
                list.add(new Employee(1994, "hau", "Nam", 14124));

                if (check()) {
                    Employee e = new Employee();
                    e.setId(Integer.parseInt(edtId.getText().toString()));
                    e.setName(edtName.getText().toString());
                    if (radMale.isChecked())
                        e.setGioiTinh("Nam");
                    if (radFemale.isChecked()) {
                        e.setGioiTinh("Nữ");
                    }
                    e.setPhoneNumber(Integer.parseInt(edtPhoneNumber.getText().toString()));
                    if (!list.contains(e)) {
                        list.add(e);
                        showkq();
                        Toast.makeText(EmployeeActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(EmployeeActivity.this, "Trùng mã", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (e != null) {
                    for (int i = 0; i < list.size(); i++) {
                        if (e.getId() == list.get(i).getId())
                            list.remove(e);
                        showkq();
                    }
                    e = null;
                } else {
                    Toast.makeText(EmployeeActivity.this, "Chon employee", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.insertEmployee(list);
                Toast.makeText(EmployeeActivity.this, "Luu Xuong Database Thanh Cong", Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(edtId.getText().toString());
                String gioitinh = "";
                if (radMale.isChecked())
                    gioitinh = "Nam";
                if (radFemale.isChecked()) {
                    gioitinh = "Nữ";
                }

                if (dbHelper.updateEP(id, edtName.getText().toString(), gioitinh, Integer.parseInt(edtPhoneNumber.getText().toString()))) {
                    Toast.makeText(EmployeeActivity.this, "Update thanh cong", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list = dbHelper.getAllEmployee();
                    adapter = new AdapterCT(EmployeeActivity.this, R.layout.row, list);
                    listDisplay.setAdapter(adapter);
                } else {
                    Toast.makeText(EmployeeActivity.this, "Update chua thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShowDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                list = dbHelper.getAllEmployee();
                adapter = new AdapterCT(EmployeeActivity.this, R.layout.row, list);
                listDisplay.setAdapter(adapter);
            }
        });

    }

    public void showkq() {
        adapter = new AdapterCT(this, R.layout.row, list);
        listDisplay.setAdapter(adapter);
    }

    public boolean check() {
        if (edtId.getText().toString().length() == 0) {
            Toast.makeText(this, "Mã không để trống!!!", Toast.LENGTH_SHORT).show();
            edtId.requestFocus();
        }
        if (edtName.getText().toString().length() == 0) {
            Toast.makeText(this, "Tên không để trống!!!", Toast.LENGTH_SHORT).show();
            edtName.requestFocus();
        }
        if (edtPhoneNumber.getText().toString().length() == 0) {
            Toast.makeText(this, "Địa chỉ không để trống!!!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

//    private void Delete() {
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int id =Integer.parseInt(edtMaSX.getText().toString());
//                if(databaseHelper.deleteNSX(id) ==  true)
//                    Toast.makeText(NhaSanXuat.this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(NhaSanXuat.this, "Xoa that bai", Toast.LENGTH_SHORT).show();
//                edtMaSX.setText("");
//            }
//        });
//    }
}