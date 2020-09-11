package com.example.onthicuoikysqlitecontenprovider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnAdd:
                Intent intentEmployee = new Intent(this, EmployeeActivity.class);
                startActivity(intentEmployee);
                break;
            case R.id.mnSearch:
                Intent intentSearch = new Intent(this, EmployeeDisplayActivity.class);
                startActivity(intentSearch);
                break;
            case R.id.mnInsertQuery:
                Intent InsertQuery = new Intent(this, InsertQuery.class);
                startActivity(InsertQuery);
                break;
            default:
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}