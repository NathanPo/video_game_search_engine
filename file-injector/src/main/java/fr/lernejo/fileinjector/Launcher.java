package fr.lernejo.fileinjector;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Launcher {

    public static void main(String[] args) {
        try (AbstractApplicationContext springContext = new AnnotationConfigApplicationContext(Launcher.class)) {
            if (args.length > 0) {
                ObjectMapper map = new ObjectMapper();
                List<Game> games = Arrays.asList(map.readValue(Paths.get(args[0]).toFile(), Game[].class));
                RabbitTemplate tmp = springContext.getBean(RabbitTemplate.class);
                generateMessage(games, tmp);
            }
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateMessage(List<Game> games, RabbitTemplate template) {
        for (Game game : games) {
            template.setMessageConverter(new Jackson2JsonMessageConverter());
            template.convertAndSend("", "game_info", game, msg -> {
                msg.getMessageProperties().getHeaders().put("game_id", game.id);
                return msg;
            });
        }
    }
}
