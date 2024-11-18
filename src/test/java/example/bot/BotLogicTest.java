package example.bot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Тест класса BotLogicTest
 */
class BotLogicTest {

    private final User user = new User(0L);

    /**
     * Тестировать правильные ответы команды в /test
     */
    @Test
    public void testCorrectAnswersTestCommand() {
        FakeBot bot = new FakeBot();
        BotLogic botLogic = new BotLogic(bot);

        botLogic.processCommand(user, "/test");
        Assertions.assertEquals("Вычислите степень: 10^2", bot.getMessages().get(0));
        botLogic.processCommand(user, "100");
        Assertions.assertEquals("Правильный ответ!", bot.getMessages().get(1));
        Assertions.assertEquals("Сколько будет 2 + 2 * 2", bot.getMessages().get(2));
        botLogic.processCommand(user, "6");
        Assertions.assertEquals("Правильный ответ!", bot.getMessages().get(3));
        Assertions.assertEquals("Тест завершен", bot.getMessages().get(4));
    }

    /**
     * Тестировать неправильные ответы в /test
     */
    @Test
    public void testWrongAnswersTestCommand() {
        FakeBot bot = new FakeBot();
        BotLogic botLogic = new BotLogic(bot);

        botLogic.processCommand(user, "/test");
        botLogic.processCommand(user, "1");
        Assertions.assertEquals("Вы ошиблись, верный ответ: 100", bot.getMessages().get(1));
        botLogic.processCommand(user, "2");
        Assertions.assertEquals("Вы ошиблись, верный ответ: 6", bot.getMessages().get(3));
    }

    /**
     * Тестировать команду /notify
     */
    @Test
    public void testNotifyCommand() throws InterruptedException {
        FakeBot bot = new FakeBot();
        BotLogic botLogic = new BotLogic(bot);

        botLogic.processCommand(user, "/notify");
        Assertions.assertEquals("Введите текст напоминания", bot.getMessages().get(0));
        botLogic.processCommand(user, "notification text");
        Assertions.assertEquals("Через сколько секунд напомнить?", bot.getMessages().get(1));
        botLogic.processCommand(user, "1");
        Assertions.assertEquals("Напоминание установлено", bot.getMessages().get(2));

        Assertions.assertEquals(3, bot.getMessages().size());

        Thread.sleep(1010);
        Assertions.assertEquals("Сработало напоминание: 'notification text'", bot.getMessages().get(3));
    }

    /**
     * Тестировать команду /repeat. Проверить случаи двух ошибок в /test
     * и исправление одной в /repeat.
     * Проверить при повторном вызов /repeat наличие ошибки, которую не исправили
     * и убедиться, что /repeat не записал в себя правильный ответ
     */
    @Test
    public void testRepeatCommand() {
        FakeBot bot = new FakeBot();
        BotLogic botLogic = new BotLogic(bot);

        botLogic.processCommand(user, "/test");
        Assertions.assertEquals("Вычислите степень: 10^2", bot.getMessages().get(0));
        botLogic.processCommand(user, "1");
        Assertions.assertEquals("Сколько будет 2 + 2 * 2", bot.getMessages().get(2));
        botLogic.processCommand(user, "2");

        botLogic.processCommand(user, "/repeat");
        Assertions.assertEquals("Вычислите степень: 10^2", bot.getMessages().get(5));
        botLogic.processCommand(user, "1");
        Assertions.assertEquals("Вы ошиблись, верный ответ: 100", bot.getMessages().get(6));
        Assertions.assertEquals("Сколько будет 2 + 2 * 2", bot.getMessages().get(7));
        botLogic.processCommand(user, "6");
        Assertions.assertEquals("Правильный ответ!", bot.getMessages().get(8));
        Assertions.assertEquals("Тест завершен", bot.getMessages().get(9));

        botLogic.processCommand(user, "/repeat");
        Assertions.assertEquals("Вычислите степень: 10^2", bot.getMessages().get(10));
        botLogic.processCommand(user, "100");
        Assertions.assertEquals("Правильный ответ!", bot.getMessages().get(11));
        Assertions.assertEquals("Тест завершен", bot.getMessages().get(12));

        botLogic.processCommand(user, "/repeat");
        Assertions.assertEquals("Нет вопросов для повторения", bot.getMessages().get(13));
    }
}