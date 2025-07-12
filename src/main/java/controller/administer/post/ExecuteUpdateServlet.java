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

@WebServlet("/administer/post/execute-update")
public class ExecuteUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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

        String title = request.getParameter("title");
        String text = request.getParameter("text");
        int userId = 1;
        int postId = Integer.parseInt(request.getParameter("postId"));

        Post post = null;

        try{
            PostsDao PostsDao = new PostsDao();
            Post oldPost = PostsDao.getOne(postId);
            post = new Post(postId, oldPost.getCreatedAt(), LocalDateTime.now(), title, text, userId);
            if(post == null){
                throw new ServletException("指定された投稿が見つかりません。"); // 投稿が見つからない場合は例外をスロー
            }
        } catch (Exception e) {
            throw new ServletException("投稿データの取得中にエラーが発生しました。", e); // 例外発生時はServletExceptionでラップしてスロー
        }

        PostsDao postsDao = new PostsDao(); // 投稿データアクセスオブジェクトを生成
        try{
            postsDao.update(post); // 投稿をデータベースに更新
            response.sendRedirect(request.getContextPath() + "/administer/post/home?message=" + java.net.URLEncoder.encode("投稿の更新が完了しました。", "UTF-8")); // 成功メッセージと共にリダイレクト
            return;// 更新後、投稿一覧ページへリダイレクト
        }catch (DaoException e) {
            String errorMessage = java.net.URLEncoder.encode("投稿の更新中にエラーが発生しました。", "UTF-8"); // エラーメッセージをURLエンコード
            response.sendRedirect(request.getContextPath() + "/administer/post/home?error=" + errorMessage); // エラーメッセージと共にリダイレクト
        }
    }   
}