package org.example.domain.events;//package org.example.domain.events;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class BookCreateEventHandlerTest {
//
//    private BookCreatedEventHandler eventHandler;
//
//    @BeforeEach
//    void setUp() {
//        eventHandler = new BookCreatedEventHandler();
//    }
//
//    @Test
//    void shouldHandleBookCreatedEvent() {
//        // Given
//        BookCreateEvent event = new BookCreateEvent(1L);
//
//        // When
//        eventHandler.onBookCreated(event);
//
//        // Then
//        // In this case, you would likely verify some side effect or system interaction
//        // For now, this simply verifies that the method runs without error.
//    }
//}