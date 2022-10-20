package com.example.ooooggg;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;
import java.util.Map;

public class log_in extends AppCompatActivity {
    Button b3;
    EditText et3,et4;
    Activity context=this;
    FirebaseAuth mAuth;
    TextView tv8;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth=FirebaseAuth.getInstance();
        b3=(Button)findViewById(R.id.button3);

        et3=(EditText)findViewById(R.id.editTextTextPersonName3);
        et4=(EditText)findViewById(R.id.editTextTextPersonName4);
        et4.setTransformationMethod(PasswordTransformationMethod.getInstance());
        tv8=(TextView)findViewById(R.id.textView8);

        FirebaseDatabase database= FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("data_text") ;

        final DatabaseReference myRef2=myRef.child("data01");



        b3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mAuth.signInWithEmailAndPassword(et3.getText().toString(),et4.getText().toString()).addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user=mAuth.getCurrentUser();
                            email=user.getEmail();
                           // tv8.setText("登入"+user.getEmail()+"成功!!!");
                            // email=user.getEmail();
                            firebase_select(myRef2);
                          /*  Intent intent = new Intent(log_in.this,Flowermap.class);

                            Bundle bundle = new Bundle();
                            bundle.putString("email",email);
                            intent.putExtras(bundle);
                            startActivity(intent);*/



                        }
                        else
                        {
                            tv8.setText("登入fail"+task.getException());
                        }
                    }
                });
            }
        });
    }
    String buffer;
    String  passnamenew="";


    private void firebase_select(DatabaseReference db) {

        final List<Map<String, Object>> items=new ArrayList<Map<String, Object>>();
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int x_sum=(int)snapshot.getChildrenCount();

                for (DataSnapshot ds: snapshot.getChildren()) {
                    message_string user_data = ds.getValue(message_string.class);
                    buffer=ds.child("name").getValue().toString();
                    String email_buffer=ds.child("email").getValue().toString();
                    if(email_buffer.equals(email))
                    {
                        passnamenew=buffer;
                        tv8.setText("登入"+ email_buffer+"成功!!!");
                        Intent intent = new Intent(log_in.this,Flowermap.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name",passnamenew);
                        bundle.putString("email",email_buffer);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                 /*   String email1 = email;
                    String email2 = user_data.getEmail();
                    email3 = email2;
                    pass = email2.equals(email1);
                    //  tv8.setText("登入"+ email+"成功!!!");
                    if (pass) {
                        // tv8.setText("登入"+ buffer+"成功!!!");
                        passname=user_data.getName();
                        break;
                    }
*/
                }

                //tv8.setText("登入"+ passname+"成功!!!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



       // return pass;
    }

}