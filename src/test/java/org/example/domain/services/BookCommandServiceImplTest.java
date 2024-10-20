package org.example.domain.services;//package org.example.domain.services;
//
//import org.example.domain.events.BookCreateEvent;
//import org.example.domain.model.Book;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//class BookCommandServiceImplTest {
//
//    @Autowired
//    private BookCommandServiceImpl bookCommandService;
//
//    @Autowired
//    private EventStore eventStore;
//
//    @Test
//    void shouldCreateBookAndSaveEvent() {
//        // Given: A new book creation request
//        Long bookId = 1L;
//        String title = "Test Book";
//        String author = "Test Author";
//
//        // When: We create the book via the command service
//        Book bookToCreate = Book.create(bookId, title, author);
//        Book createdBook = bookCommandService.createBook(bookToCreate);
//
//        // Then: Verify that the book was created with the correct details
//        assertNotNull(createdBook);
//        assertEquals(bookId, createdBook.getId());
//        assertEquals(title, createdBook.getTitle());
//        assertEquals(author, createdBook.getAuthor());
//
//        // Check if the Book has the expected changes (BookCreatedEvent)
//        assertFalse(createdBook.getChanges().isEmpty(), "Expected the book to have uncommitted changes (events).");
//
//        // Verify that the event store saved the BookCreatedEvent
//        ArgumentCaptor<Object> eventCaptor = ArgumentCaptor.forClass(Object.class);
//        verify(eventStore, times(1)).saveEvent(eventCaptor.capture());
//
//        Object savedEvent = eventCaptor.getValue();
//        assertTrue(savedEvent instanceof BookCreateEvent, "Expected a BookCreatedEvent to be saved in the event store.");
//
//        // Check that the event is correctly populated
//        BookCreateEvent bookCreateEvent = (BookCreateEvent) savedEvent;
//        assertEquals(bookId, bookCreateEvent.getBookId());
//
//        // Verify that the book changes are marked as committed
//        assertTrue(createdBook.getChanges().isEmpty(), "Expected changes to be marked as committed.");
//    }
//
//    @Test
//    void shouldMarkChangesAsCommittedAfterSavingEvents() {
//        // Given: A new book creation request
//        Long bookId = 2L;
//        String title = "Another Test Book";
//        String author = "Another Test Author";
//
//        // When: We create the book
//        Book bookToCreate = Book.create(bookId, title, author);
//        Book createdBook = bookCommandService.createBook(bookToCreate);
//
//        // Then: Ensure the book's changes have been committed
//        assertTrue(createdBook.getChanges().isEmpty(), "Expected the book changes to be committed (empty).");
//
//        // Verify that the event store's saveEvent method was called once
//        verify(eventStore, times(1)).saveEvent(any(BookCreateEvent.class));
//    }
//}
