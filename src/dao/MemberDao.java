package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import util.DBConnectionPool;
import vo.Member;


// DB ConnectionPool 적용
public class MemberDao {
//	Connection connection;
//	DBConnectionPool connPool;
	DataSource ds;
	
//	public void setConnection(Connection connection) {
//		this.connection = connection;
//	}
	
//	  public void setDbConnectionPool(DBConnectionPool connPool) {
//		    this.connPool = connPool;
//		  }
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	  public List<Member> selectList() throws Exception {
		    Connection connection = null;
		    Statement stmt = null;
		    ResultSet rs = null;

		try {
//			connection = connPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT MNO,MNAME,EMAIL,CRE_DATE" + " FROM MEMBERS" + " ORDER BY MNO ASC");

			 ArrayList<Member> members = new ArrayList<Member>();

			while (rs.next()) {
				members.add(
						new Member()
						.setNo(rs.getInt("MNO"))
						.setName(rs.getString("MNAME"))
						.setEmail(rs.getString("EMAIL"))
						.setCreatedDate(rs.getDate("CRE_DATE")));
			}

			return members;
		} catch (SQLException e) {
			throw e;
		} finally {
			 try {if (rs != null) rs.close();} catch(Exception e) {}
		     try {if (stmt != null) stmt.close();} catch(Exception e) {}
//		     if (connection != null) connPool.returnConnection(connection);
		     try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	} // end of selectList()
	
	
	public int insert(Member member) throws Exception {
		  Connection connection = null;
		    PreparedStatement stmt = null;

	    try {
//	      connection = connPool.getConnection();
	     connection = ds.getConnection();
	      stmt = connection.prepareStatement(
	          "INSERT INTO MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)"
	              + " VALUES (?,?,?,NOW(),NOW())");
	      stmt.setString(1, member.getEmail());
	      stmt.setString(2, member.getPassword());
	      stmt.setString(3, member.getName());
	      return stmt.executeUpdate();

	    } catch (Exception e) {
	      throw e;

	    } finally {
	      try {if (stmt != null) stmt.close();} catch(Exception e) {}
//	      if (connection != null) connPool.returnConnection(connection);
	      try {if (connection != null) connection.close();} catch(Exception e) {}
	    }
	} // end of insert()
	
	public int delete(int no) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		try {
//			connection = connPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.createStatement();
			return stmt.executeUpdate(
					"DELETE FROM MEMBERS WHERE MNO=" + no);
			
		} catch (SQLException e) {
			throw e;
		}  finally {
		      try {if (stmt != null) stmt.close();} catch(Exception e) {}
//		      if (connection != null) connPool.returnConnection(connection);
		      try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	} // end of delete();
	
	public int updeate(Member member) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
//			connection = connPool.getConnection();
			 connection = ds.getConnection();
			pstmt = connection.prepareStatement(
					 "UPDATE MEMBERS "
					 + "SET EMAIL=?,MNAME=?,MOD_DATE=now()"
				     + " WHERE MNO=?");
			
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getName());
			pstmt.setInt(3, member.getNo());
			return pstmt.executeUpdate();
	
		} catch (SQLException e) {
			throw e;
		} finally {
		      try {if (pstmt != null) pstmt.close();} catch(Exception e) {}
//		      if (connection != null) connPool.returnConnection(connection);
		      try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	} // end of update()
	//
	public Member selectOne(int no) throws Exception {
		 Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
//			connection = connPool.getConnection();
			 connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					 "SELECT MNO,EMAIL,MNAME,CRE_DATE FROM MEMBERS" + 
				              " WHERE MNO=" + no); 
			
			if(rs.next()) {
			    return new Member()
			            .setNo(rs.getInt("MNO"))
			            .setEmail(rs.getString("EMAIL"))
			            .setName(rs.getString("MNAME"))
			            .setCreatedDate(rs.getDate("CRE_DATE"));

			} else {
				 throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}
		 } catch (Exception e) {
		      throw e;
	    } finally {
	      try {if (rs != null) rs.close();} catch(Exception e) {}
	      try {if (stmt != null) stmt.close();} catch(Exception e) {}
//	      if (connection != null) connPool.returnConnection(connection);
	      try {if (connection != null) connection.close();} catch(Exception e) {}
	    }
		
	} // end of selectOne()
	
	  public Member exist(String email, String password) throws Exception {
		  Connection connection = null;  
		  PreparedStatement stmt = null;
		    ResultSet rs = null;

		    try {
//		    connection = connPool.getConnection();
		    	 connection = ds.getConnection();
		      stmt = connection.prepareStatement(
		          "SELECT MNAME,EMAIL FROM MEMBERS"
		              + " WHERE EMAIL=? AND PWD=?");
		      stmt.setString(1, email);
		      stmt.setString(2, password);
		      rs = stmt.executeQuery();
		      if (rs.next()) {
		        return new Member()
		          .setName(rs.getString("MNAME"))
		          .setEmail(rs.getString("EMAIL"));
		      } else {
		        return null;
		      }
		    } catch (Exception e) {
		      throw e;

		    } finally {
		      try {if (rs != null) rs.close();} catch (Exception e) {}
		      try {if (stmt != null) stmt.close();} catch (Exception e) {}
//		      if (connection != null) connPool.returnConnection(connection);
		      try {if (connection != null) connection.close();} catch(Exception e) {}
		    }
		  }
	
}
