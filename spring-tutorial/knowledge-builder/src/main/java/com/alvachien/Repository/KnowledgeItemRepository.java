package com.alvachien.Repository;

import com.alvachien.Model.KnowledgeItem;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface KnowledgeItemRepository extends JpaRepository<KnowledgeItem, Long> {
  
}


