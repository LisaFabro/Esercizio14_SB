package com.example.demo14.Interceptor;

import com.example.demo14.Entity.Month;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MonthInterceptor implements HandlerInterceptor {
    private List<Month> listedMonthsInInterceptor = addMonthsInList();

    private List<Month> addMonthsInList() {
        List<Month> fullListMonths = new ArrayList<>();
        fullListMonths.add(new Month(1, "Jan", "Gen", "Boh"));
        fullListMonths.add(new Month(2, "Feb", "Feb", "Boh"));
        fullListMonths.add(new Month(3, "Mar", "Mar", "Boh"));
        fullListMonths.add(new Month(4, "Apr", "Apr", "Boh"));
        fullListMonths.add(new Month(5, "May", "Mag", "Boh"));
        fullListMonths.add(new Month(6, "Jun", "Giu", "Boh"));
        return fullListMonths;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //takes the header monthNumber from the request
        String monthNumberString = request.getHeader("monthNumber");

        //if monthNumber is null/empty then returns an HTTP Bad Request error
        if (monthNumberString == null || monthNumberString.isEmpty()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return false;
        }

        // String to int per cercare il mese nella lista!
        Integer monthNumber = Integer.parseInt(monthNumberString);

        //if monthNumber is present in the list, returns it using a specific request attribute
        //returns an empty Month with all the string values set to nope

        Optional<Month> monthFounded = listedMonthsInInterceptor.stream()
                .filter(month -> {return month.getMonthNumber() == monthNumber;}).findFirst();

        if(monthFounded.isPresent()){
            //questo è il request attribute che torno se lo trovo!
            request.setAttribute("month", monthFounded.get());
        }else{
            //questo è l'empty month che torno se non trova il mese
            Month emptyMonth = new Month(0, "nope", "nope", "nope");
            request.setAttribute("empty month", emptyMonth);
        }
        //returns an HTTP OK status
        response.setStatus(HttpStatus.OK.value());
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
