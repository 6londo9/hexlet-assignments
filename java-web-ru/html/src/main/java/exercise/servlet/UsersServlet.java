package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        ObjectMapper objectMapper = new ObjectMapper();
        Path filePath = Paths.get("src", "main", "resources", "users.json").toAbsolutePath().normalize();
        String file = Files.readString(filePath).trim();
        return objectMapper.readValue(file, List.class);
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        StringBuilder sb = new StringBuilder();
        Map<Integer, Map<String, String>> users = getUsersAsMap(getUsers());

        sb.append("""
                <!doctype html>
                <html>
                    <head>
                        <title>Users</title>
                    </head>
                    <body>
                        <h1>Users</h1>
                        <table>
                            <tr>
                """);

        for (Map.Entry<Integer, Map<String, String>> data : users.entrySet()) {
            Map<String, String> user = data.getValue();
            sb.append("                <td>");
            sb.append(user.get("id") + "</td>\n");
            sb.append("                <td>\n");
            sb.append("                    <a href=\"/users/")
                    .append(user.get("id") + "\">" + user.get("firstName") + " " + user.get("lastName") + "</a>\n");
            sb.append("                </td>\n");
        }

        sb.append("""
                            </tr>
                        </table>
                    </body>
                </html>
                """);

        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();
        out.println(sb);
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        StringBuilder sb = new StringBuilder();
        Map<Integer, Map<String, String>> users = getUsersAsMap(getUsers());
        if (id.equals("") || users.get(Integer.parseInt(id)) == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            Map<String, String> user = users.get(Integer.parseInt(id));
            sb.append("""
                <!doctype html>
                <html>
                    <head>
                        <title>""");
            sb.append(user.get("firstName") + " " + user.get("lastName") + "</title>");
            sb.append("""
                    </head>
                    <body>
                """);
            sb.append("        <h3>" + user.get("firstName") + " " + user.get("lastName") + "</h3>\n");
            sb.append("        <p>id: " + user.get("id") + "</p>\n");
            sb.append("        <p>email: " + user.get("email") + "</p>\n");
            sb.append("""
                    </body>
                </html>
                """);

            response.setContentType("text/html;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = response.getWriter();
            out.println(sb);
        }
        // END
    }

    private Map<Integer, Map<String, String>> getUsersAsMap(List<String> usersAsList) {
        Map<Integer, Map<String, String>> users = new TreeMap<>();

        for (Object data : usersAsList) {
            Map<String, String> user = (Map<String, String>) data;
            users.put(Integer.parseInt(user.get("id")), user);
        }
        return users;
    }
}
