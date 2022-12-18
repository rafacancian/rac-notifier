package com.notifier.websites.nike.brazilshirt;

import com.notifier.sendemail.SendEmailService;
import com.notifier.websites.notifierUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class VerifyBrazilShirt {

    @Autowired
    private final SendEmailService enviaEmailService;

    public VerifyBrazilShirt(SendEmailService enviaEmailService) {
        this.enviaEmailService = enviaEmailService;
    }


    private static void sendWhatsapp() {
        // https://wa.me/5519997317554?text=Teste
        // https://api.whatsapp.com/send/?phone=5519997317554&text=Teste&type=phone_number&app_absent=1
        // span class = "Iniciar conversa"
    }

    public void run() {
        checkBrazilShitNikeWebsite();
    }

    private void checkBrazilShitNikeWebsite() {

        while (true) {
            try {
                Document document = Jsoup.connect("https://www.nike.com.br/camisa-nike-brasil-i-202223-torcedor-pro-masculina-022834.html").get();
                Elements productNotAvailable = document.getElementsContainingText("Produto Indisponível");
                if (productNotAvailable.isEmpty()) {
                    sendEmail();
                } else {
                    notifierUtils.sleepForAWhile("Product is not available");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void sendEmail() {
        enviaEmailService.send("cancian.rafa@gmail.com",
                "RAC system inform - your product is available",
                "The Brazil 2022/23 Men's Nike Soccer Jersey is available, run to pick it up!" +
                        "\n\n https://www.nike.com.br/camisa-nike-brasil-i-202223-torcedor-pro-masculina-022834.html");
    }

}