package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rootservice.bean.User;
import com.rootservice.service.AdminService;

public class DOLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String resultpage = "/login";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		if(username!=null && password!=null){
			try{
				System.out.println(username);
				AdminService loginservice = new AdminService();
				int valid = loginservice.authentication(username, password);
				if(valid == 1){
					
					resultpage = "/homepage";
					
				}else{
					request.setAttribute("error", "Invalid username/password");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			request.setAttribute("error", "required all fields");
		}
		RequestDispatcher rd = request.getRequestDispatcher(resultpage);
		rd.forward(request, response);
	}
}
