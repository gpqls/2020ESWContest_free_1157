package android2;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class SerialReader implements Runnable {

	InputStream in;


	public SerialReader(InputStream in) {
		this.in = in;
	}

	public void run() {

		byte[] buf = new byte[1024];
		int len = -1;
		try {
			while((len=this.in.read(buf)) > -1) {
				OutputStream output = new FileOutputStream("/home/hyebin/location/location.txt");
				String location = new String(buf, 0, len);
				byte[] by = location.getBytes();
				System.out.println(location);
				output.write(by);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

