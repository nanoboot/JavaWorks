package ru.itis.servlets;

import ru.itis.factories.ServiceFactory;
import ru.itis.models.Owner;
import ru.itis.service.OwnersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Servlet extends HttpServlet {

    OwnersService ownerService;

    @Override
    public void init() throws ServletException {
        super.init();
        ownerService = ServiceFactory.getInstance().getOwnerService();
    }

    // метод, реагирующий на http-запрос
    // request - здесь лежит сам запрос
    // response - сюда вы положите ответ
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=windows-1251");

        PrintWriter out = response.getWriter();

        List<Owner> allOwners = ownerService.getAllUser();
        out.println("<h1>All owners</h1>");
        for (Owner owner: allOwners) {
            out.println("<h1>" + owner + "</h1>");
        }
    }
}