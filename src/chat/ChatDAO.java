package chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


//데이터베이스 구축
public class ChatDAO {
	private Connection conn;
	
	//ChatDAO 가 만들어지자 마자, 데이터베이스가 연동됨.
	public ChatDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3307/ANONYMOUSCHAT";
			String dbID = "root";
			String dbPassword = "1234";
			//Class.forName (즉석김밥),  String str = new String("미리만든김밥");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
			//여기까지가 db연동.			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//ArrayList 에 각각의 채팅메시지를 담음. 지금까지 입력된 채팅을 가져올 함수 (현시각에맞춰서)
	public ArrayList<Chat> getChatList(String nowTime){
		ArrayList<Chat> chatList = null;
		PreparedStatement pstmt = null; //sql 문을 넣을객체
		ResultSet rs = null; // 결과값 
		String SQL = "select * from chat where chatTime > ? order by chatTime";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, nowTime);
			rs = pstmt.executeQuery();
			chatList = new ArrayList<Chat>();
			while(rs.next()) {
				Chat chat = new Chat();
				chat.setChatName(rs.getString("chatName"));
				chat.setChatContent(rs.getString("chatContent"));
				chat.setChatTime(rs.getString("chatTime"));
				chatList.add(chat);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		return chatList;
	}
	
	public int submit (String chatName, String chatContent) {		
		PreparedStatement pstmt = null; //sql 문을 넣을객체
		ResultSet rs = null; // 결과값 
		String SQL = "insert into chat values (?, ?, now())";		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, chatName);
			pstmt.setString(2, chatContent);
			return pstmt.executeUpdate(); // 정상적으로 실행이 되었다면, 1이 반환된다.			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		return -1;
	}
	
	// 여기까지 만들고, mysql-connector-java-5.1.42-bin.jar (jdbc 인터페이스파일) 을 다운받아서, 
	// WEB-INF 파일 안의 lib 폴더에 복사해준뒤, 파일 오른쪽클릭 -> buildpath 해서 프로젝트에 등록시켜준다.
}
