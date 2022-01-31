import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private List<Person> persons;

    @BeforeEach
    void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        persons = objectMapper.readValue(new File("src/test/data/person.json"), new TypeReference<>() {});
    }

    @Test
    void itShouldCreatePerson() {
        Person person = new Person(1, "675-759-3583", new String[] {"Ms", "Dr."}, "Bail", "Haggerwood", "6757593583", new String[] {"Nurse Practicioner"}, "bhaggerwood0@eventbrite.com", 1, null, Salutation.Frau);
        assertEquals(1, person.getId());
        assertEquals("675-759-3583", person.getPhone());
        assertArrayEquals(new String[] {"Ms", "Dr."}, person.getTitles());
        assertEquals("6757593583", person.getFax());
        assertArrayEquals(new String[] {"Nurse Practicioner"}, person.getPositions());
        assertEquals("bhaggerwood0@eventbrite.com", person.getEmail());
        assertEquals(Salutation.Frau, person.getSalutation());
        assertEquals("Bail", person.getFirstname());
        assertEquals("Haggerwood", person.getLastname());
        assertEquals(person.getFaculty().getId(), 1);
        assertNull(person.getRoom());
    }

    @Test
    void itShouldPrintFullName() {
        assertEquals("Bail Haggerwood", persons.get(0).toString());
    }

    @Test
    void itShouldComparePersons() {
        // Bail Haggerwood comes after Wandie Dorgan, because of lastname
        assertTrue(persons.get(0).compareTo(persons.get(1)) > 0);

        // Wandie Dorgan comes after Alexander Dorgan, because of firstname
        assertTrue(persons.get(1).compareTo(persons.get(2)) > 0);
    }

    @Test
    void itShouldEqualsPersons() {
        assertEquals(persons.get(0), persons.get(0));
        assertNotEquals(persons.get(0), persons.get(1));
    }

    @Test
    void itShouldAddTitles() {
        assertArrayEquals(new String[] {"Ms", "Dr."}, persons.get(0).getTitles());
        persons.get(0).addTitle("Prof.");
        assertArrayEquals(new String[] {"Ms", "Dr.", "Prof."}, persons.get(0).getTitles());
    }

    @Test
    void itShouldAddPositions() {
        assertArrayEquals(new String[] {"Nurse Practicioner"}, persons.get(0).getPositions());
        persons.get(0).addPosition( "Dozent");
        assertArrayEquals(new String[] {"Nurse Practicioner", "Dozent"}, persons.get(0).getPositions());
    }

}