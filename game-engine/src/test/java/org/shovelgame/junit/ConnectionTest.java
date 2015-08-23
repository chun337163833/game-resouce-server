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
		LineReader reader = new LineReader(is);
		try {
			String line = null;
			while((line = reader.readLine()) != null) {
				Command c = Command.fromString(line);
				if(c.getStatus().equals(CommandStatus.Ok)) {
					c = CommandName.Authentication.createCommand("Successfully authenticated.").asResponse();
					os.write(c.toJson().getBytes());
					os.write("\n".getBytes());
				}
//				os.write(new String("test").getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			reader.close();
			ss.close();
		}
		
	}
	
}
