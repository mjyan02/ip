# Zirnitra Chatbot User Guide

> “Your mind is for having ideas, not holding them.” – David Allen ([source](https://dansilvestre.com/productivity-quotes))

Zirnitra 🐉 frees your mind of having to remember things you need to do. It's,

- text-based
- easy to learn
- ~~FAST~~ SUPER FAST to use
- and it is **FREE**!

All you need to do is:

1. ensure you have `17` or above installed
2. download the latest `.jar` from [here](https://github.com/mjyan02/ip.git).
3. open cmd, `cd` into the folder you put the jar file in and use the `java -jar zirnitra.jar` command to run the application
4. add your tasks.
4. let it manage your tasks for you 😉

**Features:**

- [x] Add new tasks using **"todo", "deadline"** and **"event"** keywords
- [x] **List, mark, unmark** or **delete** any tasks added
- [x] Locate specific tasks in the list using **"find"** keyword
- [x] Duplicate tasks are detected and prevented from being added

**Commands:**

> [!NOTE]
> Command keywords and task description are coded to be non-case sensitive!

1. Viewing Help: `help`
   - Shows the welcome message for using Zirnitra

2. Adding a Task: `todo`, `deadline` or `event`
   - Add new tasks to task list
   - Example: `todo ST2132 Assignment 2`
   - Example: `deadline CS2103T ip /by 2025-02-21`
   - Example: `event Career Fair /from 2025-03-02 /to 2025-03-04`

3. Listing Tasks: `list`
   - List all tasks currently in the task list

4. Marking Tasks: `mark` or `unmark`
   - Mark tasks in task list by specifying task number after keyword
   - Example: `mark 1`

5. Deleting Tasks: `delete`
   - Delete tasks in task list by specifying task number after keyword
   - Example: `delete 1`

6. Finding Tasks: `find`
   - Find tasks in task list by specifying description after keyword
   - Example: `find CS2103T`
---

> [!NOTE] 
> If you are a Java programmer, you can use it to practice Java too. Here's the `main` method:

```
public class Main {
    public static void main(String[] args) {
        Application.launch(DukeLauncher.class, args);
    }
}
```