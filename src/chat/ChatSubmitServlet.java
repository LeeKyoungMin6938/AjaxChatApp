package chat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChatSubmitServlet
 */
@WebServlet("/ChatSubmitServlet")
public class ChatSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 어떠한 메시지를 파라미터 형태로 받아서 처리할것. (UTF-8로 인코딩해서)
		response.setContentType("text/html; charset=UTF-8");
		String chatName= request.getParameter("chatName");
		String chatContent= request.getParameter("chatContent");
		
		if(chatName ==null || chatName.equals("") || chatContent==null || chatContent.equals("")) {
			// getWriter ? : 대부분 클라이언트에 데이터를 전송하기 위하여 Response 객체를 사용한다. 이를 위해 Response객체의 setContentType() 과 getWriter()메소드를 이용한다.
			// ServletResponse 인터페이스는 두가지 스트림을 제공한다. 바이트(bytes) 를 출력하기 위한 ServletOutputStream 과 문자(character data)를 출력하기 위한 PrintWriter.
			// PrintWriter 는 텍스트 데이터를 출력할 때 사용한다.
			response.getWriter().write("0"); // 오류발생시 0출력
		}
		else {
			// write() 의 값은 1이 될 것이다.(성공적으로 db에 등록된다면)
			response.getWriter().write(new ChatDAO().submit(chatName, chatContent)+"");
		}
	}

}
