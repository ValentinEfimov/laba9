package Lab9.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletConfig;

import Lab9.entity.Ad;
import Lab9.entity.AdList;
import Lab9.entity.UserList;
import Lab9.helper.AdListHelper;
import Lab9.helper.UserListHelper;

public class StartupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // В методе инициализации будут создаваться общие структуры данных
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
// Загрузить список пользователей
        UserList userList = UserListHelper.readUserList(getServletContext());
// Сохранить список пользователей в контексте сервлета
// (для JSP это тождественно равно applicationContext)
        getServletContext().setAttribute("users", userList);
// Загрузить список сообщений
        AdList adList = AdListHelper.readAdList(getServletContext());
// Сохранить список объявлений в контексте сервлета
// (для JSP это тождественно равно applicationContext)
        getServletContext().setAttribute("ads", adList);
        for (Ad ad: adList.getAds()) {
// Т.к. в сообщениях изначально присутствует только id автора, для удобства установим ссылки
            ad.setAuthor(userList.findUser(ad.getAuthorId()));
// Инициализировать значения свойства lastModifiedDate
            ad.setLastModified(ad.getLastModified());
        }
    }
}
