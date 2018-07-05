package chat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChatSubmitServlet
 */
@WebServlet("/ChatListServlet")
public class ChatListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 어떠한 메시지를 파라미터 형태로 받아서 처리할것. (UTF-8로 인코딩해서)
		response.setContentType("text/html; charset=UTF-8");
		String listType = request.getParameter("listType");
		if(listType == null || listType.equals("")) response.getWriter().write("");
		else if(listType.equals("today")) response.getWriter().write(getToday());
	}

	//json 으로
	// \ : 개행의 의미.
	public String getToday() {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO(); //데이터베이스 접근객체
		ArrayList<Chat> chatList = chatDAO.getChatList(new SimpleDateFormat("yyyy-MM-dd").format(new Date())); //현재날짜가 입력이된다.
		for(int i = 0; i<chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getChatName()+"\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent()+"\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime()+"\"}]");
			if(i != chatList.size()-1) result.append(","); // 마지막 메시지가 아니라면, ','를 넣어준다.			
		}
		result.append("]}");
		System.out.println(result.toString());		
		return result.toString();
	}
}
