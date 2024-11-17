package example.container;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Тест класса Container
 */
public class ContainerTest {

    private final Container container = new Container();
    private final Item item1 = new Item(1L);
    private final Item item2 = new Item(2L);

    /**
     * Проверить добавление элемента в хранилище
     */
    @Test
    public void testAddItem() {
        container.add(item1);
        Assertions.assertEquals(1, container.size());

        container.add(item2);
        Assertions.assertEquals(2, container.size());
        Assertions.assertTrue(container.contains(item1));
        Assertions.assertTrue(container.contains(item2));
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
        Assertions.assertTrue(container.contains(item2));
    }
}
