package com.pizzaboy.backend.controller;

import com.pizzaboy.backend.model.Item;
import com.pizzaboy.backend.repository.ItemDynamoDBRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MenuControllerTest {
    ItemDynamoDBRepository itemDynamoDBRepository;
    MenuController menuControllerTest;

    @BeforeEach
    public void setup() {
        itemDynamoDBRepository = mock(ItemDynamoDBRepository.class);
        menuControllerTest = new MenuController(itemDynamoDBRepository);
    }

    @Test
    public void modifyMenuTest_success() {
        List<Item> items = this.createItemList();
        menuControllerTest.modifyMenu(items);
        verify(itemDynamoDBRepository).updateMenu(items);
    }

    @Test
    public void modifyMenuTest_failure() {
        List<Item> items = this.createItemList();
        doThrow(new RuntimeException()).when(itemDynamoDBRepository).updateMenu(items);

        Assertions.assertThrows(RuntimeException.class,
                () -> menuControllerTest.modifyMenu(items),
                "Expected exception not thrown");

        verify(itemDynamoDBRepository).updateMenu(items);
    }

    @Test
    public void getMenuTest_failure() {
        doThrow(new RuntimeException()).when(itemDynamoDBRepository).getMenu();
        Assertions.assertThrows(RuntimeException.class,
                () -> menuControllerTest.getMenu(),
                "Expected exception not thrown");
        verify(itemDynamoDBRepository).getMenu();
    }

    @Test
    public void getMenuTest_success() {
        List<Item> expectedItems = this.createItemList();
        List<Item> actualItems = menuControllerTest.getMenu();
        Assertions.assertEquals(expectedItems, actualItems);
        verify(itemDynamoDBRepository).getMenu();
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
