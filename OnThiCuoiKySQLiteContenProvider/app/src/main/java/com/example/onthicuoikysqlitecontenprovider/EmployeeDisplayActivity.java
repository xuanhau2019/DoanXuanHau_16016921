package com.example.onthicuoikysqlitecontenprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class EmployeeDisplayActivity extends AppCompatActivity {

    EditText edtId;
    Button btnView, btnExit;

    GridView gvDisplay;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_display);

        edtId =  findViewById(R.id.edtId);

        gvDisplay = findViewById(R.id.gv_display);
        dbHelper = new DBHelper(this);

        btnExit = findViewById(R.id.btnExit);
        btnView = findViewById(R.id.btnView);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Employee> nsxArrayList = new ArrayList<>();
                ArrayList<String> stringArrayList = new ArrayList<>();

                if(edtId.getText().toString().equals(""))
                    nsxArrayList = dbHelper.getAllEmployee();
                else
                    nsxArrayList.add(dbHelper.getAllEmployeeByID(Integer.parseInt(edtId.getText().toString())));

                if(nsxArrayList.size()>0){
                    for(Employee employee: nsxArrayList){
                        stringArrayList.add(employee.getId() + "");
                        stringArrayList.add(employee.getName() + "");
                        stringArrayList.add(employee.getGioiTinh() + "");
                        stringArrayList.add(employee.getPhoneNumber() + "");
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(EmployeeDisplayActivity.this,android.R.layout.simple_list_item_1,stringArrayList);
                    gvDisplay.setAdapter(adapter);
                }else{
                    Toast.makeText(EmployeeDisplayActivity.this, "CSDL NULL", Toast.LENGTH_SHORT).show();
                }
            }
        });

        gvDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = (Math.round(i/4) * 4);
                edtId.setText(adapterView.getItemAtPosition(id).toString());
//                edtName.setText(adapterView.getItemAtPosition(id+1).toString());
//
//                if (adapterView.getItemAtPosition(id+2).toString().equals("Nam") )
//                    radMale.setChecked(true);
//                if (adapterView.getItemAtPosition(id+2).toString().equals("Nữ")) {
//                    radFemale.setChecked(true);
//                }
//                edtPhoneNumber.setText(adapterView.getItemAtPosition(id+3).toString());
            }
        });
    }
}