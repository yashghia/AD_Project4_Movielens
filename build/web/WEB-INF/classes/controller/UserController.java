package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Database.DBUtil;
import Database.Emailserver;
import Database.RecommendDB;
import Database.ResetDB;
import Database.StudyDB;
import Database.TempUserDB;
import Database.UserDB;
import java.net.InetAddress;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabeans.Recommend;
import javabeans.Reset;
import javabeans.TempUser;
import javabeans.User;
import util.Hashpassword;

@WebServlet("/UserController")
public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response) responsible for navigating to the respective pages.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession();

        // get current action
        String url = "/home.jsp";
        String action = request.getParameter("action");
        // if action is null goes to home.jsp

        if (action == null || action.equals("") || action.isEmpty()) {
            url = "/home.jsp";
            InetAddress local = InetAddress.getLocalHost();
            Cookie c = new Cookie("host", local.toString());
            Cookie c1 = new Cookie("port", request.getServerPort() + "");
            c.setMaxAge(60 * 60 * 24 * 365 * 10);
            c1.setMaxAge(60 * 60 * 24 * 365 * 10);
            response.addCookie(c);
            response.addCookie(c1);
            Cookie[] cookies = request.getCookies();
            if (cookies == null) {
                response.setIntHeader("Refresh", 1);
            }

        } // perform login action and if credentials are right takes him to the main.jsp or admin.jsp based on usertype
        else if (action.equals("login")) {
            // get parameters from the request
            String email = request.getParameter("email");
            String pwd = request.getParameter("password");
            // UserDB.getUsers();
            String salt = UserDB.validateUser(email);
            if (salt != null) {
                if (UserDB.selectUser(email, pwd, salt)) {
                    User user = UserDB.getUser(email);
                    String userType = user.getUserType();
                    if (userType.equalsIgnoreCase("Participant")) {
                        session.setAttribute("theUser", user);
                        int participants = StudyDB.getParticipants(user.getEmail());
                        session.setAttribute("par", participants);
                        url = "/main.jsp";
                    } else if (userType.equalsIgnoreCase("Admin")) {
                        session.setAttribute("theAdmin", user);
                        url = "/admin.jsp";
                    }
                } else {
                    request.setAttribute("msg", "Incorrect Username or Password.Please try again.");
                    url = "/login.jsp";
                }
            } else {
                request.setAttribute("msg", "Incorrect Username or Password.Please try again.");
                url = "/login.jsp";
            }
        } // creates a new user
        else if (action.equals("create")) {
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String cPassword = request.getParameter("confirm_password");
            String token=request.getParameter("token");
            if (UserDB.getUser(email) != null) {
                request.setAttribute("msg", "The Email id is already registerd");
                request.setAttribute("email", email);
                request.setAttribute("name", name);
                url = "/signup.jsp";
            } else if (!password.equals(cPassword)) {
                request.setAttribute("msg", "Incorrect Passwords dont match.");
                request.setAttribute("email", email);
                request.setAttribute("name", name);
                url = "/signup.jsp";
            } else {
                /*User user = new User(name, email, "Participant", password, cPassword, 0, 0, 0);
                     int participants = StudyDB.getParticipants(user.getEmail());
                     session.setAttribute("par", participants);
                     UserDB.addUser(user);
                     session.setAttribute("theUser", user);
                     url = "/main.jsp";*/
                TempUser tempuser = new TempUser();
                if(token=="") token = UUID.randomUUID().toString();
                //tempuser.setDate(new Date());
                tempuser.setEmail(email);
                String salt = Hashpassword.getSalt();
                password = Hashpassword.hashAndSaltPassword(password, salt);
                tempuser.setPassword(password);
                tempuser.setToken(token);
                tempuser.setSalt(salt);
                tempuser.setName(name);
                TempUserDB tempuserDB = new TempUserDB();
                tempuserDB.addTempUser(tempuser);
                String link = request.getRequestURL().toString();
                //System.out.println(link);
                Emailserver.activateUser(name, email, token, link);
                request.setAttribute("msg", "An activation email has been sent to the user");
                url = "/login.jsp";
            }

        } else if (action.equals("activate")) {
            String token = request.getParameter("token");
            System.out.println(token);
            if (TempUserDB.getTempUser(token) == null) {
                String msg = "Invalid Link";
                request.setAttribute("msg", msg);
                url = "/signup.jsp";
            } else {
                TempUser tempuser = TempUserDB.getTempUser(token);
                Recommend rec= RecommendDB.getUser(token);
                    if(rec!=null) System.out.println(rec.getEmail() + "  " + rec.getToken());
                    if(RecommendDB.getUser(token)!=null) {
                        rec= RecommendDB.getUser(token);
                        String userEmail=rec.getEmail();
                        User user=UserDB.getUser(userEmail);
                        System.out.println("coins " + user.getNumCoins());
                        UserDB.updateUserCoins(userEmail, user.getNumCoins()+2);
                    }
                //User participant = new User();
                String name = tempuser.getName();
                String email = tempuser.getEmail();
                String password = tempuser.getPassword();
                String salt = tempuser.getSalt();
                System.out.println(password);
                User user = new User(name, email, "Participant", password, salt, 0, 0, 0, false);
                int participants = StudyDB.getParticipants(user.getEmail());
                session.setAttribute("par", participants);
                UserDB.addUser(user);
                TempUserDB.deleteUser(email);
                request.setAttribute("msg", "User added. Please login!!");
                url = "/login.jsp";

            }

        } else if (action.equals("forgot")) {
            String email = request.getParameter("email");
            if (UserDB.getUser(email) != null) {
                User user = UserDB.getUser(email);
                String token = UUID.randomUUID().toString();
                String link = request.getRequestURL().toString();
                Emailserver.resetPass(user.getName(), email, token, link);
                Reset reset = new Reset(email, token, new Date());
                request.setAttribute("msg", "An email has been sent to the user to reset the password");
                ResetDB.addUser(reset);
            } else {
                request.setAttribute("msg", "Invalid username");
                url = "/login.jsp";
            }
        } else if (action.equals("reset")) {
            String token = request.getParameter("token");
            System.out.println(token);
            if (ResetDB.getUser(token) == null) {
                String msg = "Invalid Link";
                request.setAttribute("msg", msg);
                url = "/login.jsp";
            } else {
                Reset reset = ResetDB.getUser(token);
                String email = reset.getEmail();
                session.setAttribute("email", email);
                url = "/updatepass.jsp";

            }

        } else if (action.equals("updatepass")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String cPassword = request.getParameter("confirm_password");
            if (!password.equals(cPassword)) {
                request.setAttribute("msg", "Incorrect Passwords dont match.");
                url = "/updatepass.jsp";
            } else {
                User user=UserDB.getUser(email);
                UserDB.updatePassword(email, password,user.getSalt());
                ResetDB.deleteUser(email);
                request.setAttribute("msg", "Password updated successfully.Please Login!!");
                url = "/login.jsp";
            }
        } else if (action.equals("how")) {
            if (session.getAttribute("theUser") != null || session.getAttribute("theAdmin") != null) {
                url = "/main.jsp";
            } else {
                url = "/how.jsp";
            }
        } else if (action.equals("about")) {
            if (session.getAttribute("theUser") != null || session.getAttribute("theAdmin") != null) {
                url = "/aboutl.jsp";
            } else {
                url = "/about.jsp";
            }
        } else if (action.equals("home")) {
            if (session.getAttribute("theUser") != null || session.getAttribute("theAdmin") != null) {
                url = "/main.jsp";
            } else {
                url = "/home.jsp";
            }
        } else if (action.equals("main")) {
            if (session.getAttribute("theUser") != null || session.getAttribute("theAdmin") != null) {
                url = "/main.jsp";
            } else {
                url = "/login.jsp";
            }
        } else if (action.equals("logout")) {
            if (session.getAttribute("theUser") != null || session.getAttribute("theAdmin") != null) {
                session.invalidate();
            }
            url = "/home.jsp";
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response) calls the doget() method
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
