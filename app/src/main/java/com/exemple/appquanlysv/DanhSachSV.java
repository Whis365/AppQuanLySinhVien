package com.exemple.appquanlysv;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DanhSachSV extends AppCompatActivity {

    SQLiteDatabase database;

    ListView listView;
    ArrayList<SinhVien> list;
    ListSVAdapter adapter;
    private static final String DATABASE_NAME = "QUANLYSINHVIEN.sqlite";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_sv);

        addControl();

    }

    private void addControl() {
    listView = findViewById(R.id.listView);
    list = new ArrayList<>();
    readData();
    adapter = new ListSVAdapter(this,R.layout.sv_items,list);
    listView.setAdapter(adapter);

    }
    public void readData()
    {
        // đọc dữ liệu trong database
        database = Database.initDatabase(this,"QUANLYSINHVIEN.sqlite");
        Cursor cursor = database.rawQuery("SELECT * FROM SINHVIEN",null);

        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String namsinh = cursor.getString(2);
            String lop = cursor.getString(3);
            String sothich = cursor.getString(5);
            int gioitinh = cursor.getInt(4);
            list.add(new SinhVien(ten,lop,namsinh,gioitinh,sothich));

            Log.e("ten",ten);
        }
        database.close();
        cursor.close();
//        adapter.notifyDataSetChanged();
    }
}
