package com.example.in100gram.Activities;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.in100gram.R;

public class RegActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        Button RegBtn = findViewById(R.id.RegBtn);
        CheckBox showCheck = findViewById(R.id.showCheck);
        EditText PassEdit = findViewById(R.id.PasswordEdit);

        showCheck.setOnCheckedChangeListener((compoundButton, b) -> {
            Log.d("ShowInputType", String.valueOf(PassEdit.getInputType()));
            if(showCheck.isChecked()){
                PassEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
            else {
                PassEdit.setInputType(129);
            }
        });

        RegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show();
            }
        });

        RegBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "Аккаунт с таким логином уже существует", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
