package com.EazyBuy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/")
	public String index() {

	
		return "<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Welcome to ShopWaveFusion Backend</title>\r\n"
				+ "    <style>\r\n"
				+ "        body {\r\n"
				+ "            font-family: Arial, sans-serif;\r\n"
				+ "            text-align: center;\r\n"
				+ "            padding: 50px;\r\n"
				+ "        }\r\n"
				+ "        \r\n"
				+ "        h1 {\r\n"
				+ "            color: #007BFF;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        p {\r\n"
				+ "            font-size: 18px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        a {\r\n"
				+ "            color: #007BFF;\r\n"
				+ "            text-decoration: none;\r\n"
				+ "            font-weight: bold;\r\n"
				+ "        }\r\n"
				+ "    </style>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <h1>Welcome to ShopWaveFusion Backend</h1>\r\n"
				+ "    <p>\r\n"
				+ "        Thank you for using the ShopWaveFusion backend application. To learn more about how this application works,\r\n"
				+ "        please visit the official GitHub repository:\r\n"
				+ "    </p>\r\n"
				+ "    <p>\r\n"
				+ "        <a href=\"https://github.com/DEEPAKKUMARMAHASETH/shopwavefusionbackend\" target=\"_blank\">ShopWaveFusion Backend Repository</a>\r\n"
				+ "    </p>\r\n"
				+ "</body>\r\n"
				+ "</html>\r\n"
				+ "";
	}

	

}
