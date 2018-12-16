package Schedule;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Schedule extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String stuId = request.getParameter("stuId");
        String schedule = new Crawl().getSchedule(stuId);
        response.setContentType("text/html;charset=gb2312");
        request.setAttribute("schedule",schedule);
        RequestDispatcher view = request.getRequestDispatcher("Schedule.jsp");
        view.forward(request,response );
    }
}
