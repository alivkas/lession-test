package example.note;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Тест класса NoteLogic
 */
public class NoteLogicTest {

    private final NoteLogic noteLogic = new NoteLogic();

    /**
     * Тестировать добавление заметок в хранилище и
     * их просмотр командами /note и /add
     */
    @Test
    public void testHandleAllCommands() {
        String response = noteLogic.handleMessage("/add New note");
        Assertions.assertEquals("Note added!", response);
        Assertions.assertEquals("Your notes:\nNew note", noteLogic.handleMessage("/notes"));
    }

    /**
     * Тестировать изменение заметки командой /edit
     */
    @Test
    public void testHandleEdit() {
        noteLogic.handleMessage("/add New note");
        String response = noteLogic.handleMessage("/edit New note | Old note");
        Assertions.assertEquals("Note edited!", response);
        Assertions.assertEquals("Your notes:\nOld note", noteLogic.handleMessage("/notes"));
    }

    /**
     * Тестировать удаление заметки командой /del
     */
    @Test
    public void testHandleDel() {
        noteLogic.handleMessage("/add note1");
        noteLogic.handleMessage("/add note2");

        String response = noteLogic.handleMessage("/del note1");
        Assertions.assertEquals("Note deleted!", response);
        Assertions.assertEquals("Your notes:\nnote2", noteLogic.handleMessage("/notes"));
    }

    /**
     * Тестировать получение ошибки при введении неправильной команды
     */
    @Test
    public void testIllegalArgumentException() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> noteLogic.handleMessage("/random"));
        Assertions.assertEquals("Неверная команда", exception.getMessage());
    }
}
