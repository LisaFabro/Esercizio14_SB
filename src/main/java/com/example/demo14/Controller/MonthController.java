package com.example.demo14.Controller;

import com.example.demo14.Entity.Month;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/months")
public class MonthController {

    //La lista di mesi sta nell'interceptor!
    //Su postman per ottenere il mese selezionare HEADERS e aggiungere monthNumber e il valore del mese che voglio
    @GetMapping("")
    public Month getMonthByNumber(HttpServletRequest request){
        Month month = (Month) request.getAttribute("month");
        return month;
    }
}
