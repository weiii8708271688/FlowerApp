package com.example.ooooggg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class register extends AppCompatActivity {
    Activity context=this;
    Button b3;
    EditText et1_email,et2_pw,et3_name;
    ListView LV;
    String x_select;
    FirebaseAuth mAuth;
    int x_last=0;
    TextView tv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        b3=(Button)findViewById(R.id.button3);

        et1_email=(EditText)findViewById(R.id.editTextTextPersonName);
        et2_pw=(EditText)findViewById(R.id.editTextTextPersonName2);
        et2_pw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        et3_name=(EditText)findViewById(R.id.editTextTextPersonName5);
        tv4=(TextView)findViewById(R.id.textView9);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("data_text") ;

        final DatabaseReference myRef2=myRef.child("data01");
        firebase_select(myRef2);
        b3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


                mAuth.createUserWithEmailAndPassword(et1_email.getText().toString(),et2_pw.getText().toString()).addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FirebaseUser user=mAuth.getCurrentUser();
                                    tv4.setText(user.getEmail()+"註冊成功!!!");
                                    x_last+=1;
                                    myRef2.child(String.valueOf(x_last)).setValue(new teststring(et3_name.getText().toString(),et1_email.getText().toString(),et2_pw.getText().toString()));
                                    firebase_select(myRef2);

                                        Intent intent = new Intent(register.this,Main.class);
                                        startActivity(intent);

                                }
                                else
                                {
                                    tv4.setText("fail"+task.getException());

                                }

                            }

                        }
                );
            }
        });
       /* LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView t1_s=(TextView)view.findViewById(R.id.text1);
                x_select=t1_s.getText().toString();
                TextView t2_s=(TextView)view.findViewById(R.id.text2);
                t1.setText(t2_s.getText().toString());
                TextView t3_s= (TextView)view.findViewById(R.id. text3);
                t2. setText(t3_s.getText().toString());
            }
        });*/

    }

    private void firebase_select(DatabaseReference db) {

        final List<Map<String, Object>> items=new ArrayList<Map<String, Object>>();
        db.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange (@NonNull DataSnapshot dataSnapshot) {
                int x_sum=(int)dataSnapshot.getChildrenCount();

                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    teststring user_data=ds.getValue (teststring.class);
                    Map<String, Object> item=new HashMap<String, Object>();
                    item.put("id",ds.getKey());
                    item.put("name",user_data.getname());
                    item.put("email",user_data.getemail());
                    item.put("password",user_data.getpassword());
                    items.add(item);
                    x_last=Integer.parseInt((ds.getKey()));

                }

                // SimpleAdapter SA=new SimpleAdapter(context,items,R.layout.teststring,new String[]{"name","email","password"},new int[]{R.id.text1,R.id.text2,R.id.text3});
                // LV.setAdapter(SA);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

         /*   @Override
            public void onCancelled (@NonNull DataSnapshot dataSnapshot) {

            }*/

        });


    }
}