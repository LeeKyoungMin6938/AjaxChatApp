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
		request.setCharacterEncoding("UTF-8"); // ��� �޽����� �Ķ���� ���·� �޾Ƽ� ó���Ұ�. (UTF-8�� ���ڵ��ؼ�)
		response.setContentType("text/html; charset=UTF-8");
		String listType = request.getParameter("listType");
		if(listType == null || listType.equals("")) response.getWriter().write("");
		else if(listType.equals("today")) response.getWriter().write(getToday());
	}

	//json ����
	// \ : ������ �ǹ�.
	public String getToday() {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO(); //�����ͺ��̽� ���ٰ�ü
		ArrayList<Chat> chatList = chatDAO.getChatList(new SimpleDateFormat("yyyy-MM-dd").format(new Date())); //���糯¥�� �Է��̵ȴ�.
		for(int i = 0; i<chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getChatName()+"\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent()+"\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime()+"\"}]");
			if(i != chatList.size()-1) result.append(","); // ������ �޽����� �ƴ϶��, ','�� �־��ش�.			
		}
		result.append("]}");
		System.out.println(result.toString());		
		return result.toString();
	}
}
