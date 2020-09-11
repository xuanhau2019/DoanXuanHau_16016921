package com.example.onthicuoikysqlitecontenprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "CuoiKy", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE EMPLOYEES (id_employee integer primary key, name_employee text, gioiTinh_employee text, phone_number_employee integer)");
//        sqLiteDatabase.execSQL("CREATE TABLE SanPham(MaSo integer primary key, Ten text, DVT text, DonGia text, MaSX integer, FOREIGN KEY(MaSX) REFERENCES NhaSanXuat(MaSX))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS EMPLOYEES");
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SanPham");
        onCreate(sqLiteDatabase);
    }

    public int insertEmployee(ArrayList<Employee> list) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        for (Employee e : list) {
            con.put("id_employee", e.getId());
            con.put("name_employee", e.getName());
            con.put("gioiTinh_employee", e.getGioiTinh());
            con.put("phone_number_employee", e.getPhoneNumber());
            db.insert("EMPLOYEES", null, con);
        }
        db.close();
        return 1;
    }

    public int insertOneEmployee(Employee e) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put("id_employee", e.getId());
        con.put("name_employee", e.getName());
        con.put("gioiTinh_employee", e.getGioiTinh());
        con.put("phone_number_employee", e.getPhoneNumber());
        db.insert("EMPLOYEES", null, con);
        db.close();
        return 1;
    }

    public ArrayList<Employee> getAllEmployee() {
        ArrayList<Employee> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM EMPLOYEES", null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (cursor.isAfterLast() == false) {
            list.add(new Employee(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    public Employee getAllEmployeeByID(int id) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM EMPLOYEES WHERE id_employee= " + id + " ", null);
        if (cursor != null)
            cursor.moveToFirst();

        Employee nsx = new Employee(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));

        cursor.close();
        database.close();
        return nsx;
    }

//    public boolean deleteNSX(int id){
//        SQLiteDatabase database = getWritableDatabase();
//        if(database.delete("NhaSanXuat","MaSX" + "=?",
//                new String[] {String.valueOf(id)}) > 0){
//            database.close();
//        };
//        return true;
//    }

    public boolean updateEP(int id_employee, String name_employee, String gioiTinh_employee, int phone_number_employee) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id_employee", id_employee);
        contentValues.put("name_employee", name_employee);
        contentValues.put("gioiTinh_employee", gioiTinh_employee);
        contentValues.put("phone_number_employee", phone_number_employee);

        database.update("EMPLOYEES", contentValues, "id_employee =" + id_employee, null);
        return true;
    }

}
