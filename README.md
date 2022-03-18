---------------------------------------------------------------------
Getting Started
Running
"gradle run"

Testing
"gradle test"

Test Report
"gradle build jacocoTestReport"
---------------------------------------------------------------------
Implemented features
1. Level Transition
2. Squarecat
3. Score
4. Save/Load
---------------------------------------------------------------------
Observer Pattern: ./ballboy/model/observer
BlueObserver | GreenObserver | RedObserver | PreviousObserver | LevelObserver | ChangeScore | Observer | Subject
Corresponding files: ./ballboy/view/Labels
AddLabel | BlueLabel | CreateLabel | CurrentLabel | GreenLabel | PreviousLabel | RedLabel

Memento Pattern: ./ballboy/model/Memento
CareTaker | CreateMemento | Memento | Originator
---------------------------------------------------------------------


