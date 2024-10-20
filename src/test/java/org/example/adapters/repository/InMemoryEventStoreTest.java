package org.example.adapters.repository;//package org.example.adapters.repository;
//
//import org.example.domain.events.BookCreateEvent;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class InMemoryEventStoreTest {
//
//    private InMemoryEventStore eventStore;
//
//    @BeforeEach
//    void setUp() {
//        eventStore = new InMemoryEventStore();
//    }
//
//    @Test
//    void shouldSaveAndRetrieveEvents() {
//        // Given
//        Long bookId = 1L;
//        BookCreateEvent event = new BookCreateEvent(bookId);
//
//        // When
//        eventStore.saveEvent(event);
//        List<Object> events = eventStore.getEventsForAggregate(bookId);
//
//        // Then
//        assertEquals(1, events.size());
//        assertEquals(bookId, ((BookCreateEvent) events.get(0)).getBookId());
//    }
//}
