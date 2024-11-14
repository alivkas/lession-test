package example.bot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Тест класса BotLogicTest
 */
class BotLogicTest {

    private User user;
    private BotLogic botLogic;
    BotImpl bot;

    /**
     * Проинициализировать объекты для тестов
     */
    @BeforeEach
    public void setUp() {
        bot = new BotImpl();
        user = new User(0L);
        botLogic = new BotLogic(bot);
    }

    /**
     * Тестировать команду /test
     */
    @Test
    public void testTestCommand() {
        botLogic.processCommand(user, "/test");
        Assertions.assertEquals(State.TEST, user.getState());
        Assertions.assertEquals("Вычислите степень: 10^2", bot.getMessage());

        botLogic.processCommand(user, "100");
        Assertions.assertEquals("Сколько будет 2 + 2 * 2", bot.getMessage());

        botLogic.processCommand(user, "6");
        Assertions.assertEquals(0, user.getWrongAnswerQuestions().size());
        Assertions.assertEquals("Тест завершен", bot.getMessage());
    }

    /**
     * Тестировать неправильные ответы в /test
     */
    @Test
    public void testWrongAnswersTestCommand() {
        botLogic.processCommand(user, "/test");
        botLogic.processCommand(user, "1");
        botLogic.processCommand(user, "6");
        List<Question> wrongQuestions = user.getWrongAnswerQuestions();

        Assertions.assertEquals(1, wrongQuestions.size());
    }

    /**
     * Тестировать команду /notify
     */
    @Test
    public void testNotifyCommand() throws InterruptedException {
        botLogic.processCommand(user, "/notify");
        Assertions.assertEquals(State.SET_NOTIFY_TEXT, user.getState());
        Assertions.assertEquals("Введите текст напоминания", bot.getMessage());

        botLogic.processCommand(user, "notification text");
        Assertions.assertEquals(State.SET_NOTIFY_DELAY, user.getState());

        botLogic.processCommand(user, "3");
        Assertions.assertEquals("Напоминание установлено", bot.getMessage());
        Assertions.assertEquals(State.INIT, user.getState());

        Thread.sleep(3100);
        Assertions.assertEquals("Сработало напоминание: 'notification text'", bot.getMessage());
    }

    /**
     * Тестировать команду /repeat
     */
    @Test
    public void testRepeatCommand() {
        botLogic.processCommand(user, "/test");
        botLogic.processCommand(user, "1");
        botLogic.processCommand(user, "2");

        botLogic.processCommand(user, "/repeat");
        Assertions.assertEquals(State.REPEAT, user.getState());

        Assertions.assertEquals("Вычислите степень: 10^2", bot.getMessage());
        botLogic.processCommand(user, "100");
        Assertions.assertEquals("Сколько будет 2 + 2 * 2", bot.getMessage());
        botLogic.processCommand(user, "3");
        Assertions.assertEquals("Тест завершен", bot.getMessage());

        Assertions.assertEquals(1, user.getWrongAnswerQuestions().size());

        botLogic.processCommand(user, "/repeat");
        botLogic.processCommand(user, "6");

        Assertions.assertEquals(0, user.getWrongAnswerQuestions().size());
    }
}