## Reeve - Developer Guide

**Table of Contents**
- [1. Introduction](#1-introduction)
- [2. About](#2-about)
- [3. Understanding the guide](#3-understanding-the-guide)
- [4. Getting started](#4-getting-started)
- [5. Design](#5-design)
    * [5.1 Architecture](#51-architecture)
    * [5.2 UI component](#52-ui-component)
    * [5.3 Logic component](#53-logic-component)
    * [5.4 Model component](#54-model-component)
    * [5.5 Storage component](#55-storage-component)
    * [5.6 Common classes](#56-common-classes)
- [6. Implementation](#6-implementation)
    * [6.1 General features](#61-general-features)
        * [6.1.1 Help Command](#611-help-command)
        * [6.1.2 Toggle Command](#612-toggle-command)
        * [6.1.3 Exit Command](#613-exit-command)
    * [6.2 Student administrative details features](#62-student-administrative-details-features)
        * [6.2.1 Add student command](#621-add-student-command)
        * [6.2.2 Edit student command](#622-edit-student-command)
        * [6.2.3 Delete student command](#623-delete-student-command)
        * [6.2.4 Find student command](#624-find-student-command)
        * [6.2.5 Overdue command](#625-overdue-command)
        * [6.2.6 Detail commands](#626-detail-commands)
            * [6.2.6.1 Add detail command](#6261-add-detail-command)
            * [6.2.6.2 Edit detail command](#6262-edit-detail-command)
            * [6.2.6.3 Delete detail command](#6263-delete-detail-command)
        * [6.2.7 Sort command](#627-sort-command)
    * [6.3 Student academic details features](#63-student-academic-details-features)
        * [6.3.1 Question commands](#631-question-commands)
            * [6.3.1.1 Add question command](#6311-add-question-command)
            * [6.3.1.2 Solve question command](#6312-solve-question-command)
            * [6.3.1.3 Delete question command](#6313-delete-question-command)
            * [6.3.1.4 Design considerations](#6314-design-considerations)
        * [6.3.2 Exam Commands](#632-exam-commands)
            * [6.3.2.1 Add exam command](#6321-add-exam-command)
            * [6.3.2.2 Delete exam command](#6322-delete-exam-command)
            * [6.3.2.3 Exam Stats command](#6323-exam-stats-command)
        * [6.3.3 Attendance commands](#633-attendance-commands)
            * [6.3.3.1 Add attendance command](#6331-add-attendance-command)
            * [6.3.3.2 Delete attendance command](#6332-delete-attendance-command)
    * [6.4 Schedule command](#64-schedule-command)
    * [6.5 Notebook feature](#65-notebook-feature)
        * [6.5.1 Add note command](#651-add-note-command)
        * [6.5.2 Edit note command](#652-edit-note-command)
        * [6.5.3 Delete note command](#653-delete-note-command)
- [7. Documentation](#7-documentation)
- [8. Logging](#8-logging)
- [9. Testing](#9-testing)
- [10. Configuration](#10-configuration)
- [11. DevOps](#11-devops)
- [Appendix A: Product Scope](#appendix-a-product-scope)
- [Appendix B: User Stories](#appendix-b-user-stories)
- [Appendix C: Use Cases](#appendix-c-use-cases)
- [Appendix D: Non-Functional Requirements](#appendix-d-non-functional-requirements)
- [Appendix E: Glossary](#appendix-e-glossary)
- [Appendix F: Instructions for Manual Testing](#appendix-f-instructions-for-manual-testing)
    * [F.1 Launch and Shutdown](#f1-launching-reeve)
    * [F.2 General Features](#f2-general-features)
    * [F.3 Administrative Features](#f3-student-administrative-features)
    * [F.4 Academic Features](#f4-student-academic-features)
    * [F.5 Notebook Features](#f5-notebook-feature)
    * [F.6 Saving Data](#f6-saving-data)


## 1. Introduction
Welcome to **Reeve**!

**Reeve** is a desktop application for **private tutors to better manage both administrative and academic details of their students**, optimised for use via a
**Command Line Interface (CLI)** for receiving inputs while still having the benefits of a **Graphical User Interface (GUI)** for displaying information.
In addition, **Reeve** comes with a customisable personal scheduler to assist users to keep track of their classes. **Reeve** also allows users to set timely reminders for themselves.

Reeve is optimized for users that are very comfortable with typing as it works on a Command Line Interface (CLI).

Students' details are displayed in a neat and organized manner through the use of a Graphical User Interface (GUI).

## 2. About

This developer guide will provide you the details of the software architecture and implementation of **Reeve**.
It is made for developers who wish to understand the internal and external workings of the application.

All developers are warmly welcome to contribute your ideas and improve **Reeve**!
To contribute, simply head over to [our github](https://github.com/AY2021S1-CS2103T-W15-2/tp) and raise an issue.

## 3. Understanding the Guide

This section will share with you how should you go about understanding this guide in order to best understand **Reeve**.

We have adopted the "top-down" approach into the structure of this guide where we will first look at the high-level structure of our application
before going into the implementation details of each feature.

We highly encourage you to read the guide from top to bottom in order to have the best understanding of **Reeve**.

Here is a summary (Table 1) of the symbols that are used in this Developer Guide:

Table 1: Summary of symbols

Symbol | Meaning
:-----:|:-------
`code` | Code snippets
:information_source: | Important information
:bulb: | Tips

## 4. **Getting Started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

## 5. **Design**
 
### 5.1 Architecture

<p align="center"><img src="images/ArchitectureDiagram.png" align="center" width="450"/></p>
<div align="center">Figure 1: Architecture Diagram</div><br>

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2021S1-CS2103T-W15-2/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S1-CS2103T-W15-2/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2021S1-CS2103T-W15-2/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#56-common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#52-ui-component): The UI of the App.
* [**`Logic`**](#53-logic-component): The command executor.
* [**`Model`**](#54-model-component): Holds the data of the App in memory.
* [**`Storage`**](#55-storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (in the diagram below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

<p align="center"><img src="images/LogicClassDiagram.png" align="center"/></p>
<div align="center">Figure 2: Logic Component Class Diagram</div><br>

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<p align="center"><img src="images/ArchitectureSequenceDiagram.png" align="center" width="574"/></p>
<div align="center">Figure 3: Architecture Sequence Diagram</div><br>

The sections below give more details of each component.

### 5.2 UI component

<p align="center"><img src="images/UiClassDiagram.png" align="center"/></p>
<div align="center">Figure 4: Ui Component Class Diagram</div><br>

**API** :
[`Ui.java`](https://github.com/AY2021S1-CS2103T-W15-2/tp/tree/master/src/main/java/seedu/address/ui)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `StudentListPanel`, `Notebook` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### 5.3 Logic component

<p align="center"><img src="images/LogicClassDiagram.png" align="center"/></p>
<div align="center">Figure 5: Logic Component Class Diagram</div><br>

**API** :
[`Logic.java`](https://github.com/AY2021S1-CS2103T-W15-2/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `ReeveParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a student).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

<p align="center"><img src="images/DeleteSequenceDiagram.png" align="center"/></p>
<div align="center">Figure 6: Delete Sequence Diagram</div><br>

<div markdown="span" class="alert alert-info">
:information_source: The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### 5.4 Model component

<p align="center"><img src="images/ModelClassDiagram.png" align="center"/></p>
<div align="center">Figure 7: Model Component Class Diagram</div><br>

<p align="center"><img src="images/AdminClassDiagram.png" align="center"/></p>
<div align="center">Figure 8: Admin Class Diagram</div><br>

<p align="center"><img src="images/AcademicClassDiagram.png" align="center"/></p>
<div align="center">Figure 9: Academic Class Diagram</div><br>

**API** : [`Model.java`](https://github.com/AY2021S1-CS2103T-W15-2/tp/blob/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the reeve data.
* exposes an unmodifiable `ObservableList<Student>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.

### 5.5 Storage component

<p align="center"><img src="images/StorageClassDiagram.png" align="center"/></p>
<div align="center">Figure 10: Storage Class Diagram</div><br>

**API** : [`Storage.java`](https://github.com/AY2021S1-CS2103T-W15-2/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

From the diagram above, the `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save `Reeve` data in json format and read it back.
* stores `Student` and `Note` data from the `Model` component in `JsonSerializableReeve` and `JsonSerializableNotebook` objects respectively.

The `JsonSerializableReeve` component stores `JsonAdaptedStudent` objects converted from the `Student` objects in the `Model` component.
Each `JsonAdaptedStudent` object also contains json-friendly versions of `Student` data as shown in the class diagram below.

<p align="center"><img src="images/JsonSerializableReeveClassDiagram.png" align="center"/></p>
<div align="center">Figure 11: JsonSerializableReeve Class Diagram</div><br>

The `JsonSerializableNotebook` component stores `JsonAdaptedNote` objects converted from `Note` objects in the `Model` component, as shown in the class diagram below.

<p align="center"><img src="images/JsonSerializableNotebookClassDiagram.png" align="center"/></p>
<div align="center">Figure 12: JsonSerializableNotebook Class Diagram</div><br>

### 5.6 Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

## 6. **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### 6.1 General features

This section describes some key details on how general features are implemented.

#### 6.1.1 Help Command

The following describes the flow of how `HelpCommand` is performed.

1. Upon successfully parsing the user input, the `HelpCommand#execute(Model model)` is called.
2. A `CommandResult` with the `shouldShowHelp` field set to true is returned and `MainWindow#handleHelp()` is called.
3. A Help display window will be opened showing a link to the User Guide.

<div markdown="block" class="alert alert-info">

:information_source: If there is already a Help window already opened, and `HelpCommand` is executed, `HelpWindow#focus()` will be called to focus on the already opened window.

</div>

#### 6.1.2 Toggle Command

The following describes the flow of how `ToggleStudentCardCommand` is performed.

1. Upon successfully parsing the user input, the `ToggleStudentCardCommand#execute(Model model)` is called.
2. A `CommandResult` with the `toggleStudentCard` field set to true is returned and `MainWindow#handleAcademicPanel()` is called.
3. Student cards in `StudentListPanel` will be switched.

<div markdown="block" class="alert alert-info">

:information_source: By default, `StudentListPanel` uses the `StudentAcademicCard`.

</div><br>

The following sequence diagram shows how the `ToggleStudentCardCommand` execution works.

<p align="center"><img src="images/ToggleStudentCardSequenceDiagram.png" align="center"/></p>
<div align="center">Figure 13: ToggleStudentCard Sequence Diagram</div><br>

The following explains the design considerations of the `toggle` command.

**Aspect: How the GUI responds when toggle is executed**

* **Alternative 1 (current choice)**: Switch between two types of student cards, student academic card and student admin card for the cards used in the student list panel.
    * Pros: Easy to implement, reduces cluttering of information, allows for better focus on different information,.
    * Cons: Can take a long time to execute finish or introduce unfinished toggling if student list is large.
    
* **Alternative 2**: Introduce two tabs, one for admin details and the other for academic details and toggling switches between these two tabs.
    * Pros: Student list size does not slow down execution of command.
    * Cons: Double the work when executing commands such as `find` because there are now two lists to update, repeat of basic information such as student's name, phone, school and academic level, harder to implement.
    
#### 6.1.3 Exit Command

The following describes the flow of how `ExitCommand` is performed.

1. Upon successfully parsing the user input, the `ExitCommand#execute(Model model)` is called.
2. A `CommandResult` with the `shouldExit` field set to true is returned and `MainWindow#handleExit()` is called.
3. **Reeve** shuts down.

### 6.2 Student administrative details features

This section describes some key details on how administrative details features are implemented.

#### 6.2.1 Add Student Command

The following describes the flow of how `AddCommand` is performed.

1. Upon successfully parsing the user input, the `AddCommand#execute(Model model)` is called which checks whether
the added student already exists in the `UniqueStudentList` using the `Model#hasStudent(Student toAdd)`.
2. A unique student is defined by `Name`, `Phone`, `School` and `Year`. If a duplicate student is defined,
a `CommandException` is thrown and the student will not be added.
3. The `AddCommand#execute(Model model)` also checks if the student to be added has clashing `ClassTime` with other students already in the `UniqueStudentList`.
4. Two student's `ClassTime` are considered clashing if they overlap either partially or fully. A `CommandException` will be thrown if there are other students with clashing class time.
5. If the added student is not a duplicate and there are no clashes in class time, then the `Model#addStudent(Student toAdd)` is called to add the student.
A new `CommandResult` is returned with a success message and the added student.
6. The student is be added into `UniqueStudentList` and a success message is shown in the result display.

The following sequence diagram summarizes the execution of the `AddCommand`:

<p align="center"><img src="images/AddStudentSequenceDiagram.png" align="center"/></p>
<div align="center">Figure 14: Add Student Sequence Diagram</div><br>

The following activity diagram summarizes the flow of events when the `AddCommand` is being executed:

<p align="center"><img src="images/AddStudentActivityDiagram.png" align="center"/></p>
<div align="center">Figure 15: Add Student Activity Diagram</div><br>

#### 6.2.2 Edit Student Command

The following describes the flow of how `EditCommand` is executed.

1. Upon successfully parsing the user input, the `EditCommand#execute(Model model)` is called which checks if the student at the specified position exists.
2. If there is no student at the specified position, a `CommandException` is thrown and the student is not edited.
3. If there is such a student, the `EditCommand#execute(Model model)` then creates the edited student, and checks to see if the edited student already exists in the `UniqueStudentList` using the `Model#hasStudent(Student toAdd)`.
4. If it is a duplicate student, a `CommandException` is thrown and the edited student will not be added.
5. The `EditCommand#execute(Model model)` also checks if the edited student has clashing `ClassTime` with other students already in the `UniqueStudentList`.
6. Two student's `ClassTime` are considered clashing if they overlap either partially or fully. A `CommandException` will be thrown if there are other students with clashing class time.
7. If the edited student is not a duplicate and there are no clashes in class time, then the`Model#setStudent(Student target, Student editedStudent)` is called to replace the outdated student with the edited copy. A new `CommandResult` is returned with a success message showing the newly edited student.
8. The edited student replaces the outdated student in the `UniqueStudentList` and a success message is shown in the result display.

The following sequence diagram shows how the `EditCommand` execution works.

<p align="center"><img src="images/EditStudentSequenceDiagram.png" align="center"/></p>
<div align="center">Figure 16: Edit Student Sequence Diagram</div><br>

The following activity diagram summarizes the flow of events when the `EditCommand` is executed.

<p align="center"><img src="images/EditStudentActivityDiagram.png" align="center"/></p>
<div align="center">Figure 17: Edit Student Activity Diagram</div><br>

#### 6.2.3 Delete Student Command

The following describes the flow of how `DeleteCommand` is performed.

1. Upon successfully parsing the user input, the `DeleteCommand#execute(Model model)` is called which checks whether
the specified `Index` is a valid index based on the `UniqueStudentList`, in the case where it is invalid, a `CommandException` is thrown and no student will be deleted.
2. Otherwise, the `Student` at the specified valid `Index` is then removed from the `UniqueStudentList` using the `Model#deleteStudent(Student target)` method.
3. The specified student is deleted from the `UniqueStudentList` and a success message is shown in the result display.

<div markdown="block" class="alert alert-info">

:information_source: A valid `Index` is one that is within the bounds of the `UniqueStudentList`.

</div>

#### 6.2.4 Find Student Command

The following describes the flow of how `FindCommand` is performed.

1. After the `FindCommand`  is created by parsing user input, `FindCommand::execute` is called.
2. The method then calls `getPredicates()` of the `FindStudentDescriptor` stored within `FindCommand` to obtain a `List<Predicate>` to search with.
3. The predicates within `List<Predicate>`are then combined into `consolidatedPredicate`.
4. The `FilteredList<Student>` within the `Model` is then updated using `Model#updateFilteredPersonList(Predicate predicate)` for display.
5. A new `CommandResult` will be returned with the success message.

The following sequence diagram shows how the `FindCommand` execution works.

<p align="center"><img src="images/FindCommandSequenceDiagram.png" align="center"/></p>
<div align="center">Figure 18: Find Student Sequence Diagram</div><br>

The following activity diagram summarizes the flow of events when the `FindCommand` is executed.

<p align="center"><img src="images/FindCommandActivityDiagram.png" align="center"/></p>
<div align="center">Figure 19: Find Student Activity Diagram</div><br>

#### 6.2.5 Overdue Command

The overdue payment filter feature allows the tutor to find all students who have not paid their tuition fees in the past month. It is handled by the `OverdueCommand`.

The following describes the flow of how `OverdueCommand` is executed.

1. Upon successfully parsing the user input, `OverdueCommand#execute(Model model)` is called to filter all students in Reeve whose last date of payment was more than a month ago.
2. `Model#updateFilteredStudentsList(Predicate<Student> predicate)` is called to find only students that match the above condition. A new `CommandResult` is returned with a successful message indicating the number of matching students.
3. The filtered student list replaces the displayed list on the GUI and a success message is shown in the result display.

The following sequence diagram shows how the `OverdueCommand` execution works.

<p align="center"><img src="images/OverdueSequenceDiagram.png" align="center"/></p>
<div align="center">Figure 20: Overdue Sequence Diagram</div><br>

The following activity diagram summarises the flow of events when `OverdueCommand` is executed.

<p align="center"><img src="images/OverdueActivityDiagram.png" align="center"/></p>
<div align="center">Figure 21: Overdue Activity Diagram</div><br>

#### 6.2.6 Detail Commands

The Detail commands keep track of any additional details a tutor wants to add to a student. They comprise of the following commands:

* `AddDetailCommand` - Adds a detail to a specified student.
* `EditDetailCommand` - Edits a specified detail in a specified student.
* `DeleteDetailCommand` - Deletes a specified detail from a specified student.

##### 6.2.6.1 Add Detail Command

The following describes the flow of how `AddDetailCommand` is performed.

1. Upon successfully parsing the user input, `AddDetailCommand#execute(Model model)`, and it checks if the student at the specified position exists.
2. If there is no student at the specified position,  a `CommandException` is thrown and the detail will not be added.
3. If the student exists, the detail is added to the student's list of details, and `DetailCommand#updateStudentDetail(Student studentToAddDetail, List<Detail> details)` is called to create a modified copy of the student with the new detail.
4. `Model#setStudent(Student target, Student editedStudent)` is called to replace the student with the modified copy. A new `CommandResult` is returned with a success message showing the affected student and the detail added.
5. The modified student replaces the outdated student in the `UniqueStudentList` and a success message is shown in the result display.

The following sequence diagram shows how the detail adding operation works.

<p align="center"><img src="images/AddDetailSequenceDiagram.png" align="center"/></p>
<div align="center">Figure 22: Add detail Sequence Diagram</div><br>

The following activity diagram summarises the flow of events when `AddDetailCommand` is executed.

<p align="center"><img src="images/AddDetailActivityDiagram.png" align="center"/></p>
<div align="center">Figure 23: Add detail activity Diagram</div><br>

##### 6.2.6.2 Edit Detail Command

The following describes the flow of how `EditDetailCommand` is performed.

1. Upon successfully parsing the user input, `EditDetailCommand#execute(Model model)`, and it checks if the student at the specified position exists.
2. If there is no student at the specified position,  a `CommandException` is thrown and the detail will not be edited.
3. If the student exists, `EditDetailCommand#execute(Model model)` then checks if the detail at the specified position exists.
4. If there is no detail at the specified position, a `CommandException` is thrown and the detail will not be edited. 
5. If the detail exists, the newly updated detail is added to the student's list of details, and `DetailCommand#updateStudentDetail(Student studentToEditDetail, List<Detail> details)` is called to create a modified copy of the student with the new detail.
6. `Student#addQuestion(Question question)` is called to create a modified copy of the student with a newly added question.
7. `Model#setStudent(Student target, Student editedStudent)` is called to replace the student with the modified copy. A new `CommandResult` is returned with a success message showing the affected student and the detail added.
8. The modified student replaces the outdated student in the `UniqueStudentList` and a success message is shown in the result display.

The following sequence diagram shows how the detail editing operation works.

<p align="center"><img src="images/EditDetailSequenceDiagram.png" align="center"/></p>
<div align="center">Figure 24: Edit detail Sequence Diagram</div><br>

The following activity diagram summarises the flow of events when `EditDetailCommand` is executed.

<p align="center"><img src="images/EditDetailActivityDiagram.png" align="center"/></p>
<div align="center">Figure 25: Edit detail Activity Diagram</div><br>

##### 6.2.6.3 Delete Detail Command

The following describes the flow of how `DeleteDetailCommand` is performed.

1. Upon successfully parsing the user input, `DeleteDetailCommand#execute(Model model)`, and it checks if the student at the specified position exists.
2. If there is no student at the specified position,  a `CommandException` is thrown and the detail will not be deleted.
3. If the student exists, `DeleteDetailCommand#execute(Model model)` then checks if the detail at the specified position exists.
4. If there is no detail at the specified position, a `CommandException` is thrown and the detail will not be deleted. 
5. If the detail exists, the detail is removed from the student's list of details, and `DetailCommand#updateStudentDetail(Student studentToDeleteDetail, List<Detail> details)` is called to create a modified copy of the student with detail removed.
6. `Student#addQuestion(Question question)` is called to create a modified copy of the student with a newly added question.
7. `Model#setStudent(Student target, Student editedStudent)` is called to replace the student with the modified copy. A new `CommandResult` is returned with a success message showing the affected student and the removed detail.
8. The modified student replaces the outdated student in the `UniqueStudentList` and a success message is shown in the result display.

The following sequence diagram shows how the detail deleting operation works.

<p align="center"><img src="images/DeleteDetailSequenceDiagram.png" align="center"/></p>
<div align="center">Figure 26: Delete detail Sequence Diagram</div><br>

The following activity diagram summarises the flow of events when `DeleteDetailCommand` is executed.

<p align="center"><img src="images/DeleteDetailActivityDiagram.png" align="center"/></p>
<div align="center">Figure 27: Delete detail Activity Diagram</div><br>

#### 6.2.7 Sort Command

This is an explanation of how `SortCommand` works.

1. After the `SortCommand` is successfully parsed, `SortCommand#execute(Model model)` is called to execute the command.
2. The `comparisonMeans` stored within the `SortCommand` is checked against `NameComparator.COMPARISON_MEANS`, `ClassTimeComparator.COMPARISON_MEANS` and `YearComparator.COMPARISON_MEANS`.
3. If `comparisonMeans` matches the comparison means of `NameComparator`, `NameComparator.COMPARISON_MEANS`, a new `NameComparator` is created and `Model#updateSortedStudentList(Comparator<? extends Student> comparator)` called.
4. Step 3 is repeated similarly for `ClassTimeComparator` and `YearComparator`.
5. If `comparisonMeans` matches no comparison means in steps 3 and 4, a `CommandException` is thrown.
6. The student list is sorted and a `CommandResult` with the success message is returned. 

The following sequence diagram shows how the `SortCommand` execution works.

<p align="center"><img src="images/SortCommandSequenceDiagram.png" align="center"/></p>
<div align="center">Figure 28: Sort Command Sequence Diagram</div><br>

The following activity diagram summarises the flow of events when `SortCommand` is executed.

<p align="center"><img src="images/SortCommandActivityDiagram.png" align="center"/></p>
<div align="center">Figure 29: Sort Command Activity Diagram</div><br>

### 6.3 Student academic details features

This section describes some key details on how academic details features are implemented.

#### 6.3.1 Question Commands

The Question commmands keep track of questions raised by a student to his tutor. They comprise of the following commands:

* `AddQuestionCommand` - Adds a question to a specified student.
* `SolveQuestionCommand` - Marks a specified question from a specified student as solved.
* `DeleteQuestionCommand` - Deletes a specified question from a specified student.

##### 6.3.1.1 Add Question Command

The following describes the flow of how `AddQuestionCommand` is performed.

1. Upon successfully parsing the user input, `AddQuestionCommand#execute(Model model)` is called to check if the student at the specified position exists.
2. If there is no student at the specified position,  a `CommandException` is thrown and the question will not be added.
3. If the student exists, `AddQuestionCommand#execute(Model model)` checks if the student already has a similar question recorded.
4. A unique question is defined solely by its `question` and does not take into account if the question has been solved. If a duplicate question is found, a `CommandException` is thrown and the question will not be added.
5. If the question is not a duplicate, `Student#addQuestion(Question question)` is called to create a modified copy of the student with a newly added question.
6. `Model#setStudent(Student target, Student editedStudent)` is called to replace the student with the modified copy. A new `CommandResult` is returned with a success message showing the affected student and the question added.
7. The modified student replaces the outdated student in the `UniqueStudentList` and a success message is shown in the result display.

The following sequence diagram shows how the question adding operation works.

<p align="center"><img src="images/AddQuestionSequenceDiagram.png" align="center"/></p>
<div align="center">Figure 30: Add Question Sequence Diagram</div><br>

The following activity diagram summarises the flow of events when `AddQuestionCommand` is executed.

<p align="center"><img src="images/AddQuestionActivityDiagram.png" align="center"/></p>
<div align="center">Figure 31: Add Question Activity Diagram</div><br>

##### 6.3.1.2 Solve Question Command

The following describes the flow of how `SolveQuestionCommand` is performed.

1. Upon successfully parsing the user input, `SolveQuestionCommand#execute(Model model)` is called to check if the student at the specified position exists.
2. If there is no student at the specified position,  a `CommandException` is thrown and the question will not be added.
3. If the student exists, `SolveQuestionCommand#execute(Model model)` checks if there is a question at the specified position.
4. If the question does not exist, a `CommandException` is thrown and the question will not be resolved.
5. If the question exists, `Student#setQuestion(Question target, Question newQuestion)` is called to create a modified copy of the student where the specified question has been replaced with a solved version.
6. `Model#setStudent(Student target, Student editedStudent)` is called to replace the student with the modified copy. A new `CommandResult` is returned with a success message showing the affected student and the question solved.
7. The modified student replaces the outdated student in the `UniqueStudentList` and a success message is shown in the result display.

The following sequence diagram shows how the question solving operation works.

<p align="center"><img src="images/SolveQuestionSequenceDiagram.png" align="center"/></p>
<div align="center">Figure 32: Solve Question Sequence Diagram</div><br>

The following activity diagram summarises the flow of events when `SolveQuestionCommand` is executed.

<p align="center"><img src="images/SolveQuestionActivityDiagram.png" align="center"/></p>
<div align="center">Figure 33: Solve Question Activity Diagram</div><br>

##### 6.3.1.3 Delete Question Command

The following describes the flow of how `DeleteQuestionCommand` is performed.

1. Upon successfully parsing the user input, `DeleteQuestionCommand#execute(Model model)` is called to check if the student at the specified position exists.
2. If there is no student at the specified position,  a `CommandException` is thrown and the question will not be added.
3. If the student exists, `DeleteQuestionCommand#execute(Model model)` checks if there is a question at the specified position.
4. If the question does not exist, a `CommandException` is thrown and the question will not be resolved.
5. If the question exists, `Student#deleteQuestion(Question target)` is called to create a modified copy of the student without the specified question.
6. `Model#setStudent(Student target, Student editedStudent)` is called to replace the student with the modified copy. A new `CommandResult` is returned with a success message showing the affected student and the question removed.
7. The modified student replaces the outdated student in the `UniqueStudentList` and a success message is shown in the result display.

The following sequence diagram shows how the question deletion operation works.

<p align="center"><img src="images/DeleteQuestionSequenceDiagram.png" align="center"/></p>
<div align="center">Figure 34: Delete Question Sequence Diagram</div><br>

The following activity diagram summarises the flow of events when `DeleteQuestionCommand` is executed.

<p align="center"><img src="images/DeleteQuestionActivityDiagram.png" align="center"/></p>
<div align="center">Figure 35: Delete Question Activity Diagram</div><br>

##### 6.3.1.4 Design considerations

The `Student` class guarantees immutability, including its list of `Question` objects. As such, any operation must return a modified copy instead of directly modifying the base list.

The pros of doing so is that it becomes easier to test and debug as there are fewer mutations to worry about.

The downside, however, that this approach is more memory-intensive and possibly slower, since changing a single question involves recreating the entire list of questions attached to the student.

Having considered that our target audience (one-to-one private tutors) are unlikely to have so much data that this would severely impact performance, we believe that this is worth the trade-off.

#### 6.3.2 Exam Commands

The Exam commands keep track of exam records of a student. They comprise of the following commands:

* `AddExamCommand` - Adds a exam record to a specified student.
* `DeleteExamCommand` - Deletes a specified exam record from a specified student.
* `ExamStatsCommand` - Displays the exam statistics of a specified student in the form of a line graph.

The structure of exam commands is as shown below:

<p align="center"><img src="images/ExamCommandClassDiagram.png" align="center"/></p>
<div align="center">Figure 36: Exam Command Class Diagram</div><br>

##### 6.3.2.1 Add exam command

The following describes the flow of how `AddExamCommand` is performed.

1. Upon successfully parsing the user input, `AddExamCommand#execute(Model model)` is called to check if the student at the specified position exists.
2. If there is no student at the specified position,  a `CommandException` is thrown and the exam will not be added.
3. If the student exists, `AddExamCommand#execute(Model model)` checks if the student already has a similar exam recorded.
4. A unique exam is defined solely by its `examName`. If a duplicate exam is found, a `CommandException` is thrown and the exam will not be added.
5. If the exam is not a duplicate, `Student#getExams()` is called get the current list of exams of the specified student.
6. The new exam is added into this current list and a new updated `Student` is created which has exactly the same characteristics as the specified student but with the updated exam list.
7. `Model#setStudent(Student selectedStudent, Student updatedStudent)` is called to replace the student with the updated copy. A new `CommandResult` is returned with a success message showing the affected student and the exam added.
8. The updated student replaces the outdated student in the `UniqueStudentList` and a success message is shown in the result display.

The sequence and activity diagrams are very similar to those of the [detail adding command](#6261-add-detail-command)

##### 6.3.2.2 Delete exam command

The following describes the flow of how `DeleteExamCommand` is performed.

1. Upon successfully parsing the user input, `DeleteExamCommand#execute(Model model)` is called to check if the student at the specified position exists.
2. If there is no student at the specified position,  a `CommandException` is thrown.
3. If the student exists, `DeleteExamCommand#execute(Model model)` checks if there is a exam at the specified position.
4. If the exam does not exist, a `CommandException` is thrown.
5. If the exam exists, `Student#getExams()` is called get the current list of exams of the specified student.
6. `Model#setStudent(Student selectedStudent, Student updatedStudent)` is called to replace the student with the modified copy. A new `CommandResult` is returned with a success message showing the affected student and the exam removed.
7. The modified student replaces the outdated student in the `UniqueStudentList` and a success message is shown in the result display.

<div markdown="block" class="alert alert-info">

:information_source: Exams are specified based on the indexes of the list of exams shown on the academic view of student details.

</div><br>

The sequence and activity diagrams are very similar to those of the [detail deleting command](#6263-delete-detail-command)

##### 6.3.2.3 Exam Stats command

The following describes the flow of how `ExamStatsCommand` is performed.

1. Upon successfully parsing the user input, `ExamStatsCommand#execute(Model model)` is called to check if the student at the specified position exists.
2. If there is no student at the specified position,  a `CommandException` is thrown and no exam statistics window will be shown.
3. If the student exists, `ExamStatsCommand#execute(Model model)` gets the specified student from `sortedStudentList`.
4. A `CommandResult` with a non-null student input is returned and `MainWindow#handleExamStats(Student student)` is called.
5. A new ExamStats display window will be opened showing a line graph representing the exam scores of the specified student.

The following sequence diagram shows how the exam stats operation works.

<p align="center"><img src="images/ExamStatsSequenceDiagram.png" align="center"/></p>
<div align="center">Figure 37: Exam Stats Sequence Diagram</div><br>

The following activity diagram summarises the flow of events when `ExamStatsCommand` is executed.

<p align="center"><img src="images/ExamStatsActivityDiagram.png" align="center"/></p>
<div align="center">Figure 38: Exam Stats Activity Diagram</div><br>

The following explains the design considerations of the `exam stats` command.

**Aspect: How the GUI responds when exam stats is executed**

* **Alternative 1 (current choice)**: Open new window that displays exam statistics.
    * Pros: Easy to implement, allows for comparison and reference with student data, allows for multiple students' exam statistics to be opened, easy for users to understand.
    * Cons: Can be more taxing on processor if many windows are opened simultaneously 
    
* **Alternative 2**: Switch from the displayed student list panel to an exam statistics panel.
    * Pros: Only one window open at all time.
    * Cons: Unable to compare and reference with student data, harder to implement, can introduce confusion when trying to switch back to the student list panel.

#### 6.3.3 Attendance Commands

The Attendance commands keep track of the attendance for a student. They comprise of the following commands:

* `AddAttendanceCommand` - Adds an attendance to a specified student.
* `DeleteAttendanceCommand` - Deletes a specified attendance from a specified student.

##### 6.3.3.1 Add Attendance Command

The following describes the flow of how `AddAttendanceCommand` is performed.

1. Upon successfully parsing the user input, `AddAttendanceCommand#execute(Model model)`, and it checks if the student at the specified position exists.
2. If there is no student at the specified position,  a `CommandException` is thrown and the attendance will not be added.
3. If the student exists, `AddAttendanceCommand#execute(Model model)` then checks if the student has an existing attendance with the input attendance date.
4. If there is such an attendance, a `CommandException` is thrown and the attendance will not be added.
5. If there is no such attendance, the attendance is added to the student's list of attendances, and `AttendanceCommand#updateStudentAttendance(Student studentToAddAttendance, List<Attendance> attendances)` is called to create a modified copy of the student with the new attendance.
4. `Model#setStudent(Student target, Student editedStudent)` is called to replace the student with the modified copy. A new `CommandResult` is returned with a success message showing the affected student and the attendance added.
5. The modified student replaces the outdated student in the `UniqueStudentList` and a success message is shown in the result display.

The following sequence diagram shows how the attendance adding operation works.

<p align="center"><img src="images/AddAttendanceSequenceDiagram.png" align="center"/></p>
<div align="center">Figure 39: Add Attendance Sequence Diagram</div><br>

The following activity diagram summarises the flow of events when `AddAttendanceCommand` is executed.

<p align="center"><img src="images/AddAttendanceActivityDiagram.png" align="center"/></p>
<div align="center">Figure 40: Add Attendance Activity Diagram</div><br>

##### 6.3.3.2 Delete Attendance Command

The following describes the flow of how `DeleteAttendanceCommand` is performed.

1. Upon successfully parsing the user input, `DeleteAttendanceCommand#execute(Model model)`, and it checks if the student at the specified position exists.
2. If there is no student at the specified position,  a `CommandException` is thrown and the attendance will not be deleted.
3. If the student exists, `DeleteAttendanceCommand#execute(Model model)` then checks if the student has an existing attendance with the input attendance date.
4. If there is no such attendance, a `CommandException` is thrown and the attendance will not be deleted.
5. If there is such an attendance, the attendance is deleted from the student's list of attendances, and `AttendanceCommand#updateStudentAttendance(Student studentToDeleteAttendance, List<Attendance> attendances)` is called to create a modified copy of the student with attendance removed.
4. `Model#setStudent(Student target, Student editedStudent)` is called to replace the student with the modified copy. A new `CommandResult` is returned with a success message showing the affected student and the attendance deleted.
5. The modified student replaces the outdated student in the `UniqueStudentList` and a success message is shown in the result display.

The following sequence diagram shows how the attendance deleting operation works.

<p align="center"><img src="images/DeleteAttendanceSequenceDiagram.png" align="center"/></p>
<div align="center">Figure 41: Delete Attendance Sequence Diagram</div><br>

The following activity diagram summarises the flow of events when `DeleteAttendanceCommand` is executed.

<p align="center"><img src="images/DeleteAttendanceActivityDiagram.png" align="center"/></p>
<div align="center">Figure 42: Delete Attendance Activity Diagram</div><br>

### 6.4 Schedule Command

This section describes the operations that `ScheduleViewCommand` performs.

1. Upon successful parsing of the user input, the `ScheduleViewCommand#execute(Model)` method is called.
2. The method `Model#setViewDate(LocalDate)` is then called to set the viewing date of the user in `SchedulePrefs`
3. Similarly, the method `ModelsetViewMode(ScheduleViewMode)` is called next to set the viewing mode (weekly/daily) of the user in `SchedulePrefs`.
4. After which, the method `updateFilteredStudentList(Predicate)` is called to get all the students.
The `Predicate` argument will be `PREDICATE_SHOW_ALL_STUDENTS` which is a reusable final predicate variable.
5. Thereafter, the method `Model#updateClassTimesToEvent()` will be called to translate all student's `ClassTime` to `LessonEvent`
6. The `Scheduler` then calls the method `resetData(List<Event>)` with the updated `LessonEvent` objects.
7. The `CommandResult` is then returned.

The following sequence diagram illustrates to execution of the `ScheduleViewCommand`:

<p align="center"><img src="images/ScheduleSequenceDiagram.png" align="center"/></p>
<div align="center">Figure 43: Schedule Sequence Diagram</div><br>

The following activity diagram summarizes the flow of events when the `ScheduleViewCommand` is being executed:

<p align="center"><img src="images/ScheduleActivityDiagram.png" align="center"/></p>
<div align="center">Figure 44: Schedule Activity Diagram</div><br>

<div markdown="block" class="alert alert-info">

:information_source: Figure 43 and 44 illustrates the `ScheduleCommand` execution within the `Logic` and `Model` Component.

</div>

For the `Ui` component, a calendar using  [jfxtras-icalendarfx](https://jfxtras.org/doc/8.0/jfxtras-icalendarfx/index.html) will be updated with the `LessonEvent` after the `CommandResult` is returned.
The `'LessonEvent` is provided to the `Ui` by the `LogicManager` through the `Model` component.
The `Model` in turns gets the `LessonEvent` from the `Scheduler` which keeps a list of updated events.
The calendar with `LessonEvent` is then displayed to the user through the interface. This is assuming that no exception arises.

The following are the various design choices made regarding the feature and alternatives that were considered prior to implementation.

* Current Implementation:
    * The current implementation creates `LessonEvent` from the `studentList` and update to the `Ui` whenever the `ScheduleViewCommand` is called.

* Alternatives Considered:
    * Creating a `Event` storage component that stores `LessonEvent` based on `Student`'s `ClassTime`.
    This would violate the data integrity of the `Student` we currently have and introduce additional complexity in
    maintaining both data structures.

### 6.5 Notebook feature

This section describes the implementation of the notebook feature and the various commands relating to notebook.

The notebook feature comprises three specific commands extending `NoteCommand`:

* `AddNoteCommand` – Adds a note to the notebook.
* `EditNoteCommand` – Edits a note in the notebook.
* `DeleteNoteCommand` – Deletes a note from the notebook

The following class diagram shows how the various commands relate to each other. (refer to [5.3 Logic Component](#53-logic-component) for higher-level details about the design)

<p align="center"><img src="images/NoteCommandClassDiagram.png" align="center"/></p>
<div align="center">Figure 45: Note Command Class Diagram</div><br>

#### 6.5.1 Add note command

This section describes how `AddNoteCommand` is performed.

1. Upon successful parsing of the user input, `AddNoteCommand#execute(Model model)` is called which checks whether there is already a note with the same title in `NotesList` using `Model#hasNote(Note toAdd)`.
2. If a duplicate note is found, a `CommandException` is thrown, and the note will not be added.
3. Otherwise, `Model#addNote(Note toAdd)` will be called, and the note will be added.
4. A `CommandResult` will be returned with the success message.

#### 6.5.2 Edit note command

This section describes how `EditNoteCommand` is performed.

1. Upon successful parsing of the user input, `EditNoteCommand#execute(Model model)` is called.
2. During the execution, `Model#getNotebook()` is called to retrieve the notebook in Reeve and `ReadOnlyNotebook#getNotesList()` is called from the notebook to retrieve the list of notes.
3. The `Index` is checked to ensure that it is valid; if it is not, a `CommandException` is thrown, and no note will be edited.
4. The note at `index` is retrieved, and a new note, `editedNote` is created based on this note and the `EditNoteDescriptor` stored within the `EditNoteCommand`.
5. `editedNote` is checked to ensure that it is not the same as the original note retrieved. A `CommandException` is thrown if it is the same note.
6. `Model#setNote(Note notetoEdit, Note editedNote)` is called to edit `noteToEdit` to `editedNote` in `model`.
7. A `CommandResult` will be returned with the success message.

#### 6.5.3 Delete note command

This section describes how `DeleteNoteCommand` is performed.

1. Upon successful parsing of the user input, `DeleteNoteCommand#execute(Model model)` is called.
2. During the execution, `Model#getNotebook()` is called to retrieve the `ReadOnlyNotebook` in Reeve and `ReadOnlyNotebook#getNotesList()` is called to retrieve the `List<Note>`.
3. The `Index` is checked to ensure that it is valid; if it is not, a `CommandException` is thrown, and no note will be deleted.
4. The `Note` at `Index` is removed from the notebook using `Model#deleteNote(Note noteToDelete)`.
5. A `CommandResult` will be returned with the success message.

## 7. **Documentation**
Refer to the [Documentation guide](Documentation.md).

## 8. **Logging**
Refer to the [Logging guide](Logging.md).

## 9. **Testing**
Refer to the [Testing guide](Testing.md).

## 10. **Configuration**
Refer to the [Configuration guide](Configuration.md).

## 11. **DevOps**
Refer to the [DevOps guide](DevOps.md).

## **Appendix A: Product Scope**

**Target user profile**:

* is a Singapore primary/secondary/junior college 1 to 1 private tutors
* has a need to manage a significant number of student contacts
* has a need to manage administrative details of students
* has a need to manage academic details of students
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Helps tutors organise administrative and academic details of their students with ease and manage student needs better.

## **Appendix B: User Stories**

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`
                                     
| Priority | As a …​                            | I want to …​                                       | So that I can…​                                                                     |
| -------- | ----------------------------------------------------------| ------------------------------------------------------- | ----------------------------------------------------------------------              |
| `* * *`  | private tutor ready to use Reeve                          | view a list of commands and how to use them             | learn how the application works or in case I forgot how some of the commands work   |
| `* * *`  | private tutor ready to use Reeve                          | add my students' details                                | store them and retrieve them whenever I need                                        |
| `* * *`  | private tutor                                             | view my students' preferred tutoring location           | figure out how to get that location                                                 |
| `* * *`  | private tutor                                             | edit my students' personal details                      | update outdated data                                                                |
| `* * *`  | private tutor                                             | view my student's details                               | refer to them when needed                                                           |
| `* * *`  | private tutor                                             | add additional details to each student                  | add other miscellaneous details which can allow me to better cater to student needs |
| `* * *`  | private tutor with many students                          | find a student's record                                 | retrieve students' data with ease                                                   |
| `* * *`  | private tutor                                             | find students who have not paid their fees              | remind students who have yet to pay me for my services                              |
| `* * *`  | private tutor who is a long-term user                     | delete students' data                                   | remove irrelevant data of students who are no longer my tutees                      |
|  `* *`   | private tutor                                             | record questions that my students raised                | find the answers to them after the lesson                                           |
|  `* *`   | private tutor                                             | record solutions to the questions raised                | use them as reference for answering future similar questions                        |
|  `* *`   | private tutor                                             | delete questions I do not need anymore                  | focus on the questions I need to pay attention to                                   |
|  `* *`   | private tutor                                             | input my student’s school test scores                   | keep track of their progress                                                        |
|  `* *`   | private tutor                                             | track my students' attendance                           | keep track of students' lesson records                                              |
|  `* *`   | private tutor                                             | input feedback to specific lessons                      | improve my capabilities as a tutor                                                  |
|  `* *`   | private tutor                                             | keep track of notes                                     | store small pieces of information in case I forget                                  |
|  `* *`   | private tutor                                             | view my tutoring schedule for a particular day          | plan ahead of my lessons for that day                                               |
|  `* *`   | private tutor                                             | be able to view students in alphabetical order          | look for students easily                                                            |
|  `* *`   | private tutor                                             | be able to my view my students in lesson time order     | know which students to prioritise when preparing lessons                            |
|  `* *`   | private tutor                                             | be able to group my students by their year of study     | look at questions and queries together for one cohort together                      |
|  `* *`   | private tutor                                             | view my tutoring schedule for a week                    | plan for the week ahead                                                             |
|   `*`    | private tutor                                             | view my students' academic progress                     | know which students need more help                                                  |
|   `*`    | private tutor ready to use Reeve                          | view the type of student details that are displayed     | focus on the details that I am currently concerned with                             |
|   `*`    | private tutor that is impatient                           | be able to get the command results in a reasonable time | save time                                                                           |
|   `*`    | private tutor that is using Reeve for the first time      | view Reeve with sample data                             | visualize how Reeve looks like when I use it                                        |

## **Appendix C: Use Cases**

For all use cases below, the **System** is `Reeve` and the **Actor** is the `Tutor (User)`, unless specified otherwise.
Use cases also assume that whenever an invalid command is entered by the user, Reeve displays an error message.

**UC01: Displaying help menu**

**MSS**

1. User enters a command to open help menu.
2. Reeve displays a success message and the help menu.

   Use case ends.

**UC02: Toggling displays between administrative and academic details of students**

**MSS**

1. User enters a command to toggle display students details.
2. Reeve displays a success message and the toggled display of students details.

   Use case ends.

**UC03: Exiting the application**

**MSS**

1. User enters a command to exit the application.
2. All processes of Reeve ends.

   Use case ends.


**UC04: Adding a student**

**MSS**

1.  User enters a command to add a student with student details.
2.  Reeve saves student data into the students list and displays a success message.

    Use case ends.

**Extensions**

* 1a. User provides input with missing compulsory fields.
    * 1a1. Reeve displays an error message.

      Use case resumes from step 1.

* 1b. User provides input with invalid format.
    * 1b1. Reeve displays an error message.

      Use case resumes from step 1.

**UC05: Listing all students**

**MSS**

1. User enters a command to list students.
2. Reeve displays the students list with student details.

   Use case ends.

**UC06: Editing a student's admin details**

**MSS**

1.  User enters a command to list students.
2.  Reeve shows the list of students.
3.  User enters command to edit a specific student in the list and provides needed parameters.
4.  Reeve updates the specified student with the input parameters and displays a success message.

    Use case ends.

**Extensions**

* 1a. The list is empty.
  Use case ends.

* 3a. User provides input with invalid index.
    * 3a1. Reeve requests for input with valid index.

      Use case resumes at step 2.
* 3b. User provides input without any parameters.
    * 3b1. Reeve requests for input with parameters.

      Use case resumes at step 2.
* 3c. User provides input with invalid format.
	* 3c1. Reeve requests for input with valid format.

	  Use case resumes at step 2.

**UC07: Deleting a student**

**MSS**

1.  User enters a command to list students.
2.  Reeve displays a list of students.
3.  User enters a command to delete a specific student in the list.
4.  Reeve deletes the student and displays a success message.

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 3a. User provides input with invalid index.
    * 3a1. Reeve displays an error message.

      Use case resumes at step 2.

**UC08: Searching for a student**

**MSS**

1.  User enters a command to find all students that match the given search parameter (name, school, year).
2.  Reeve displays all students matching the criteria.

    Use case ends.

**Extensions**

* 1a. User provides input with invalid data into the search parameter.
    * 1a1. Reeve displays erroneous field and expected format.

      Use case resumes at step 1.
* 1b. User provides input without a search parameter.
    * 1a1. Reeve displays a message indicating a search parameter was not provided.

      Use case resumes at step 1.
* 1c. No students match the given criteria.
    * 1c1. Reeve displays a message indicating no match found.

      Use case ends.

**UC09: Clearing all student records**

**MSS**

1. User enters a command to clear the students list.
2. Reeve displays a success message.

   Use case ends.
  
**UC10: Sorting the list of students**

**MSS**

1.  User enters a command to sort students by a given method (name, class time, year).
2.  Reeve sorts the list of students by the method.
3.  Reeve displays the student list in the new order

    Use case ends.

**Extensions**

* 1a. User provides input with invalid format.
    * 1a1. Reeve displays expected format.

      Use case resumes at step 1.
* 1b. User provides input without a search parameter.
    * 1a1. Reeve displays a message indicating a search parameter was not provided.

      Use case resumes at step 1.
* 1c. User provides invalid means to sort students.
    * 1c1. Reeve displays a message indicating that sorting means in valid and valid sorting means.

      Use case resumes at step 1.

**UC11: Adding an additional detail to a student**

**MSS**

1. User enters a command to list students.
2. Reeve displays a list of students.
3. User enters a command to add a detail to a specific student in the list.
4. Reeve updates the specified student in the list with the newly added detail.
5. Reeve displays a success message.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 3a. User provides input with an invalid student index.
    * 3a1. Reeve displays an error message.

      Use case resumes at step 2.

* 3b. User inputs a detail in an invalid format.
    * 3b1. Reeve displays an error message.

      Use case resumes at step 2.

**UC12: Editing an additional detail in a student**

**MSS**

1. User enters a command to list students.
2. Reeve displays a list of students.
3. User enters a command to edit a detail from a specific student in the list.
4. Reeve updates the specified student in the list with the edited detail.
5. Reeve displays a success message.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 3a. User provides input with an invalid student.
    * 3a1. Reeve displays an error message.

      Use case resumes at step 2.

* 3b. User provides input with an invalid detail.
    * 3b1. Reeve displays an error message.

      Use case resumes at step 2.

**UC13: Deleting an additional detail from a student**

**MSS**

1. User enters a command to list students.
2. Reeve displays a list of students.
3. User enters a command to delete a detail from a specific student in the list.
4. Reeve updates the specified student in the list with the removed detail.
5. Reeve displays a success message.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 3a. User provides input with an invalid student.
    * 3a1. Reeve displays an error message.

      Use case resumes at step 2.

* 3b. User provides input with an invalid detail.
    * 3b1. Reeve displays an error message.

      Use case resumes at step 2.

**UC14: Adding a question to a student**

**MSS**

1. User enters a command to list students.
2. Reeve displays a list of students.
3. User enters a command to add an unresolved question to a specific student in the list.
4. Reeve updates the specified student in the list with the newly added question.
5. Reeve displays a success message.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 3a. User provides input with an invalid student index.
    * 3a1. Reeve displays an error message.

      Use case resumes at step 2.

* 3b. User inputs a question in an invalid format.
    * 3b1. Reeve displays an error message.

      Use case resumes at step 2.

**UC15: Resolving a question from a student**

**MSS**

1. User enters a command to list students.
2. Reeve displays a list of students.
3. User enters a command to resolve a question from a specific student in the list with a solution.
4. Reeve updates the specified student in the list with the updated question.
5. Reeve displays a success message.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 3a. User provides input with an invalid student.
    * 3a1. Reeve displays an error message.

      Use case resumes at step 2.

* 3b. User provides input with an invalid question.
    * 3b1. Reeve displays an error message.

      Use case resumes at step 2.

* 3c. User inputs the solution in an invalid format.
    * 3c1. Reeve displays an error message.

      Use case resumes at step 2.

* 3d. User specifies a question that has already been solved.
    * 3d1. Reeve displays an error message.

      Use case resumes at step 2.


**UC16: Deleting a question from a student**

**MSS**

1. User enters a command to list students.
2. Reeve displays a list of students.
3. User enters a command to delete a question from a specific student in the list.
4. Reeve updates the specified student in the list with the removed question.
5. Reeve displays a success message.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 3a. User provides input with an invalid student.
    * 3a1. Reeve displays an error message.

      Use case resumes at step 2.

* 3b. User provides input with an invalid question.
    * 3b1. Reeve displays an error message.

      Use case resumes at step 2.

**UC17: Finding all students with overdue tuition fees**

**MSS**

1. User enter command to filter all students by those who have not paid their fees in the past month.
2. Reeve displays all students that match the above criteria.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 1b. All students have paid their fees in the past month.

  * 1b1. Reeve displays an empty list.

    Use case ends.

**UC18: Adding an exam record to a student**

**MSS**

1. User enters a command to list students.
2. Reeve displays a list of students.
3. User enters a command to add an exam record to a specific student in the list.
4. Reeve updates the specified student in the list with the newly added exam record.
5. Reeve displays a success message.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 3a. User provides input with an invalid student index.
    * 3a1. Reeve displays an error message.

      Use case resumes at step 2.

* 3b. User inputs an exam record in an invalid format.
    * 3b1. Reeve displays an error message.

      Use case resumes at step 2.

**UC19: Deleting an exam record from a student**

**MSS**

1. User enters a command to list students.
2. Reeve displays a list of students.
3. User enters a command to delete a specific exam record from a specific student in the list.
4. Reeve updates the specified student in the list with the removed question.
5. Reeve displays a success message.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 3a. User provides input with an invalid student.
    * 3a1. Reeve displays an error message.

      Use case resumes at step 2.

* 3b. User provides input with an invalid exam record.
    * 3b1. Reeve displays an error message.

      Use case resumes at step 2.

**UC20: Displaying exam statistics of a student**

**MSS**

1. User enters a command to list students.
2. Reeve displays a list of students.
3. User enters a command to view the exam statistics on a specific student.
4. Reeve displays a success message and the exam statistics.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 3a. User provides input with an invalid student.
    * 3a1. Reeve displays an error message.

      Use case resumes at step 2.

**UC21**: View class schedule.

**MSS**

1. User enters command to view schedule.
2. Reeve shows the schedule of the user's classes.

    Use case ends.
    
**Extensions**

* 1a. User enters command in an incorrect format.
    1a1. Reeve displays error message.
    1a2. User corrects command input.
    
    Use case resumes at step 2.
    
* 1b. There are no student in Reeve.
    1b1. Schedule is shown with no classes.
    
    Use case ends.

**UC22: Adding an attendance record to a student**

**MSS**

1. User enters a command to list students.
2. Reeve displays a list of students.
3. User enters a command to add an attendance record to a specific student in the list.
4. Reeve updates the specified student in the list with the newly added attendance record.
5. Reeve displays a success message.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 3a. User provides input with an invalid student index.
    * 3a1. Reeve displays an error message.

      Use case resumes at step 2.

* 3b. User inputs attendance with an invalid format.
    * 3b1. Reeve displays an error message.

      Use case resumes at step 2.

**UC23: Deleting an attendance record from a student**

**MSS**

1. User enters a command to list students.
2. Reeve displays a list of students.
3. User enters a command to delete an attendance record from a specific student in the list.
4. Reeve updates the specified student in the list with the removed attendance record.
5. Reeve displays a success message.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 3a. User provides input with an invalid student.
    * 3a1. Reeve displays an error message.

      Use case resumes at step 2.

* 3b. User provides input with an invalid attendance record.
    * 3b1. Reeve displays an error message.

      Use case resumes at step 2.
 
**UC24: Adding a note to the notebook**

**MSS**

1. User enters a command to add a note to the notebook.
2. Reeve saves the note into the notebook and displays a success message

* 1a. User provides input with missing fields.
    * 1a1. Reeve displays an error message.

      Use case resumes at step 1.

* 1b. User provides invalid input.
    * 1b1. Reeve displays an error message and input constraints.

      Use case resumes at step 1.

**UC25: Editing a note in the notebook**

**MSS**

1.  User enters command to edit a specific note in the notebook and provides needed parameters.
2.  Reeve updates the specified note with the input parameters and displays a success message.

    Use case ends.

**Extensions**

* 1a. User provides input with invalid index.
    * 1a1. Reeve displays an error message and requests for input with valid index.

      Use case resumes at step 1.
* 1b. User provides input without any parameters.
    * 1b1. Reeve requests for input with parameters.

      Use case resumes at step 1.
* 1c. User provides input with invalid format.
	* 1c1. Reeve requests for input with valid format.

	  Use case resumes at step 1.

**UC26: Editing a note in the notebook**

**MSS**

1.  User enters command to edit a specific note in the notebook and provides needed parameters.
2.  Reeve updates the specified note with the input parameters and displays a success message.

    Use case ends.

**Extensions**

* 1a. User provides input with invalid index.
    * 1a1. Reeve displays an error message and requests for input with valid index.

      Use case resumes at step 1.
* 1b. User provides input without any parameters.
    * 1b1. Reeve requests for input with parameters.

      Use case resumes at step 1.
* 1c. User provides input with invalid format.
	* 1c1. Reeve requests for input with valid format.

	  Use case resumes at step 1.

## **Appendix D: Non-Functional Requirements**

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  The response to any use action should become visible within 5 seconds.
3.  The program should be able to handle at least 100 students (in practice, our target audience should not require as high a capacity, but this is a buffer to ensure it will not fail).
4.  The graphical user interface should be easy to use for non-IT savvy users.
5.  The program should be able to run even without internet connection.

## **Appendix E: Glossary**

The following table provides the definitions of the various terms used in this Developer Guide.

Term | Definition
--------|------------------
Mainstream OS | Refers to Windows, Linux, Unix, OS-X
Private contact detail | A contact detail that is not meant to be shared with others

## **Appendix F: Instructions for Manual Testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">
:information_source: These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### F.1 Launching Reeve

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample students. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.
       
### F.2 General Features

1. Opening help window.

    1. Test case: `help` when no help window is opened.
    <br>Expected: Expected: Help window opens.
    
    1. Test case: `help` when help window is already opened.
    <br>Expected: Expected: Brings up already opened help window.
    
2. Toggling between student details.

    1. Test case: `toggle` when students display administrative details.
    <br>Expected: Expected: Students switch to display academic details.

    1. Test case: `toggle` when students display academic details.
    <br>Expected: Expected: Students switch to display administrative details.    
   
3. Exiting Reeve.
    1. Test case: `exit`.
    <br>Expected: Reeve shuts down. 
    
### F.3 Student Administrative Features

1. Adding a student with administrative details to the students list.
    
    1. Test case: `add n/Brendan Tan p/93211234 s/Commonwealth Secondary School y/pri 6 v/Blk 33 West Coast Rd #21-214 t/5 1430-1630 f/25 d/10/10/2020`
    <br>Expected: Expected: Student Brendan Tan has been added to the students list.

    1. Test case: `add n/Brendan Tan p/93211234 s/Commonwealth Secondary School v/Blk 33 West Coast Rd #21-214 t/5 1430-1630 f/25 d/50/50/2020 a/Likes Algebra`
    <br>Expected: Expected: No student is added as due to invalid payment date. Error details displayed in the result display.

    1. Test case: `add n/Brendan Tan p/93211234 s/Commonwealth Secondary School v/Blk 33 West Coast Rd #21-214 t/15 1430-1630 f/25 d/10/10/2020 a/Likes Algebra`
    <br>Expected: Expected: No student is added as due to invalid class time. Error details displayed in the result display.

1. Deleting a student while all students are being shown.

   1. Prerequisites: At least one student in the students list.

   1. Test case: `delete 1`
   <br>Expected: First student is deleted from the students list. Details of the deleted student shown in the result display.

   1. Test case: `delete 0`
   <br>Expected: No student is deleted. Error details shown in the result display.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. Finding students with overdue fees (i.e last payment date is more than one month ago) while all students are shown.

   1. Prerequisite: Set at least one student's last payment date to within one month of the current date with `edit` command. Multiple students in the list.

   1. Test case: `overdue`<br>
      Expected: All students except those whose payment date is within one month from the current date are listed. The number of students found is displayed.

1. Finding students with overdue fees while some students are hidden.

   1. Prerequisite: Hide some students with overdue fees with `find` command.

   1. Test case: `overdue`<br>
      Expected: All students except those whose payment date is within one month from the current date are displayed again. The number of students found is displayed.

1. Viewing schedule of classes

    1. Prerequisites: There are students stored in Reeve currently with non-overlapping class times.

    1. Test case: `schedule m/weekly d/02/11/2020`
       Expected: Shows the schedule of classes in the whole week of 02/11/2020.

    1. Test case: `schedule m/daily d/02/11/2020`
       Expected: Shows the schedule of classes in the day of 02/11/2020.

    1. Test case: `schedule m/dAiLy d/02/11/2020`
       Expected: Shows the schedule successfully as the case letters of the view mode does not matter.

    1. Test case: `schedule m/day d/02/11/2020`
       Expected: Displays error message indicating invalid view mode.

    1. Test case: `schedule m/weekly d/02-11-2020`
       Expected: Displays error message indicating invalid date format.

1. Finding a student while all students are being shown with several matching students

   1. Prerequisites: List all students using the `list` command. Two students in the list. One student has name _Samuel_ and has school _yishun secondary_. Another student has name _Sam_ and has school _yishun sec school_.

   1. Test case: `find n/samuel`<br>
      Expected: Only the student with name _Samuel_ and school _yishun secondary_ shows up in the list.

   2. Test case: `find n/sam`<br>
      Expected: Only the student with name _Sam_ and school _yishun sec school_ showsinp on the list.

   3. Test case: `find s/yishun sec`<br>
      Expected: Both students show up in the list.

   4. Incorrect find commands to try: `find`, `find samuel`, `find yishun sec`
      Expected: No changes show up on the list. Error details shown in the status message.

1. Adding details to a student.

    1. Prerequisite: List all students using `list` command. Multiple students present.

    1. Test case: `detail add 1 t/DETAIL_TEXT`
       Expected: New detail that matches your input `DETAIL_TEXT` added to first student.

    1. Test case: `detail add 0 t/DETAIL_TEXT`
       Expected: No detail added. Error details shown in status message. List stays the same.

    1. Test case: `detail add 1 t/`
       Expected: Similar to previous.

    1. Test case: `detail add t/DETAIL_TEXT`
       Expected: Similar to previous.

1. Editing details for a student.

    1. Prerequisite: List all students using `list` command. Multiple students present. Student to be edited has at least one detail.

    1. Test case: `detail edit 1 i/1 t/DETAIL_TEXT`
       Expected: New detail that matches your input `DETAIL_TEXT` replaces the first detail for first student.

    1. Test case: `detail edit 0 i/1 t/DETAIL_TEXT`
       Expected: No detail edited. Error details shown in status message. List stays the same.

    1. Test case: `detail edit 1 i/1 t/`
       Expected: Similar to previous.

    1. Test case: `detail edit 1 i/ t/DETAIL_TEXT`
       Expected: Similar to previous.

    1. Test case: `detail edit i/1 t/DETAIL_TEXT`
       Expected: Similar to previous.

1. Deleting details from a student.

    1. Prerequisite: List all students using `list` command. Multiple students present. Student to be edited has at least one detail.
       
    1. Test case: `detail delete 1 i/1`
       Expected: First detail in first student is deleted.
           
    1. Test case: `detail delete 0 i/1`
       Expected: No detail deleted. Error details shown in status message.

    1. Test case: `detail delete i/1`
       Expected: Similar to previous.
       
    1. Test case: `detail delete`
       Expected: Similar to previous.

### F.4 Student Academic Features

1. Adding an exam record to a student. 

   1. Prerequisites: At least one student in the students list.
   
   1. Test case: `exam add 1 n/Mid Year 2020 d/08/12/2020 s/40/60`
   <br>Expected: Mid Year 2020 exam record is added to the exams list of the first student in the displayed students list.
   
   1. Test case: `exam add 1 n/Mid Year 2020 d/08/12/2020 s/50/10`
   <br>Expected: No exam record is added due to invalid score as numerator is larger than denominator.
   
   1. Test case: `exam add 1 n/Mid Year 2020 d/30/30/2020 s/50/100`
   <br>Expected: No exam record is added due to invalid exam date.
   
1. Deleting an exam record from a student.

   1. Prerequisites: At least one student in the students list. At least one exam record in the student's exams list.
   
   1. Test case: `exam delete 1 i/1`
   <br>Expected: First exam record of the exam list of the first student in the displayed students list is deleted. Details of deleted exam record shown in the result display.
   
   1. Test case: `exam delete 1 i/0`
   <br>Expected: No exam is deleted due to invalid exam index. Error details shown in the result display.
   
1. Displaying exam statistics of a student

   1. Prerequisites: At least one student in the students list.

   1. Test case: `exam stats 1`
   <br>Expected: Opens the exam statistics window of the first student in the displayed students list.

1. Adding attendance to a student.

    1. Prerequisite: List all students using `list` command. Multiple students present.

    1. Test case: `attendance add 1 d/12/02/2020 a/present`
       Expected: New attendance that matches the input date and status is added to the first student.

    1. Test case: `attendance add 1 d/12/02/2020 a/present f/attentive`
       Expected: New attendance that matches the input date, status and feedback is added to the first student.

    1. Test case: `attendance add 1 d/12/02/2020 a/pre`
       Expected: No attendance added. Error details shown in status message. List stays the same.

    1. Test case: `attendance add 1 d/12/02 a/present`
       Expected: Similar to previous.

1. Deleting attendance record from a student.

    1. Prerequisite: List all students using `list` command. Multiple students present. Student to be edited has at least one attendance record.
       
    1. Test case: `attendance delete 1 d/12/02/2020`
       Expected: Attendance record that has the input date in the first student is deleted.
           
    1. Test case: `attendance delete 1 d/12/02`
       Expected: No attendance deleted. Error details shown in status message.

    1. Test case: `attendance delete d/12/02/2020`
       Expected: Similar to previous.

1. Adding questions to a student.

    1. Prerequisite: List all students using `list` command. View academic data with `toggle` command. Multiple students present.

    1. Test case: `question add 1 t/RANDOM_QUESTION`
       Expected: New unresolved (cross symbol) question whose description matches your input added to first student.

    1. Test case: `question add 0 t/RANDOM_QUESTION`
       Expected: No question added. Error details shown in status message. List stays the same.

    1. Test case: `question add 1 t/   `
       Expected: Similar to previous.

    1. Test case: `question add 1 t/SAME_QUESTION_IN_TEST_CASE_2`
       Expected: Similar to previous.

1. Resolving questions from a student.

    1. Prerequisite: List all students using `list` command. View academic data with `toggle` command. First student on the list must have at least one unresolved question.

    1. Test case: `question solve 1 i/UNSOLVED_QUESTION_INDEX t/   `
       Expected: No question resolved. Error details shown in status message.

    1. Test case: `question solve 1 i/UNSOLVED_QUESTION_INDEX t/RANDOM_SOLUTION`
       Expected: Status of question at `UNSOLVED_QUESTION_INDEX` changed to a tick, `RANDOM_SOLUTION` added next to the question text in square brackets (`[]`).
       Details of the question resolved and student's name included in status message.

    1. Test case: `question solve 0 i/1 t/RANDOM_SOLUTION`
       Expected: No question resolved. Error details shown in status message.

    1. Test case: `question solve 1 i/0 t/RANDOM_SOLUTION`
       Expected: Similar to previous.

    1. Test case: `question solve 1 i/INDEX_OF_QUESTION_SOLVED_IN_TEST_CASE_2 t/RANDOM_SOLUTION`
       Expected: Similar to previous.

1. Deleting questions from a student.

    1. Prerequisite: List all students using `list` command. View academic data with `toggle` command. First student on the list must have at least one question.

    1. Test case: `question delete 0 i/1`
       Expected: No question deleted. Error details shown in status message.

    1. Test case: `question delete 1 i/0`
       Expected: Similar to previous.
       
    1. Test case: `question delete 1 i/QUESTION_INDEX`
       Expected: Question at `QUESTION_INDEX` is removed. Details of deleted question included in status message.

### F.5 Notebook Feature

1. Adding a note to the notebook.
    
    1. Test case: `note add t/things to do d/mark practice papers`
    <br>Expected: Note with title things to do is added and details shown in the results display.

    1. Test case: `note add t/things to finish by tomorrow d/mark practice papers`
    <br>Expected: No note is added due to a title that is too long. Error details shown in the results display.

1. Deleting a note.

   1. Prerequisites: At least one note in the notebook.

   1. Test case: `note delete 1`
   <br>Expected: First note is deleted from the notebook. Details of the deleted note shown in the result display.

   1. Test case: `note delete 0`
   <br>Expected: No note is deleted. Error details shown in the result display.

### F.6 Saving Data

1. Dealing with missing data files

   1. Test case: `data/addressbook.json` missing or deleted<br>
      Expected: Reeve initialises with sample student data, notebook data is intact.

   1. Test case: `data/notebook.json` missing or deleted<br>
      Expected: Reeve initialises with sample notebook data, student data is intact.

1. Dealing with corrupted data files (Student data)

   1. Prerequisite: Ensure `data/addressbook.json` is present. Modify the data using `edit` or `delete` to create `data/addressbook.json` if absent.

   1. Test case: delete a random field from a random student in `addressbook.json`<br>
      Expected: Reeve initialises with an empty student list.

   1. Test case: duplicate a student's record again in `addressbook.json`<br>
      Expected: Similar to previous.

   1. Test case: invalid data in `addressbook.json` (e.g add special characters to the "name" field) <br>
      Expected: Similar to previous.

1. Dealing with corrupted data files (Notebook data)

   1. Prerequisite: Ensure `data/notebook.json` is present. Modify the data using note commands to create `data/notebook.json` if absent.

   1. Test case: "description" field has more than 80 characters<br>
      Expected: Reeve initialises with an empty notebook.

   1. Test case: "title" field has more than 15 characters<br>
      Expected: Reeve initialises with an empty notebook.

   1. Test case: duplicate note in `notebook.json`<br>
      Expected: Similar to previous.
