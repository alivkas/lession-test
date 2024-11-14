package example.bot;

/**
 * Тестовая реализация интерфейса Bot
 */
public class BotImpl implements Bot {

    private String message;

    /**
     * Отправка сообщения пользователю
     *
     * @param chatId идентификатор чата
     * @param message текст сообщения
     */
    @Override
    public void sendMessage(Long chatId, String message) {
        this.message = message;
    }

    /**
     * Получить сообщение
     * @return сообщение
     */
    public String getMessage() {
        return message;
    }
}
