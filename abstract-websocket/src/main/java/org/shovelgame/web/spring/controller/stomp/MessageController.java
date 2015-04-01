package org.shovelgame.web.spring.controller.stomp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

	@MessageMapping("/notify" )
    @SendTo("/topic/push")
    public Result test(Message message) throws Exception {
        Result result = new Result(message.getValue()); 
        return result;
    }

}
