package controller.Pycharm;

import Util.PostUtil;
import bean.Book;
import bean.User;
import com.google.gson.Gson;
import dao.DbBook;
import dao.DbUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "BookEx", urlPatterns = {"/bookEx"})
public class BookEx extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, String> map = PostUtil.getMap(request);
        try {
            Book b =DbBook.bookEx(map.get("ISBN"));
            PrintWriter writer = response.getWriter();
            writer.write(new Gson().toJson(b));
            writer.close();
            //浏览记录
            HttpSession session =  request.getSession();
            User usr = (User) session.getAttribute("login");
            //TODO:DELETE
            //usr = new User(9,"123","456");
            //内部处理没登录的
            DbUser.updateView(usr,map.get("ISBN"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
