package controller.reader;

import java.io.IOException; // IOExceptionのインポート
import javax.servlet.RequestDispatcher; // RequestDispatcherのインポート
import javax.servlet.ServletException; // サーブレット例外のインポート
import javax.servlet.annotation.WebServlet; // WebServletアノテーションのインポート
import javax.servlet.http.HttpServlet; // HttpServletクラスのインポート
import javax.servlet.http.HttpServletRequest; // HttpServletRequestのインポート
import javax.servlet.http.HttpServletResponse; // HttpServletResponseのインポート
import javax.servlet.http.HttpSession; // HttpSessionのインポート
import dao.PostsDao; // PostsDaoのインポート
import entity.Post; // Postエンティティのインポート

import java.util.ArrayList; // ArrayListのインポート
import java.util.List; // Listのインポート

@WebServlet("/reader/home") // このサーブレットのURLマッピング
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // messageとerrorの取得・セット
        String message = request.getParameter("message");
        if (message != null && !message.isEmpty()) {
            request.setAttribute("message", message);
        }
        String error = request.getParameter("error");
        if (error != null && !error.isEmpty()) {
            request.setAttribute("error", error);
        }

        try{
            PostsDao postsDao = new PostsDao();
            List<Post> posts = postsDao.getAll();
            if(posts == null){
                posts = new ArrayList<>();
            }
            request.setAttribute("posts", posts);
        } catch (Exception e){
            throw new ServletException("投稿データの取得中にエラーが発生しました。", e);
        }

        String alertMessage = request.getParameter("message");
        if(alertMessage != null && !alertMessage.isEmpty()){
            request.setAttribute("alertMessage", alertMessage); 
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/reader/home.jsp");
        dispatcher.forward(request, response);
    }
}