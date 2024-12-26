public class ProjectManagementSystem {
    public static void main(String[] args) {
        Project project = new Project("New Website Launch");

        project.addPerson("John", "Doe");
        project.addPerson("Jane", "Smith");
        project.addPerson("Emily", "Johnson");

        project.addCommentToPerson("Smith", "Lead Developer");
        project.addCommentToPerson("Johnson", "Technical Writer");

        Task devTask = project.createTask("development", "Build frontend");
        Task docTask = project.createTask("documentation", "Write API documentation");

        project.listPeople();
        project.listTasks();

        devTask.performTask();
        docTask.performTask();
    }
}
