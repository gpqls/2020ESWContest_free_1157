package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class MainActivity extends AppCompatActivity {
    static Socket socket;
    static BufferedReader in;
    static BufferedReader in2;
    static PrintWriter out;
    static BufferedInputStream bis;
    static DataInputStream dis;
    static String msg;
    final String savepath = Environment.getExternalStorageDirectory().getAbsolutePath();
    //final File face = new File(savepath + "/face")
    private EditText edtTextAddress;
    private static Handler mHandler;



    Button forward;
    Button left;
    Button right;
    Button back;
    Button stop;
    Button showimg;
    Button connect;
    Button exit;
    TextView location;
    TextView signal;
    ImageView image;
    Button storeimg;
    Button manual;
    Button auto;
    Button takelocation;





    public void forward() {
        //Toast.makeText(getApplicationContext(), "Go Forward", Toast.LENGTH_SHORT).show();
        String data = "W"; //글자입력칸에 있는 글자를 String 형태로 받아서 data에 저장
        Log.w("NETWORK", " " + data);
        if (data != null) { //만약 데이타가 아무것도 입력된 것이 아니라면
            MainActivity.out.println(data); //data를 stream 형태로 변형하여 전송
        }
    }

    public void left() {
        //Toast.makeText(getApplicationContext(), "Go Forward", Toast.LENGTH_SHORT).show();
        String data = "A"; //글자입력칸에 있는 글자를 String 형태로 받아서 data에 저장
        Log.w("NETWORK", " " + data);
        if (data != null) { //만약 데이타가 아무것도 입력된 것이 아니라면
            MainActivity.out.println(data); //data를 stream 형태로 변형하여 전송
        }
    }

    public void right() {
        //Toast.makeText(getApplicationContext(), "Go Forward", Toast.LENGTH_SHORT).show();
        String data = "D"; //글자입력칸에 있는 글자를 String 형태로 받아서 data에 저장
        Log.w("NETWORK", " " + data);
        if (data != null) { //만약 데이타가 아무것도 입력된 것이 아니라면
            MainActivity.out.println(data); //data를 stream 형태로 변형하여 전송
        }
    }

    public void back() {
        //Toast.makeText(getApplicationContext(), "Go Forward", Toast.LENGTH_SHORT).show();
        String data = "X"; //글자입력칸에 있는 글자를 String 형태로 받아서 data에 저장
        Log.w("NETWORK", " " + data);
        if (data != null) { //만약 데이타가 아무것도 입력된 것이 아니라면
            MainActivity.out.println(data); //data를 stream 형태로 변형하여 전송
        }
    }

    public void stop() {
        //Toast.makeText(getApplicationContext(), "Go Forward", Toast.LENGTH_SHORT).show();
        String data = "S"; //글자입력칸에 있는 글자를 String 형태로 받아서 data에 저장
        Log.w("NETWORK", " " + data);
        if (data != null) { //만약 데이타가 아무것도 입력된 것이 아니라면
            MainActivity.out.println(data); //data를 stream 형태로 변형하여 전송
        }
    }

    public void manual() {
        //Toast.makeText(getApplicationContext(), "Go Forward", Toast.LENGTH_SHORT).show();
        String data = "M"; //글자입력칸에 있는 글자를 String 형태로 받아서 data에 저장
        Log.w("NETWORK", " " + data);
        if (data != null) { //만약 데이타가 아무것도 입력된 것이 아니라면
            MainActivity.out.println(data); //data를 stream 형태로 변형하여 전송
        }
    }

    public void auto() {
        //Toast.makeText(getApplicationContext(), "Go Forward", Toast.LENGTH_SHORT).show();
        String data = "U"; //글자입력칸에 있는 글자를 String 형태로 받아서 data에 저장
        Log.w("NETWORK", " " + data);
        if (data != null) { //만약 데이타가 아무것도 입력된 것이 아니라면
            MainActivity.out.println(data); //data를 stream 형태로 변형하여 전송
        }
    }

    public void storeimg()  {
        //Toast.makeText(getApplicationContext(), "Go Forward", Toast.LENGTH_SHORT).show();
        String data = "I"; //글자입력칸에 있는 글자를 String 형태로 받아서 data에 저장
        Log.w("NETWORK", " " + data);
        if (data != null) { //만약 데이타가 아무것도 입력된 것이 아니라면
            MainActivity.out.println(data); //data를 stream 형태로 변형하여 전송
        }

    }

    public void takelocation() {
        String data = "T"; //글자입력칸에 있는 글자를 String 형태로 받아서 data에 저장
        Log.w("NETWORK", " " + data);
        if (data != null) { //만약 데이타가 아무것도 입력된 것이 아니라면
            MainActivity.out.println(data); //data를 stream 형태로 변형하여 전송
        }
    }


    public void exit() {
        //Toast.makeText(getApplicationContext(), "Go Forward", Toast.LENGTH_SHORT).show();
        String data = "Q"; //글자입력칸에 있는 글자를 String 형태로 받아서 data에 저장
        Log.w("NETWORK", " " + data);
        if (data != null) { //만약 데이타가 아무것도 입력된 것이 아니라면
            MainActivity.out.println(data); //data를 stream 형태로 변형하여 전송
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forward = (Button) findViewById(R.id.auto1);
        left = (Button) findViewById(R.id.auto2);
        right = (Button) findViewById(R.id.auto3);
        back = (Button) findViewById(R.id.auto4);
        stop = (Button) findViewById(R.id.stop);
        showimg = (Button) findViewById(R.id.showimg);
        connect = (Button) findViewById(R.id.connect);
        exit = (Button) findViewById(R.id.exit);
        storeimg = (Button) findViewById(R.id.storeimg);
        signal = (TextView) findViewById(R.id.signal);
        location = (TextView) findViewById(R.id.location);
        image = (ImageView) findViewById(R.id.image);
        manual = (Button) findViewById(R.id.manual);
        auto = (Button) findViewById(R.id.auto);
        takelocation = (Button) findViewById(R.id.takelocation);

        edtTextAddress = (EditText) findViewById(R.id.address);



        storeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //버튼이 클릭되면
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        storeimg();
                        try {
                            String savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/face";
                            int filesCount = dis.readInt();
                            File[] files = new File[filesCount];
                            for (int i = 0; i < filesCount; i++) {
                                long fileLength = dis.readLong();
                                String fileName = dis.readUTF();
                                files[i] = new File(savePath, fileName);
                                FileOutputStream fos = new FileOutputStream(files[i]);
                                BufferedOutputStream bos = new BufferedOutputStream(fos);
                                for (int j = 0; j < fileLength; j++)
                                    bos.write(bis.read());
                                bos.flush();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        showimg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //버튼이 클릭되면
                Toast.makeText(getApplicationContext(), "Show Image", Toast.LENGTH_SHORT).show();
                Bitmap bit = BitmapFactory.decodeFile("/storage/emulated/0/face/frame.jpg");
                image.setImageBitmap(bit);
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //버튼이 클릭되면
                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        forward();
                    }
                }).start();
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //버튼이 클릭되면
                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        left();
                    }
                }).start();
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //버튼이 클릭되면
                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        right();
                    }
                }).start();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //버튼이 클릭되면
                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        back();
                    }
                }).start();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //버튼이 클릭되면
                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        stop();
                    }
                }).start();
            }
        });

        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //버튼이 클릭되면
                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        manual();
                    }
                }).start();
            }
        });

        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //버튼이 클릭되면
                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        auto();
                    }
                }).start();
            }
        });

        takelocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //버튼이 클릭되면
                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        takelocation();
                    }
                }).start();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //버튼이 클릭되면
                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        exit();
                    }
                }).start();
            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectThread thread = new ConnectThread();
                thread.start();
            }
        });

        //connect.setOnClickListener(buttonConnectOnClickListener);
    }

    class ConnectThread extends Thread{
        public void run(){
            try{
                int port = 6666;
                socket = new Socket("192.168.43.6", port);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                bis = new BufferedInputStream(socket.getInputStream());
                dis = new DataInputStream(bis);
                //msg = in.readLine();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MessageThread thread = new MessageThread();
                        thread.start();
                    }
                });
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class MessageThread extends Thread{


        @Override
        public void run(){
            try{
                while(true){
                    msg = in.readLine();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (msg.contains("location")) {
                                location.post(new Runnable(){
                                    public void run() {
                                        location.setText(msg);
                                    }
                                });
                            }else if(msg.contains("signal")){
                                signal.post(new Runnable(){
                                    public void run(){
                                        signal.setText(msg);
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                signal.setText("");
                                            }
                                        }, 3000);
                                    }
                                });
                            }
                        }
                    });
                    Thread.sleep(3000);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        try {
            if(socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

