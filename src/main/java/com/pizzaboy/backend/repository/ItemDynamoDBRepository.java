package com.pizzaboy.backend.repository;

import com.pizzaboy.backend.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemDynamoDBRepository {
    @Autowired
    private final ItemRepository itemRepository;
    public void updateMenu(List<Item> items){
        itemRepository.deleteAll();
        itemRepository.saveAll(items);
    }

    public List<Item> getMenu() {
        List<Item> list = new ArrayList<>();
        itemRepository.findAll().forEach(list::add);
        return list;
    }

    public Item findByItemId(String id) {
        return itemRepository.findById(id).orElse(new Item());
    }
}
