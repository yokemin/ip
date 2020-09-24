# User Guide

## Features 

### Feature 1: Add todo
Add a todo-type task.

#### Usage: `todo todoDescription`

#####_Requirements:_
- `todoDescription` cannot be empty
- `todo` keyword must be in non-capitalised

#####__Expected outcome:__

- adds a todo-type task with `todoDescription` as task description

#####__Example of usage:__

- input: `todo CS2113 Week 1 Repl.it exercises`
- outcome: adds `[T]`-type task `CS2113 Week 1 Repl.it exercises` to the list

_________

### Feature 2: Add deadline
Add a deadline-type task.

#### Usage: `deadline deadlineDescription /by date time`

#####_Requirements:_
- `deadlineDescription` cannot be empty
- `deadline` keyword must be in non-capitalised
- `/by` delimiter is necessary
- `date` is necessary; must be in `yyyy-MM-dd` format
- `time` is _optional_; must be in `HH:mm` format (hour is in 24 hours format)

#####__Expected outcome:__

- adds a deadline-type task with:
    1. `deadlineDescription` as task description
    2. `date` and `time` (if applicable) in brackets `(by: date time)`

#####__Example of usage:__

- input: `deadline do CS2113 luminus quiz /by 2020-08-08 23:59`
- outcome: adds `[D]`-type task `do CS2113 luminus quiz` `(by: Aug 08 2020 23:59)` to the list

_________

### Feature 3: Add event
Add a event-type task.

#### Usage: `event eventDescription /at inputString`

#####_Requirements:_
- `eventDescription` cannot be empty
- `event` keyword must be in non-capitalised
- `/at` delimiter is necessary
- `inputString` is necessary

#####__Expected outcome:__

- adds a event-type task with:
    1. `eventDescription` as task description
    2. `inputString` (if applicable) in brackets `(at: inputString)`

#####__Example of usage:__

- input: `event CS2113 exam /at school tmr`
- outcome: adds `[E]`-type task `CS2113 exam` `(at: school tmr)` to the list

_________

### Feature 4: List tasks

#### Usage: `list`

#####_Requirements:_
- `list` keyword must be in non-capitalised

#####__Expected outcome:__

- prints out all tasks in the list

#####__Example of usage:__

- input: `list`
- outcome: lists out all tasks (todo, deadline, event)

_________

### Feature 5: Mark Done
Mark a task as done. 
Before being marked done, tasks are all marked as undone.

#### Usage: `done taskNo`

#####_Requirements:_
- `taskNo` cannot be empty; must be a valid task number
- `done` keyword must be in non-capitalised

#####__Expected outcome:__

- marks `taskNo` task in list as done. 

#####__Example of usage:__

- input: `done 1`
- outcome: marks task 1 of list of tasks as done. 
    - Status icon for that particular task changes from `[cross]` to `[tick]`

_________

### Feature 6: Delete Tasks
Delete a task from current list of tasks.

#### Usage: `delete taskNo`

#####_Requirements:_
- `taskNo` cannot be empty; must be a valid task number
- `delete` keyword must be in non-capitalised

#####__Expected outcome:__

- `taskNo` task is removed from list of tasks

#####__Example of usage:__

- input: `delete 1`
- outcome: task 1 in the `list` will be deleted.

_________

### Feature 7: Find tasks
Finds tasks given a keyword and prints a list of task containing that keyword.

#### Usage: `find keyword`

#####_Requirements:_
- `keyword` cannot be empty
- `find` keyword must be in non-capitalised

#####__Expected outcome:__

- prints out a `list` of tasks that contains the `keyword`
- if none of the tasks in `list` contains `keyword` : message will be returned

#####__Example of usage:__

- input: `find work`
- outcome: prints a `list` of all tasks with `work` in its description or in its delimiter argument

_________

### Feature 8: Bye
Exits program
#### Usage: `bye`

#####_Requirements:_
- `bye` keyword must be in non-capitalised

#####__Expected outcome:__

- prints goodbye message and exits program

#####__Example of usage:__

- input: `bye`
- outcome: `Bye. Hope to see you again soon!`
