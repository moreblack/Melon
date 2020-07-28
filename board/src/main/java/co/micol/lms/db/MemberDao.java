/*
 * CRUD 기본
 * Member dao
 * 2020.07.22
 * Micol
 */
package co.micol.lms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

	private String driver ="oracle.jdbc.driver.OracleDriver"; //벤더 제공
	private String url ="jdbc:oracle:thin:@localhost:1521:xe";
	//url ="jdbc:oracle:thin:// 클라이언트제공
	private String user = "micol";// 클라이언트제공
	private String password = "1234";// 클라이언트제공
	
	private Connection conn;  //연결
	private PreparedStatement psmt; //명령어 전달
	private ResultSet rs; //결과 Set을 받는다
	
	private final String SELECT_MEMBER = "select * from member where id = ?";//final로 쿼리변경을 불가능하게 만듬
	private final String SELECT_MEMBER_ALL = "select * from member";
	private final String INSERT_MEMBER = "insert into member values(?,?,?,?)";
	private final String DELETE_MEMBER = "delete from member where id = ?";
	private final String UPDATE_MEMBER = "update member set  password = ?, name = ?, tel = ? where id = ?";
	
	
	public MemberDao() { //생성하면서 DB 커넥션을 한다
		try {//서버 안끄고 지속적으로 사용하기위해 예외처리를 한다
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public MemberVo select(MemberVo vo) { //한명의 멤버를 조회하기
		//sql 구문전달
		//결과 받기
		try {
			psmt = conn.prepareStatement(SELECT_MEMBER);
			psmt.setString(1, vo.getId());//1 물음표의 순서
			rs = psmt.executeQuery(); //결과를 result set객체로 받는다.
			if(rs.next()) {//커서로 조회
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setTel(rs.getString("tel"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	
	public List<MemberVo> SelectAllMemeber(){ //전체멤버를 조회하기
		List<MemberVo> list = new ArrayList<MemberVo>();
		
			try {
				psmt = conn.prepareStatement(SELECT_MEMBER_ALL);
				rs = psmt.executeQuery();
				while(rs.next()) {
					MemberVo vo = new MemberVo();
					vo.setId(rs.getString("id"));
					vo.setPassword(rs.getString("password"));
					vo.setName(rs.getString("name"));
					vo.setTel(rs.getString("tel"));	
					list.add(vo);
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	
	
		
		
		
		return list;
	}

	public int insert(MemberVo vo) {// 멤버 삽입
		int n = 0;
		
		try {
			psmt = conn.prepareStatement(INSERT_MEMBER);
			psmt .setString(1,vo.getId());
			psmt .setString(2,vo.getPassword());
			psmt .setString(3,vo.getName());
			psmt .setString(4,vo.getTel());
			n = psmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return n;
	}
	public int update(MemberVo vo) {// 멤버 수정
		
		
		int n = 0;
		try {
			psmt = conn.prepareStatement(UPDATE_MEMBER);
			psmt.setString(1,vo.getPassword());
			psmt.setString(2,vo.getName());
			psmt.setString(3,vo.getTel());
			psmt.setString(4,vo.getId());
			n = psmt.executeUpdate();			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return n;
	}
	public int delete(MemberVo vo) {// 멤버 삭제
		int n = 0;
		try {
			psmt = conn.prepareStatement(DELETE_MEMBER);
			psmt.setString(1,vo.getId());
								
			n = psmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		return n;
	}
}
