package com.ui.service;

import com.ui.model.Item;
import com.ui.repository.ItemRepository;
import com.ui.result.PaginatedResult;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemService {

    public ItemService(ItemRepository itemRepository){
        this.itemRepository= itemRepository;
    }

    private ItemRepository itemRepository;

    public PaginatedResult getPaginatedItems(int page, int size) {
        int offset = (page - 1) * size;
        List<Item> items = itemRepository.getPaginatedItems(offset, size);
        int totalItems = itemRepository.getTotalItemsCount();
        int totalPages = (int) Math.ceil((double) totalItems / size);

        return new PaginatedResult(items, totalItems, totalPages, page, size);
    }
}
