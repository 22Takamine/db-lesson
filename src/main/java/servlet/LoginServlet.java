package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Product;
import service.ProductService;
import util.ParamUtil;

@WebServlet("/serch")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int idNumber = 0;
    	int priceNumber = 0;
        // 文字化け対策
        request.setCharacterEncoding("UTF-8");

        // ログイン
        String id = request.getParameter("id");
        String price = request.getParameter("price");

        // 入力値のチェック
        if (ParamUtil.isNullOrEmpty(id) || ParamUtil.isNullOrEmpty(price)) {
            // メッセージ設定
            request.setAttribute("msg", "対象のデータはありません。");

            // 次画面指定
            request.getRequestDispatcher("top.jsp").forward(request, response);
            return;
            
        }else {
        	idNumber = util.ParamUtil.checkAndParseInt(id);
        	priceNumber = util.ParamUtil.checkAndParseInt(price);
        }

        // ログインチェック
        ProductService productService = new ProductService();
        Product product = productService.authentication(idNumber,priceNumber);
        

        // 表示メッセージの受け渡し
        if (product != null) {
       
            List<Product> list = productService.findSerch(idNumber,priceNumber);
            request.setAttribute("productList", list);

            // 次画面指定
            request.getRequestDispatcher("searchResult.jsp").forward(request, response);
        } else {
            // メッセージ設定
            request.setAttribute("msg", "対象のデータはありません。");

            // 次画面指定
            request.getRequestDispatcher("top.jsp").forward(request, response);
        }
    }

}
