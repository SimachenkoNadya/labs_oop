import java.util.ArrayList;
import java.util.List;

class Project {
    private String name;
    private List<Task> tasks;
    private List<Person> people;

    public Project(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
        this.people = new ArrayList<>();
    }

    public List<Person> getPeople() {
        return people;
    }

    public Task createTask(String type, String title) {
        TaskFactory factory;

        switch (type.toLowerCase()) {
            case "development":
                factory = new DevelopmentTaskFactory();
                break;
            case "documentation":
                factory = new DocumentationTaskFactory();
                break;
            default:
                throw new IllegalArgumentException("Invalid task type: " + type);
        }

        Task task = factory.createTask(title);
        tasks.add(task);
        return task;
    }

    public void addPerson(String firstName, String lastName) {
        people.add(new Person(firstName, lastName));
    }

    public Person findPersonByLastName(String lastName) {
        for (Person person : people) {
            if (person.getLastName().equalsIgnoreCase(lastName)) {
                return person;
            }
        }
        return null;
    }

    public void addCommentToPerson(String lastName, String comment) {
        Person person = findPersonByLastName(lastName);
        if (person != null) {
            person.setComment(comment);
        } else {
            System.out.println("Person with last name " + lastName + " not found.");
        }
    }

    public void listPeople() {
        System.out.println("People in project: " + name);
        for (Person person : people) {
            System.out.println("- " + person.getFirstName() + " " + person.getLastName() + (person.getComment() != null ? " (Comment: " + person.getComment() + ")" : ""));
        }
    }

    public void listTasks() {
        System.out.println("Tasks for project: " + name);
        for (Task task : tasks) {
            System.out.println("- " + task.getTitle());
        }
    }
}