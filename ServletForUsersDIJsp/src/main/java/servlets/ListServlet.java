package servlets;

import dao.CarsAndUsersDao;
import factories.ServiceFactory;
import models.Car;
import models.CarUser;
import models.User;
import service.CarsAndUsersService;
import service.CarsService;
import service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListServlet extends HttpServlet {

    private UsersService usersService;
    private CarsService carsService;
    private CarsAndUsersService carsAndUsersService;

    @Override
    public void init() throws ServletException {
        try {
            super.init();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        usersService = ServiceFactory.getInstance().getUsersService();
        carsService = ServiceFactory.getInstance().getCarsService();
        carsAndUsersService = ServiceFactory.getInstance().getCarsAndUsersDao();
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        List<User> users = usersService.getAllUser();
        List<Car> cars = carsService.getAllCar();
        List<CarUser> idCarsAndUsers = carsAndUsersService.getAll();

        request.setAttribute("usersJsp", users);
        request.setAttribute("carsJsp", cars);
        request.setAttribute("idCarsAndUsersJsp", idCarsAndUsers);
        getServletContext().getRequestDispatcher("/list.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

    }
}
