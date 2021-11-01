package com.example.slide6_class;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class CheckActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtKinhDo;
    private EditText edtViDo;
    private Button btnSua;
    private Button btnXoa;
    private mapModel mapModel;
    private DataBase dataBase;
    private List<mapModel> mapModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        edtName = (EditText) findViewById(R.id.edtName);
        edtKinhDo = (EditText) findViewById(R.id.edtKinhDo);
        edtViDo = (EditText) findViewById(R.id.edtViDo);
        btnSua = (Button) findViewById(R.id.btnSua);
        btnXoa = (Button) findViewById(R.id.btnXoa);

        Intent intent = getIntent();
        String KinhDo = intent.getStringExtra("Lat");
        edtKinhDo.setText(KinhDo);
        String ViDo = intent.getStringExtra("Lng");
        edtViDo.setText(ViDo);

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBase = new DataBase(getApplicationContext());
                mapDAO mapDAO = new mapDAO(getApplicationContext());
                String Name = edtName.getText().toString();
                String ViDo = edtViDo.getText().toString();
                String KinhDo = edtKinhDo.getText().toString();
                long check = mapDAO.updateMaps(mapModel);
                if(check > 0){
                    Toast.makeText(getApplicationContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                    mapModelList.clear();
                    mapModelList.addAll(mapDAO.getAllsach());
                }else{
                    Toast.makeText(getApplicationContext(), "Sửa Thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapDAO mapDAO = new mapDAO(view.getContext());
                int check = mapDAO.deleteMaps(String.valueOf(mapModel.getID()));
                if(check > 0){
                    mapModelList.remove(mapDAO);
                    Toast.makeText(view.getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(view.getContext(), "Lỗi xóa", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}