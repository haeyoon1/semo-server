package arom_semo.server.login.controller;

import arom_semo.server.login.dto.JoinDto;
import arom_semo.server.login.service.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String JoinProcess(JoinDto joinDto){
        joinService.joinProcess(joinDto);
        return "ok"; //회원가입이 되든 안되든 ok
    }

}
