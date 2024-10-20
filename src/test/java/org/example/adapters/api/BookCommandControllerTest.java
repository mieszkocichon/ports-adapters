package org.example.adapters.api;//package org.example.adapters.api;
//
//import org.example.domain.model.Book;
//import org.example.domain.services.BookCommandServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.ResponseEntity;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class BookCommandControllerTest {
//
//    private BookCommandServiceImpl bookCommandService;
//    private BookCommandController bookCommandController;
//
//    @BeforeEach
//    void setUp() {
//        bookCommandService = mock(BookCommandServiceImpl.class);
//        bookCommandController = new BookCommandController(bookCommandService);
//    }
//
//    @Test
//    void createBook_shouldReturnCreatedBook() {
//        // Arrange
//        Book book = Book.create(1L, "Title", "Author");
//        when(bookCommandService.createBook(any(Book.class))).thenReturn(book);
//
//        // Act
//        ResponseEntity<Book> response = bookCommandController.createBook(book);
//
//        // Assert
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals(book, response.getBody());
//        verify(bookCommandService, times(1)).createBook(book);
//    }
//}