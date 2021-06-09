package com.example.emotion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmotionActivity extends AppCompatActivity  {
    TextView userName,email;
    String name,emailUser;
    ImageView happyBtn,unHappyBtn,normalBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion);

        userName = findViewById(R.id.userName);
        email = findViewById(R.id.email);
        happyBtn = findViewById(R.id.happyBtn);
        unHappyBtn = findViewById(R.id.unHappyBtn);
        normalBtn = findViewById(R.id.normalBtn);
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

        FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(currentFirebaseUser.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        name = snapshot.child("name").getValue().toString();
                        emailUser = snapshot.child("email").getValue().toString();
                        userName.setText(name);
                        email.setText(emailUser);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        happyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Face face = new Face(emailUser,name,true,false,false,  LocalDateTime.now());
                saveEmotion(face);
            }
        });
        unHappyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Face face = new Face(emailUser,name,false,false,true, LocalDateTime.now());
                saveEmotion(face);
            }
        });
        normalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Face face = new Face(emailUser,name,false,false,true,  LocalDateTime.now());
                saveEmotion(face);
            }
        });
    }

    public void saveEmotion(Face face){
        FirebaseDatabase.getInstance().getReference()
                .child("Emotions")
                .push()
                .setValue(face)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(EmotionActivity.this, "Thành công" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(EmotionActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}