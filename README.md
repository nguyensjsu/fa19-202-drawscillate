# Drawscillate

[User Story Video](https://youtu.be/8FFxvjQ4u8Q)

## Topic

Drawscillate: An interactive audio-visual game.

## Team 

* [Aditya Agrawal](https://github.com/agrawaladit)
* [Andrew Selvia](https://github.com/AndrewSelvia)
* [Hetal Shah](https://github.com/ihetal)
* [Rajeev Sebastian](https://github.com/RajeevSebastian)
* [Shraddha Nayak](https://github.com/shraddhanayak07)

## Summary

Drawscillate enables you to play with sound & color to express your creativity. Harking back to the classic Buzz Wire game, this modern interpretation demands your focus as you attempt to trace a simple shape (like a heart or a star). Along the way, use the keyboard to switch colors and play with the oscillating sounds that react to your movements. However you choose to play, we sincerely hope you have fun! 

## Key Features

* Choose from 4 shapes: Star, Rectangle, Heart, Circle
* Choose from 3 difficulties: Easy, Normal, Hard
* Draw a line with your mouse
* Switch between 7 different colors while drawing: Black, Blue, Green, Orange, Purple, Red, Yellow
* Alter the oscillation of a background sound as you draw
* Trace within the boundaries of the shape from start to finish to win the game
* Share your finished artwork with the auto-generated picture of your completed drawing

## Contributions

### [Aditya Agrawal](https://github.com/agrawaladit)

* 

### [Andrew Selvia](https://github.com/AndrewSelvia)

#### Idea Generation

I experimented with various game ideas (Pac-Man, Sudoku, etc.) and tools (Greenfoot, Processing, etc.) until I found something original and achievable within a two-week timeframe. Then, I created our *Product Backlog* on GitHub with dozens of issues which allowed us to plan our sprints and stay focused on the most important tasks.

#### Scrum Master

One of the primary responsibilities of a scrum master is to remove impediments. The following contributions exemplify how I scouted ahead for our team to enable us to move with as much velocity as possible:
* Demonstrated the core [drawing](https://github.com/nguyensjsu/fa19-202-drawscillate/commit/3eeb721596dcbf29653d4d1ae4e11bc2eccf41fc) & [sound](https://github.com/nguyensjsu/fa19-202-drawscillate/commit/bed4f86bafa857d8a4e6261b7c5beb36b1bdceb9) APIs we would use to assemble our game
* Enabled us to [develop in IDEs other than Processing](https://github.com/nguyensjsu/fa19-202-drawscillate/commit/6b66e65cec595e057bc1e2d2cd49e947969db246) (i.e. Eclipse, IntelliJ IDEA), thereby leveraging skills we have built up in those environments and avoid the awkward ramifications of using Processing (i.e. .pde files rather than plain Java)
* Observed during our daily stand up meetings during Sprint 2 that we were all approaching a bottleneck of bad design (Drawscillate.java was a god class), so I bit the bullet to [deliver a well-designed refactoring into multiple screens](https://github.com/nguyensjsu/fa19-202-drawscillate/pull/62) so the team could keep making progress over the remaining few days of the sprint
* Added a [CI/CD pipeline with *spotbugs* & *smartsmells*](https://github.com/nguyensjsu/fa19-202-drawscillate/pull/97) to provide us insights into our code's quality during development

In addition, I coordinated daily stand up meetings, retrospectives, and contributed significantly to our [User Story Video](https://youtu.be/8FFxvjQ4u8Q).

### [Hetal Shah](https://github.com/ihetal)



### [Rajeev Sebastian](https://github.com/RajeevSebastian)



### [Shraddha Nayak](https://github.com/shraddhanayak07)



## Scrum

[Sprint Task Sheet](https://docs.google.com/spreadsheets/d/1EVMsOQ3g3JuIVDFdPkGSJBxW_JH25RkZzY6yD2SRak4)

![Sprint 1 Burndown Chart](Sprint1BurndownChart.png)

### Sprint Retrospectives

* [Sprint 1 Retrospective](https://youtu.be/xQuffx0hzCM)

### Daily Standup Recordings

* [11/18](https://youtu.be/TQFxYSvY6BM)
* [11/21](https://youtu.be/F1KQlH5aFno)

## Building & Running

We suggest building & running the app through through a general-purpose IDE (like IntelliJ IDEA). If you're using macOS, make sure to build & run with JDK 8; newer versions may fail according to [this source](https://discourse.processing.org/t/keep-getting-noclassdeffounderror-errors-on-mac/11727).
