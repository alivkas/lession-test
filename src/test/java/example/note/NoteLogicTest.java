package example.note;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тест класса NoteLogic
 */
public class NoteLogicTest {

    private NoteLogic noteLogic;

    /**
     * Проинициализировать объекты для тестов
     */
    @BeforeEach
    public void setUp() {
        noteLogic = new NoteLogic();
    }

    /**
     * Тестировать добавление заметок в хранилище и их просмотр
     */
    @Test
    public void testHandleAllCommands() {
        String response = noteLogic.handleMessage("/add New note");
        Assertions.assertEquals("Note added!", response);
        Assertions.assertEquals("Your notes:\nNew note", noteLogic.handleMessage("/notes"));
    }

    /**
     * Тестировать изменение заметки
     */
    @Test
    public void testHandleEdit() {
        String response = noteLogic.handleMessage("/edit New note | Old note");
        Assertions.assertEquals("Note edited!", response);
        Assertions.assertEquals("Your notes:\nOld note", noteLogic.handleMessage("/notes"));
    }

    /**
     * Тестировать удаление заметки из хранилища
     */
    @Test
    public void testHandleDel() {
        String response = noteLogic.handleMessage("/del Old note");
        Assertions.assertEquals("Note deleted!", response);
        Assertions.assertEquals("Your notes:\n", noteLogic.handleMessage("/notes"));
    }

    /**
     * Тестировать неправильно введенную команду
     */
    @Test
    public void testHandleUnknownCommand() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            noteLogic.handleMessage("/random");
        });
    }
}
