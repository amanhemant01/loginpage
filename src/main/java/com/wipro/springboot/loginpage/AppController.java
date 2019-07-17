package com.wipro.springboot.loginpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
		@RequestMapping("/")
		public String welcome()
		{
			System.out.println("AppController--->Welcome");
			return "index";
		}
}
@Controller
class App {
		@RequestMapping("/action")
		public String action()
		{
			System.out.println("AppController--->action");
			return "action";
		}
		

}
