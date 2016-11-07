package com.webonise.springsockets;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public ReverseString greeting(Message message) throws Exception {
        String name = message.getName();
        char[] tempArray = name.toCharArray();
        int left = 0;
        int right = name.length() - 1;

        for (left = 0; left < right; left++, right--) {
            char temp = tempArray[left];
            tempArray[left] = tempArray[right];
            tempArray[right] = temp;

        }

        name = new String(tempArray);
        message.setName(name);


        return new ReverseString("The reverse of this String is " + message.getName());
    }

}