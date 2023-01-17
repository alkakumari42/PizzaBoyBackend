package com.pizzaboy.backend.controller;

import com.pizzaboy.backend.model.Item;
import com.pizzaboy.backend.repository.ItemDynamoDBRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    @Autowired
    private final ItemDynamoDBRepository itemDynamoDBRepository;

    @PostMapping("/modify")
    public void modifyMenu(@RequestBody List<Item> items) {
        itemDynamoDBRepository.updateMenu(items);
    }

    @GetMapping
    public List<Item> getMenu() {
        return itemDynamoDBRepository.getMenu();
    }

    @GetMapping("/id")
    public Item getItem(String id) {
        return itemDynamoDBRepository.findByItemId(id);
    }
}
