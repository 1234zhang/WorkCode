package servlet.search;

import org.json.JSONArray;
import org.json.JSONObject;
import service.search.SearchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet("/search/sing")
public class SingSearch extends HttpServlet {
    private static SearchService searchService;
    @Override
    public void init() throws ServletException {
        searchService = SearchService.getInstance();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String singName = request.getParameter("key");
        response.addHeader("Access-Control-AllowHeaders","Content-Type");
        response.addHeader("Access-Control-Allow-Method","*");
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Max-Age","3600");
        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setCharacterEncoding("GB2312");
        response.setContentType("text/plain");
        JSONArray data = searchService.getSingJson(singName);
        JSONObject singJson = new JSONObject();
        singJson.put("result", data);
        singJson.put("statues",200);
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        response.getOutputStream()
                )
        );
        writer.write(singJson.toString());
        writer.flush();
        writer.close();
    }
}
