package org.example.domain.services;//package org.example.domain.services;
//
//import org.example.domain.events.BookCreateEvent;
//import org.example.domain.model.Book;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class BookQueryServiceImplTest {
//
//    private BookQueryServiceImpl bookQueryService;
//    private EventStore eventStore;
//
//    @BeforeEach
//    void setUp() {
//        // Mock the EventStore dependency
//        eventStore = Mockito.mock(EventStore.class);
//
//        // Initialize the service with the mocked event store
//        bookQueryService = new BookQueryServiceImpl(eventStore);
//    }
//
//    @Test
//    void shouldReconstructBookFromEvents() {
//        // Given: Events exist for a book with ID 1
//        Long bookId = 1L;
//        List<Object> events = new ArrayList<>();
//        events.add(new BookCreateEvent(bookId));
//
//        when(eventStore.getEventsForAggregate(bookId)).thenReturn(events);
//
//        // When: We retrieve the book from the query service
//        Book book = bookQueryService.getBook(bookId);
//
//        // Then: The book should be reconstructed correctly
//        assertNotNull(book);
//        assertEquals(bookId, book.getId());
//        assertNull(book.getTitle()); // Since only BookCreatedEvent sets the ID, no title is set in this test case
//        assertNull(book.getAuthor());
//
//        // Verify that event store's getEventsForAggregate method was called exactly once
//        verify(eventStore, times(1)).getEventsForAggregate(bookId);
//    }
//
//    @Test
//    void shouldReturnEmptyBookWhenNoEventsFound() {
//        // Given: No events exist for a book with ID 2
//        Long bookId = 2L;
//        when(eventStore.getEventsForAggregate(bookId)).thenReturn(Collections.emptyList());
//
//        // When: We retrieve the book from the query service
//        Book book = bookQueryService.getBook(bookId);
//
//        // Then: The book should not have an ID (no events to apply)
//        assertNull(book.getId());
//
//        // Verify that event store's getEventsForAggregate method was called exactly once
//        verify(eventStore, times(1)).getEventsForAggregate(bookId);
//    }
//}