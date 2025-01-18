package vn.duke.book.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testPersonBuilder() {
        // Build a person using builder pattern
        Person person = Person.builder()
                .firstName("John")
                .lastName("Doe")
                .age(30)
                .email("john.doe@example.com")
                .address("123 Main St")
                .build();

        // Verify the values
        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals(30, person.getAge());
        assertEquals("john.doe@example.com", person.getEmail());
        assertEquals("123 Main St", person.getAddress());
    }

    @Test
    void testPartialBuild() {
        // You can build with only some fields
        Person person = Person.builder()
                .firstName("Jane")
                .lastName("Doe")
                .build();

        // Verify set values
        assertEquals("Jane", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        
        // Unset values will be null or 0
        assertEquals(0, person.getAge());
        assertNull(person.getEmail());
        assertNull(person.getAddress());
    }
} 