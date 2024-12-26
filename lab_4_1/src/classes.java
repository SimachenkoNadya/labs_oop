abstract class Task {
    private String title;

    public Task(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract void performTask();
}

class DevelopmentTask extends Task {
    public DevelopmentTask(String title) {
        super(title);
    }

    @Override
    public void performTask() {
        System.out.println("Performing development task: " + getTitle());
        // Simulating task-specific logic
        System.out.println("Writing code and debugging...");
    }
}

class DocumentationTask extends Task {
    public DocumentationTask(String title) {
        super(title);
    }

    @Override
    public void performTask() {
        System.out.println("Performing documentation task: " + getTitle());
        // Simulating task-specific logic
        System.out.println("Writing technical documentation...");
    }
}

abstract class TaskFactory {
    public abstract Task createTask(String title);
}

class DevelopmentTaskFactory extends TaskFactory {
    @Override
    public Task createTask(String title) {
        return new DevelopmentTask(title);
    }
}

class DocumentationTaskFactory extends TaskFactory {
    @Override
    public Task createTask(String title) {
        return new DocumentationTask(title);
    }
}