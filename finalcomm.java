package android2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import com.fazecast.jSerialComm.SerialPort;

public class finalcomm {
	private static WatchKey watchkey;
	
	
	public static void main(String[] args) throws IOException{
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		BufferedReader in2 = null;
		serverSocket = new ServerSocket(6666);
		System.out.println("Socket Open");
		String inputLine = null;
		String inputLine2 = null;
		WatchService watchService = FileSystems.getDefault().newWatchService(); //디렉토리 내 파일 변화 감지 API
		Path path1 = Paths.get("/home/hyebin/image"); //감지할 디렉토리 경로 설정
		path1.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY); //여러 설정 중 디렉토리 내 파일이 수정되는 경우를 디렉토리에 설정
		
		int baudRate = 115200;
		int dataBits = 8;
		int stopBits = 0;
		int parity = 0;
		
		try {
			clientSocket = serverSocket.accept();
			System.out.println("Connect client");
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			SerialPort comPort = SerialPort.getCommPort("ttyUSB0");
			comPort.openPort(); //시리얼 포트 오픈
			comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
			comPort.setComPortParameters(baudRate, dataBits, stopBits, parity);
			
			InputStream sin = comPort.getInputStream();
			OutputStream sout = comPort.getOutputStream();
			
			//in2 = new BufferedReader(new InputStreamReader(sin));
			
			
			(new Thread(new SerialReader(sin))).start(); 
			(new Thread(new NewFile(watchkey, watchService, comPort, sout, out, sin))).start(); //파일 변화하면 MCU와 APP에 신호 뿌리기 -> signal 표시 뜨게 하기
			System.out.println("Ready to Start UART communication");
			String fpath = "/home/hyebin/location";
			File f = new File(fpath);
			
			while(true) {
				if(in.ready()) {		
					inputLine = in.readLine();
					System.out.println("received data : " + inputLine);
					
					if(inputLine.equals("W")) { //전진
						sout.write(87);
						sout.close();
					}
					if(inputLine.equals("A")) { //왼쪽 회전
						sout.write(65);
						sout.close();
					}
					if(inputLine.equals("D")) { //오른쪽 회전
						sout.write(68);
						sout.close();
					}
					if(inputLine.equals("X")) { //후진
						sout.write(88);
						sout.close();
					}
					if(inputLine.equals("S")) { //정지
						sout.write(83);
						sout.close();
					}
					if(inputLine.equals("M")) { //수동조작활성화
						sout.write(77);
						sout.close();
					}
					if(inputLine.equals("U")) { //자동주행활성화
						sout.write(85);
						sout.close();
					}
					if(inputLine.equals("I")) { //이미지 요청
						String path = "/home/hyebin/image";
						File[] files = new File(path).listFiles();
						try {
							BufferedOutputStream bos = new BufferedOutputStream(clientSocket.getOutputStream());
							DataOutputStream dos = new DataOutputStream(bos);
							dos.writeInt(files.length);
							for(File file : files) {
								long length = file.length();
								dos.writeLong(length);
								String name = file.getName();
								System.out.println("Send : " + file.getName());
								dos.writeUTF(name);
								FileInputStream fis = new FileInputStream(file);
								BufferedInputStream bis = new BufferedInputStream(fis);
								int theByte = 0;
								while((theByte = bis.read()) != -1) {
									bos.write(theByte);
								}
								bos.flush();
								System.out.println("File transfer complete");
								bis.close();
							}
							dos.flush();
							
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
					
					if(inputLine.equals("T")) { //좌표 요청
						try {
							File file = new File("/home/hyebin/location/location.txt");
							FileReader filereader = new FileReader(file);
							BufferedReader bufReader = new BufferedReader(filereader);
							String line="";
							while((line=bufReader.readLine()) != null) {
								System.out.println(line);
								out.println("location : "+line);
							}
							bufReader.close();
						}catch(FileNotFoundException e) {
							
						}catch(IOException e) {
							e.printStackTrace();
						}
						sout.close();
						
					}
					if(inputLine.equals("Q")) { //연결해제
						out.close();
						in.close();
						clientSocket.close();
						serverSocket.close();
						System.exit(0);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
}



