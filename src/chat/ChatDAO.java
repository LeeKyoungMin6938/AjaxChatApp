package chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


//�����ͺ��̽� ����
public class ChatDAO {
	private Connection conn;
	
	//ChatDAO �� ��������� ����, �����ͺ��̽��� ������.
	public ChatDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3307/ANONYMOUSCHAT";
			String dbID = "root";
			String dbPassword = "1234";
			
			//Class.forName (�Ｎ���),  String str = new String("�̸�������");
			//Ŭ������ �޸𸮷� �ε��Ѵ�.
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
			//��������� db����.			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//ArrayList �� ������ ä�ø޽����� ����. ���ݱ��� �Էµ� ä���� ������ �Լ� (���ð������缭)
	public ArrayList<Chat> getChatList(String nowTime){
		ArrayList<Chat> chatList = null;
		PreparedStatement pstmt = null; //sql ���� ������ü
		ResultSet rs = null; // ����� 
		String SQL = "select * from chat where chatTime > ? order by chatTime";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, nowTime);
			rs = pstmt.executeQuery();
			chatList = new ArrayList<Chat>();
			while(rs.next()) {
				Chat chat = new Chat();
				chat.setChatID(rs.getInt("chatID"));
				chat.setChatName(rs.getString("chatName"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
				String timeType = "����";
				if(Integer.parseInt(rs.getString("chatTime").substring(11,13))>=12) {
					timeType = "����";
					chatTime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0,11)+" "+ timeType + " " + chatTime + ":" + rs.getString("chatTime").substring(14,16) + " ");
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
	
	//�ֱ����� ���� number����ŭ�� �����͸� ����Ʈ�� �������� �Լ�
	public ArrayList<Chat> getChatListByRecent(int number){
		ArrayList<Chat> chatList = null;
		PreparedStatement pstmt = null; //sql ���� ������ü
		ResultSet rs = null; // ����� 
		String SQL = "select * from chat where chatID > (select max(chatID) - ? from chat) order by chatTime";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			chatList = new ArrayList<Chat>();
			while(rs.next()) {
				Chat chat = new Chat();
				chat.setChatID(rs.getInt("chatID"));
				chat.setChatName(rs.getString("chatName"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
				String timeType = "����";
				if(Integer.parseInt(rs.getString("chatTime").substring(11,13))>=12) {
					timeType = "����";
					chatTime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0,11)+" "+ timeType + " " + chatTime + ":" + rs.getString("chatTime").substring(14,16) + " ");
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
		PreparedStatement pstmt = null; //sql ���� ������ü
		ResultSet rs = null; // ����� 
		String SQL = "insert into chat values (?, ?, now(), NULL)";		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, chatName);
			pstmt.setString(2, chatContent);
			return pstmt.executeUpdate(); // ���������� ������ �Ǿ��ٸ�, 1�� ��ȯ�ȴ�.			
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
	
	// ������� �����, mysql-connector-java-5.1.42-bin.jar (jdbc �������̽�����) �� �ٿ�޾Ƽ�, 
	// WEB-INF ���� ���� lib ������ �������ص�, ���� ������Ŭ�� -> buildpath �ؼ� ������Ʈ�� ��Ͻ����ش�.
	// ��ϵ������� Referenced Libraries �� ��ϵȴ�.
}
