package android2;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.nio.file.WatchEvent.Kind;
import java.util.*;

import com.fazecast.jSerialComm.SerialPort;

public class NewFile implements Runnable{
	
	WatchKey watchkey;
	WatchService watchService;
	SerialPort comPort;
	InputStream in;
	PrintWriter out;
	OutputStream sout;
	
	public NewFile(WatchKey watchkey, WatchService watchService, SerialPort comPort, OutputStream sout, PrintWriter out, InputStream in) {
		this.watchkey = watchkey;
		this.watchService = watchService;
		this.comPort = comPort;
		this.in = in;
		this.out = out;
		this.sout = sout;
	}

	public void run() {
		while(true) {
			try {
				watchkey = watchService.take();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			List<WatchEvent<?>> events = watchkey.pollEvents();
			for(WatchEvent<?> event : events) {
				Kind<?> kind = event.kind();
				Path pth = (Path)event.context();
				if(kind.equals(StandardWatchEventKinds.ENTRY_MODIFY)) { //파일 변화 감지되면
					try {
						sout = comPort.getOutputStream();
						String signal = "signal";
						out.println(signal); 
						sout.write(97);
						System.out.println("New File");
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
			if(!watchkey.reset()) {
				try {
					watchService.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		
		}
	
	}
}
