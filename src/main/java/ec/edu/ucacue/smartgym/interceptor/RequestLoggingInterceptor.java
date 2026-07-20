package ec.edu.ucacue.smartgym.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class RequestLoggingInterceptor implements HandlerInterceptor {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        long startTime = (Long) request.getAttribute("startTime");
        long duration = System.currentTimeMillis() - startTime;

        int status = response.getStatus();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String time = LocalTime.now().format(TIME_FORMATTER);

        String color;
        if (status >= 500) {
            color = "\033[31m";
        } else if (status >= 400) {
            color = "\033[33m";
        } else if (status >= 200 && status < 300) {
            color = "\033[32m";
        } else {
            color = "\033[0m";
        }

        log.info("{}RLI [{}] {} - Status: {} - {} ({}ms)\033[0m", color, method, uri, status, time, duration);
    }
}
