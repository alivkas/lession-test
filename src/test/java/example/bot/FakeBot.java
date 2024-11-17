package example.bot;

import java.util.ArrayList;
import java.util.List;

/**
 * Фейковый бот, для сохранения сообщений, которые отправил класс {@link BotLogic}
 */
public class FakeBot implements Bot {

    private final List<String> messages = new ArrayList<>();

    @Override
    public void sendMessage(Long chatId, String message) {
        messages.add(message);
    }

    /**
     * Получить список сообщений
     * @return список сообщений, отправленных классом BotLogic
     */
    public List<String> getMessages() {
        return messages;
    }

    /**
     * Получить последнее сообщение
     * @return последнее сообщение из списка
     */
    public String getLastMessage() {
        return messages.getLast();
    }
}
