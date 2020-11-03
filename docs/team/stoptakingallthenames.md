---
layout: page
title: Ong Ying Gao's Project Portfolio Page
---

## Project: Reeve

Reeve is a desktop companion application for one-on-one private tutors designed to help them better manage their students' academic and administrative needs.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Feature Addition**: Added the ability to find all students with overdue tuition fees.
  * What it does: Allows the user to find all students who last paid their tuition fees more than a month ago.
  * Justification: This feature improves the product significantly because it makes it more convenient for the user to handle payment matters better without having to individually check each student.

* **Feature Addition**: Added the ability to record and manage questions from students.
  * What it does: Allows the user to log questions from students, resolve them and delete them when no longer needed.
  * Justification: This helps tutors significantly as it allows them to keep track of questions from their students and gives them advanced notice to resolve them.
    Furthermore, the ability to resolve it with a solution allows tutors to use the solution to answer future similar questions from students.

* **Code contribution**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=W15-2&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=StopTakingAllTheNames&tabRepo=AY2021S1-CS2103T-W15-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed releases `v1.2` and `v1.3` (2 releases) on GitHub

* **Enhancements to existing features**:
  * Added support for storing tuition fee and payment date information in a student's records.
  * Wrote additional tests for existing and added features to increase coverage from 72% to 79% ([\#75](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/75)).
  * Enhanced code quality to ensure adherence to abstraction levels, improve defensiveness and decouple modules ([\#103](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/103) and [\#194](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/194)).

* **Documentation**:
  * User Guide:
    * Added documentation for the features `overdue` ([\#79](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/79)) and `question` ([\#71](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/103)).
    * Did cosmetic tweaks to existing documentation of features `list`, `delete`, `clear`, `attendance` and `exit`: ([\#140](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/140)).
  * Developer Guide:
    * Added implementation details of the `overdue` ([\#105](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/105)) and `question` ([\#103](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/103)) features.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#60](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/60), [\#62](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/62), [\#68](https://github.com/AY2021S1-CS2103T-W15-2/tp/pull/68)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/StopTakingAllTheNames/ped/issues/1), [2](https://github.com/StopTakingAllTheNames/ped/issues/5), [3](https://github.com/StopTakingAllTheNames/ped/issues/2))
