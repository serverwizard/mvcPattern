package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConnectionPool {
	private String url;
	private String username;
	private String password;
	ArrayList<Connection> connList = new ArrayList<Connection>();

	  public DBConnectionPool(String driver, String url, 
		      String username, String password) throws Exception {
		    
		  this.url = url;
		    this.username = username;
		    this.password = password;
		    
		    Class.forName(driver);
		  }

	// 커넥션 객체 요청
	public Connection getConnection() throws SQLException {
		if (connList.size() > 0) {
			 Connection conn = connList.remove(0); 
			// DB 커넥션 객체도 일정 시간이 지나면 서버와의 연결이 끊어지기 때문에
			// 유효성 체크한 다음에 반환
			   if (conn.isValid(10)) {
			        return conn;
			      }
		}
	    return DriverManager.getConnection(url, username, password);
	  }
	
	// 커넥션 객체 반환
	public void returnConnection(Connection conn) throws Exception {
		connList.add(conn);
	}
	
	public void closeAll() {
		for(Connection conn: connList){
			try{ conn.close();} catch(Exception e){ e.printStackTrace(); }
		}
	}
}



