package org.shovelgame.junit;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;
import org.shovelgame.engine.io.LineReader;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.engine.session.command.CommandName;
import org.shovelgame.engine.session.command.CommandStatus;

public class ConnectionTest {

	
	@Test
	public void createServer() throws Exception {
		
//		for(CommandName c: CommandName.values()) {
//			System.out.println(c.name() + " = \"" + c.name() + "\",");
//		}
		ServerSocket ss = new ServerSocket(1111);
		Socket s = ss.accept();
		InputStream is = s.getInputStream();
		OutputStream os = s.getOutputStream();
		for(int i = 0; i < 200; i++) {
			System.out.println("send " + i);
			os.write(("test " + i).getBytes());
			os.write('\n');
//			Thread.sleep(50);
		}
		os.write('\0');
		int v = -1;
		while((v = is.read()) > 0) {
			
		}
	}
	
}
