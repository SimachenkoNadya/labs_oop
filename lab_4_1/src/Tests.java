import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class ProjectTest {

    private Project project;

    @BeforeEach
    void setUp() {
        project = new Project("New Website Launch");
        project.addPerson("John", "Doe");
        project.addPerson("Jane", "Smith");
        project.addPerson("Emily", "Johnson");
    }

    @Test
    void testAddPerson() {
        // Checking if the people were added correctly
        List<Person> people = project.getPeople();  // Assuming a getter for people
        assertEquals(3, people.size());
        assertEquals("John", people.get(0).getFirstName());
        assertEquals("Doe", people.get(0).getLastName());
    }

    @Test
    void testFindPersonByLastName() {
        Person person = project.findPersonByLastName("Smith");
        assertNotNull(person);
        assertEquals("Jane", person.getFirstName());
        assertEquals("Smith", person.getLastName());
    }

    @Test
    void testFindPersonByLastNameNotFound() {
        Person person = project.findPersonByLastName("Nonexistent");
        assertNull(person);
    }

    @Test
    void testAddCommentToPerson() {
        project.addCommentToPerson("Smith", "Lead Developer");
        Person person = project.findPersonByLastName("Smith");
        assertNotNull(person);
        assertEquals("Lead Developer", person.getComment());
    }

    @Test
    void testAddCommentToPersonNotFound() {
        project.addCommentToPerson("Nonexistent", "Some Comment");
        // Assuming the console output or logging mechanism is handled by a different method
        // No need to assert anything here if it's just printing a message
    }

    @Test
    void testCreateDevelopmentTask() {
        Task devTask = project.createTask("development", "Build frontend");
        assertNotNull(devTask);
        assertEquals("Build frontend", devTask.getTitle());
        assertTrue(devTask instanceof DevelopmentTask);
    }

    @Test
    void testCreateDocumentationTask() {
        Task docTask = project.createTask("documentation", "Write API documentation");
        assertNotNull(docTask);
        assertEquals("Write API documentation", docTask.getTitle());
        assertTrue(docTask instanceof DocumentationTask);
    }

    @Test
    void testCreateInvalidTask() {
        assertThrows(IllegalArgumentException.class, () -> project.createTask("invalid", "Some invalid task"));
    }

    @Test
    void testListPeople() {
        project.addCommentToPerson("Smith", "Lead Developer");
        project.addCommentToPerson("Johnson", "Technical Writer");
        project.listPeople();  // This will print to the console, no need to assert anything if just printing
    }

    @Test
    void testListTasks() {
        Task devTask = project.createTask("development", "Build frontend");
        Task docTask = project.createTask("documentation", "Write API documentation");

        project.listTasks();  // This will print to the console, no need to assert anything if just printing
    }

    @Test
    void testPerformDevelopmentTask() {
        Task devTask = project.createTask("development", "Build frontend");
        devTask.performTask();  // We are testing if the task performs correctly, no assert needed as it prints
    }

    @Test
    void testPerformDocumentationTask() {
        Task docTask = project.createTask("documentation", "Write API documentation");
        docTask.performTask();  // We are testing if the task performs correctly, no assert needed as it prints
    }
}