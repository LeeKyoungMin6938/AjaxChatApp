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
		request.setCharacterEncoding("UTF-8"); // ��� �޽����� �Ķ���� ���·� �޾Ƽ� ó���Ұ�. (UTF-8�� ���ڵ��ؼ�)
		response.setContentType("text/html; charset=UTF-8");
		String chatName= request.getParameter("chatName");
		String chatContent= request.getParameter("chatContent");
		
		if(chatName ==null || chatName.equals("") || chatContent==null || chatContent.equals("")) {
			// getWriter ? : ��κ� Ŭ���̾�Ʈ�� �����͸� �����ϱ� ���Ͽ� Response ��ü�� ����Ѵ�. �̸� ���� Response��ü�� setContentType() �� getWriter()�޼ҵ带 �̿��Ѵ�.
			// ServletResponse �������̽��� �ΰ��� ��Ʈ���� �����Ѵ�. ����Ʈ(bytes) �� ����ϱ� ���� ServletOutputStream �� ����(character data)�� ����ϱ� ���� PrintWriter.
			// PrintWriter �� �ؽ�Ʈ �����͸� ����� �� ����Ѵ�.
			response.getWriter().write("0"); // �����߻��� 0���
		}
		else {
			// write() �� ���� 1�� �� ���̴�.(���������� db�� ��ϵȴٸ�)
			response.getWriter().write(new ChatDAO().submit(chatName, chatContent)+"");
		}
	}

}
