package controller.administer.post;

import java.io.IOException; // IOExceptionのインポート
import javax.servlet.ServletException; // サーブレット例外のインポート
import javax.servlet.annotation.WebServlet; // WebServletアノテーションのインポート
import javax.servlet.http.HttpServlet; // HttpServletクラスのインポート
import javax.servlet.http.HttpServletRequest; // HttpServletRequestのインポート
import javax.servlet.http.HttpServletResponse; // HttpServletResponseのインポート
import javax.servlet.http.HttpSession; // HttpSessionのインポート

import entity.Post; // Postエンティティのインポート
import modelUtil.Failure;
import dao.PostsDao; // PostsDaoのインポート
import dao.DaoException; // DaoExceptionのインポート
import java.time.LocalDateTime; // LocalDateTimeのインポート

@WebServlet("/administer/post/execute-delete") // このサーブレットのURLマッピング
public class ExecuteDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // 文字化け防止のためリクエストのエンコーディングをUTF-8に設定

        // messageとerrorの取得・セット
        String message = request.getParameter("message");
        if (message != null && !message.isEmpty()) {
            request.setAttribute("message", message);
        }
        String error = request.getParameter("error");
        if (error != null && !error.isEmpty()) {
            request.setAttribute("error", error);
        }

        int postId = Integer.parseInt(request.getParameter("postId")); // リクエストパラメータからpostIdを取得

        PostsDao postsDao = new PostsDao(); // 投稿データアクセスオブジェクトを生成
        try {
            postsDao.delete(postId); // 投稿をデータベースから削除
            response.sendRedirect(request.getContextPath() + "/administer/post/home?message=" + java.net.URLEncoder.encode("投稿の削除が完了しました。", "UTF-8")); // 成功メッセージと共にリダイレクト
        } catch (DaoException e) {
            String errorMessage = java.net.URLEncoder.encode("投稿の削除中にエラーが発生しました。", "UTF-8"); // エラーメッセージをURLエンコード
            response.sendRedirect(request.getContextPath() + "/administer/post/home?error=" + errorMessage); // エラーメッセージと共にリダイレクト
        }
    }
}