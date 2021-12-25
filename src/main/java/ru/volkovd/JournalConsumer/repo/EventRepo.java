package ru.volkovd.JournalConsumer.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.volkovd.JournalConsumer.model.Event;

public interface EventRepo extends ElasticsearchRepository<Event, String> {
    Page<Event> findByText(String name, Pageable pageable);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
    Page<Event> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable);
}
