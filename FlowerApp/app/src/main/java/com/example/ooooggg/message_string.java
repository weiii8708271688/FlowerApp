package com.example.ooooggg;


public class message_string {

    private String message;
    private String time;
    private String email;
    private String name;
    private int like;

  /*  protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_string);
       // b1 = (Button) findViewById(R.id.button10);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv=(ImageView) findViewById(R.id.imageView9);

                String uri = "@drawable/heart.jpg"; //圖片路徑和名稱

                int imageResource = getResources().getIdentifier(uri, null, getPackageName()); //取得圖片Resource位子
                iv.setImageResource(imageResource);


            }
        });




    }*/

    public  message_string() {}
    public  message_string (String text_1) {
        this.message=text_1;

    }
    public  message_string (String text_1,String text_2,String text_3) {
        this.message=text_1;
        this.time=text_2;
        this.email=text_3;

    }
    public String getmessage() {
        return message;
    }
    public String gettime() {
        return time;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }






}
