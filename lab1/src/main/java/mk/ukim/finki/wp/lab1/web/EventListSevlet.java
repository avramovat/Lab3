package mk.ukim.finki.wp.lab1.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab1.model.Event;
import mk.ukim.finki.wp.lab1.service.impl.EventServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(name="EventListServlet", urlPatterns = {"/events/events-list"})
public class EventListSevlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final EventServiceImpl eventService;

    public EventListSevlet(SpringTemplateEngine springTemplateEngine, EventServiceImpl eventService) {
        this.springTemplateEngine = springTemplateEngine;
        this.eventService = eventService;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Event> eventList;
        String searchName = req.getParameter("searchName");
        String minRating = req.getParameter("minRating");

        if (searchName != null && minRating != null && !Objects.equals(minRating, "")) {
            eventList = eventService.searchEvents(searchName).stream()
                    .filter(event -> event.getPopularityScore() >= Double.parseDouble(minRating))
                    .toList();
        } else if (minRating != null && !Objects.equals(minRating, "")) {
            eventList = eventService.listAll().stream()
                    .filter(event -> event.getPopularityScore() >= Double.parseDouble(minRating))
                    .toList();
        } else if (searchName != null) {
            eventList = eventService.searchEvents(searchName);
        } else {
            eventList = eventService.listAll();
        }

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("events", eventList);
        springTemplateEngine.process("listEvents.html", context, resp.getWriter());

    }
}
