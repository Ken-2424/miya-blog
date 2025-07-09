package controller.administer.post;

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

@WebServlet("/administer/post/update") // このサーブレットのURLマッピング
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
            PostsDao postsDao = new PostsDao(); // 投稿データアクセスオブジェクトを生成
            int postId = Integer.parseInt(request.getParameter("postId")); // リクエストパラメータからpostIdを取得
            Post post = postsDao.getOne(postId); // 投稿データを取得
            if(post == null) {
                throw new ServletException("指定された投稿が見つかりません。"); // 投稿が見つからない場合は例外をスロー
            }
            request.setAttribute("post", post); // 投稿データをリクエスト属性にセット
        } catch (Exception e) {
            throw new ServletException("投稿データの取得中にエラーが発生しました。", e); // 例外発生時はServletExceptionでラップしてスロー
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/administer/post/update.jsp"); // 更新画面のJSPへのディスパッチャ取得
        dispatcher.forward(request, response); // JSPへフォワード
    }
}