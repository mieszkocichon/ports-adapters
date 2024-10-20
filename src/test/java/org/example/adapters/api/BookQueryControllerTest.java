package org.example.adapters.api;//package org.example.adapters.api;
//
//import org.example.domain.model.Book;
//import org.example.domain.services.BookQueryServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.ResponseEntity;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class BookQueryControllerTest {
//
//    private BookQueryServiceImpl bookQueryService;
//    private BookQueryController bookQueryController;
//
//    @BeforeEach
//    void setUp() {
//        bookQueryService = mock(BookQueryServiceImpl.class);
//        bookQueryController = new BookQueryController(bookQueryService);
//    }
//
//    @Test
//    void getBook_shouldReturnBook() {
//        // Arrange
//        Book book = Book.create(1L, "Title", "Author");
//        when(bookQueryService.getBook(1L)).thenReturn(book);
//
//        // Act
//        ResponseEntity<Book> response = bookQueryController.getBook(1L);
//
//        // Assert
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals(book, response.getBody());
//        verify(bookQueryService, times(1)).getBook(1L);
//    }
//}