package ukma.fi.scheduler.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MyErrorController implements ErrorController {

    @GetMapping("/noAccess")
    public void doError() throws Exception {
        throw new AccessException("You do not have permission to access the requested resource");
    }

}
