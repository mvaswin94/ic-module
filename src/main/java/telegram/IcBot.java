package mipl.icmodule.telegram;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.vdurmont.emoji.EmojiParser;

import mipl.icmodule.cover.Cover;
import mipl.icmodule.cover.CoverRepository;

@Component
public class IcBot extends TelegramLongPollingBot {

	@Autowired
	CoverRepository coverRepo;

	public void onUpdateReceived(Update update) {
		String thumbsUp = EmojiParser.parseToUnicode(":thumbsup:");

		String command = update.getMessage().getText();
		SendMessage message = new SendMessage();
		message.setChatId(update.getMessage().getChatId());
		if (command.startsWith("/close")) {
			String ic = command.replace("/close", "").trim();
			if (ic.length() < 1) {
				message.setText("Ic Number Missing.\r\n /close <COVERNUMBER>");
			} else {
				Long lpic = 0l;
				try {
					lpic = Long.parseLong(ic);
				}catch (Exception e) {
					e.printStackTrace();
					
				}
				Optional<Cover> cover = coverRepo.findById(lpic);
				if(!cover.isPresent()) {
					message.setText("Invalid Cover Number!");
				}else {
					Cover c1 = cover.get();
					c1.setStatus((byte) 9);
					coverRepo.save(c1);
					message.setText("Cover " + ic + " Closed Successfully " + thumbsUp);
				}
				
			}
		}

		try {
			execute(message);
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "test_ic_module_bot";
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "898992785:AAGfIa7EwPNepdeswxFPWvdTVvgzQYUnewk";
	}

	public void sampleFunction(Update update) {
		String firstname = update.getMessage().getFrom().getFirstName();
		String thumbsUp = EmojiParser.parseToUnicode(":thumbsup:");
		String command = EmojiParser.parseToUnicode(update.getMessage().getText());

		SendMessage message = new SendMessage();
		message.setChatId(update.getMessage().getChatId());
//		if(command.equals("/name")) {
//			message.setText(firstname);
//		}
//		if(command.equals("ߑ")) {
//			message.setText("Thumbs Up ߑ");
//		}
//		else {
//			message.setText(command + " "+":thumbsup:");
//		}
		if (command.startsWith(thumbsUp)) {
			message.setText("IC");
		} else {
			message.setText(thumbsUp);
		}
		message.setText(thumbsUp);

		try {
			execute(message);
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
