package com.pizzaboy.backend.repository;

import com.pizzaboy.backend.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;


public class ItemDynamoDBRepositoryTest {
    @InjectMocks
    ItemDynamoDBRepository itemDynamoDBRepository;
    @Mock
    ItemRepository itemRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void updateMenuTest_success() {
        List<Item> items = createItemList();
        itemDynamoDBRepository.updateMenu(items);
        verify(itemRepository).deleteAll();
        verify(itemRepository).saveAll(items);
    }

    @Test
    public void updateMenuTest_deleteAll_failure() {
        List<Item> items = createItemList();
        Mockito.doThrow(RuntimeException.class).when(itemRepository).deleteAll();
        Assertions.assertThrows(RuntimeException.class,
                () -> itemDynamoDBRepository.updateMenu(items));
        verify(itemRepository).deleteAll();
        verify(itemRepository, never()).saveAll(items);
    }

    @Test
    public void updateMenuTest_saveAll_failure() {
        List<Item> items = createItemList();
        Mockito.doThrow(RuntimeException.class).when(itemRepository).saveAll(items);
        Assertions.assertThrows(RuntimeException.class,
                () -> itemDynamoDBRepository.updateMenu(items));
        verify(itemRepository).deleteAll();
        verify(itemRepository).saveAll(items);
    }

    @Test
    public void getMenuTest_success() {
        List<Item> items = createItemList();
        when(itemRepository.findAll()).thenReturn(items);
        List<Item> actualItems = itemDynamoDBRepository.getMenu();
        Assertions.assertEquals(items, actualItems);
        verify(itemRepository).findAll();
    }

    @Test
    public void getMenuTest_findAll_failure() {
        Mockito.doThrow(RuntimeException.class).when(itemRepository).findAll();
        Assertions.assertThrows(RuntimeException.class,
                () -> itemDynamoDBRepository.getMenu());
    }

    private List<Item> createItemList() {
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setId("4");
        item1.setName("Samosa");
        item1.setPrice(10);
        Item item2 = new Item();
        item2.setId("5");
        item2.setName("pastry");
        item2.setPrice(6);
        Item item3 = new Item();
        item3.setId("6");
        item3.setName("panipuri");
        item3.setPrice(9);
        return items;
    }
}
