package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        PrintWriter out = response.getWriter();
        List<String> companies = getCompanies();
        String query = request.getQueryString();
        String param = request.getParameter("search");

        if (query == null) {
            for (String company : companies) {
                out.println(company);
            }

        } else {
            List<String> filteredCompanies = companies.stream()
                    .filter(x -> x.contains(param))
                    .toList();
            if (param == null || filteredCompanies.isEmpty()) {
                out.println("Companies not found");
            }
            for (String company : filteredCompanies) {
                out.println(company);
            }
        }
        // END
    }
}
