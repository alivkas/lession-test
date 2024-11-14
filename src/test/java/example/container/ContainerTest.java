package example.container;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тест класса Container
 */
public class ContainerTest {

    private Container container;
    private Item item1;
    private Item item2;

    /**
     * Проинициализировать объекты для тестов
     */
    @BeforeEach
    public void setUp() {
        container = new Container();
        item1 = new Item(1L);
        item2 = new Item(2L);
    }

    /**
     * Проверить добавление элемента в хранилище
     */
    @Test
    public void testAddItem() {
        container.add(item1);
        container.add(item2);

        Assertions.assertEquals(2, container.size());
        Assertions.assertEquals(item1, container.get(0));
    }

    /**
     * Проверить удаление элемента из хранилища
     */
    @Test
    public void testRemoveItem() {
        container.add(item1);
        container.add(item2);
        container.remove(item1);

        Assertions.assertEquals(1, container.size());
        Assertions.assertFalse(container.contains(item1));
    }
}
