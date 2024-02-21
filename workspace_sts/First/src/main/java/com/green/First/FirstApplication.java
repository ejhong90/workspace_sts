package com.green.First;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstApplication {
// 실행은 메인메소드에서 무조건! 웹개발이건 뭐건 첫실행은 메인메소드입니다.
	public static void main(String[] args) {
		SpringApplication.run(FirstApplication.class, args);

	}

}
