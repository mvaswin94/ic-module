package mipl.icmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import mipl.icmodule.telegram.IcBot;

@SpringBootApplication
@EnableScheduling
public class IcModulePortalApplication {

	static {
		//Telegram Bot library Start
		ApiContextInitializer.init();
	}

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(IcModulePortalApplication.class, args);
		IcBot icbot = applicationContext.getBean(IcBot.class);
		 /*registerWithTelegram(icbot); */
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public static void registerWithTelegram(IcBot icbot) {
		// ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(icbot);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
