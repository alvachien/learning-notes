package com.alvachien.Controller;

import java.util.List;

import com.alvachien.Model.KnowledgeItem;
import com.alvachien.Repository.KnowledgeItemRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class KnowledgeItemController {
    private final KnowledgeItemRepository repository = null;

    @GetMapping("/knowledgeitems")
    public List<KnowledgeItem> getStudents() {
        return repository.findAll();
    }    
}
