package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
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
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

class StoreImg extends Thread{
    public void run(){
        try{
            String savePath = Environment.getExternalStorageDirectory().toString() + "/DCIM/Camera/frame.jpg";
            for (int k = 0; k < 100; k++) {
                BufferedInputStream[] bis = new BufferedInputStream[100];
                bis[k] = new BufferedInputStream(MainActivity.socket.getInputStream());
                DataInputStream dis = new DataInputStream(bis[k]);
                int filesCount = dis.readInt();
                //File[] files = new File[filesCount];
                for (int i = 0; i < filesCount; i++) {
                    long fileLength = dis.readLong();
                    String fileName = dis.readUTF();
                    File files = new File(savePath, fileName);
                    FileOutputStream fos = new FileOutputStream(files);
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    for (int j = 0; j < fileLength; j++)
                        bos.write(bis[k].read());
                    bos.flush();
                    //bis[k].close();
                }

            }
            // dis.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}




public class MainActivity extends AppCompatActivity {
    static Socket socket;
    static BufferedReader in;
    static BufferedReader in2;
    static PrintWriter out;
    static BufferedInputStream bis;
    static DataInputStream dis;
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


    String msg;


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
        String data = "A"; //글자입력칸에 있는 글자를 String 형태로 받아서 data에 저장
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

    public void storeimg() {
        //Toast.makeText(getApplicationContext(), "Go Forward", Toast.LENGTH_SHORT).show();
        String data = "I"; //글자입력칸에 있는 글자를 String 형태로 받아서 data에 저장
        Log.w("NETWORK", " " + data);
        if (data != null) { //만약 데이타가 아무것도 입력된 것이 아니라면
            MainActivity.out.println(data); //data를 stream 형태로 변형하여 전송
        }
        try{
            String savePath = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/face";
            int filesCount = MainActivity.dis.readInt();
            //File[] files = new File[filesCount];
            for (int i = 0; i < filesCount; i++) {
                long fileLength = MainActivity.dis.readLong();
                String fileName = MainActivity.dis.readUTF();
                File files = new File(savePath, fileName);
                FileOutputStream fos = new FileOutputStream(files);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                for (int j = 0; j < fileLength; j++)
                    bos.write(bis.read());
                bos.flush();
                //bis[k].close();
            }
            // dis.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void takelocation() {
        //Toast.makeText(getApplicationContext(), "Go Forward", Toast.LENGTH_SHORT).show();
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
                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        storeimg();
                    }
                }).start();
            }

            /*public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Store Image", Toast.LENGTH_SHORT).show();
                String data = "I"; //글자입력칸에 있는 글자를 String 형태로 받아서 data에 저장
                Log.w("NETWORK", " " + data);
                if (data != null) { //만약 데이타가 아무것도 입력된 것이 아니라면
                    out.println(data); //data를 stream 형태로 변형하여 전송
                }
                Thread image = new StoreImg();
                image.start();
            }*/
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

        connect.setOnClickListener(buttonConnectOnClickListener);
    }





    View.OnClickListener buttonConnectOnClickListener = new View.OnClickListener(){
        public void onClick(View arg0){
            NetworkTask myClientTask = new NetworkTask(edtTextAddress.getText().toString(), 6666);
            myClientTask.execute();
        }
    };

    public class NetworkTask extends AsyncTask<Void, Void, Void>{
        String dstAddress;
        int dstPort;

        NetworkTask(String addr, int port){
            dstAddress = addr;
            dstPort = port;
        }

        @Override
        protected Void doInBackground(Void... arg0){
            try{
                Socket socket = new Socket(dstAddress, dstPort);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                bis = new BufferedInputStream(socket.getInputStream());
                dis = new DataInputStream(bis);
                msg = in.readLine();
            }catch(UnknownHostException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result){
            try{
                while (true) {
                    if (msg.contains("signal")) {
                        signal.post(new Runnable() {
                            public void run() {
                                signal.setText(msg);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        signal.setText("");
                                    }
                                }, 3000);
                            }
                        });
                    } else if (msg.contains("location")) {
                        location.post(new Runnable() {
                            public void run() {
                                location.setText(msg);
                            }
                        });
                    }
                    Thread.sleep(300);
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            super.onPostExecute(result);
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

