package ru.practicum.shareit.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByOwnerId(Long userId);

    @Query("""
            select i from Item i
            where (upper(i.name) like upper(concat('%', :text, '%'))
            or upper(i.description) like upper(concat('%', :text, '%')))
            and i.available = true
            """)
    List<Item> search(String text);

    Collection<Item> findAllByRequestId(Long requestId);
}
