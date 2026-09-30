# JavaFX tutorial submission

Implementation of the [SE-EDU JavaFX tutorial](https://se-education.org/guides/tutorials/javaFx.html).

## Run and verify

Use JDK 25 (the JavaFX-bundled JDK prescribed by the tutorial on macOS).

```sh
./gradlew run
./gradlew clean build smokeTest
java -jar build/libs/javafx-tutorial-duke.jar
```

`smokeTest` requires a graphical desktop. It opens the real JavaFX window, verifies both input actions, reply content and alignment, command styles, and resize settings, then closes the window. The application is the tutorial's echo chatbot: task-like inputs demonstrate color categories without creating or modifying actual tasks.

## Submission milestones

| Tag | Working milestone |
| --- | --- |
| `Tutorial-Part1` | Hello World application and launch smoke test |
| `Tutorial-Part2` | Java-built chat layout, reusable dialog box, bundled avatars |
| `Tutorial-Part3` | Send and Enter handlers, echoed replies, flipped dialogs, automatic scrolling |
| `Tutorial-Part4` | FXML views and controllers, backend injection, working resource paths |
| `Tutorial-Part5` | Completed GUI tweaks and final integration checks |
| `Tutorial-Part5-Tweaks` | Explicit checkpoint for the implemented tweaks below |

The original work is preserved in Git history. New sequential checkpoints complete and verify the earlier placeholder milestones; tags refer to these working revisions, rather than to incomplete historical commits. Each milestone includes its matching GUI smoke test.

## Part 5 tweaks

The implementation covers more than half of Part 5's layout, styling, and enhancement topics:

- Anchor the input, Send button, and scroll pane; fit chat content to the viewport width.
- Set minimum window dimensions.
- Link separate main-window and dialog CSS files to FXML.
- Flip the reply bubble corners using a dedicated style class.
- Set message padding and margins.
- Customize border widths, colors, and corner radii.
- Use RGB, hexadecimal, named, looked-up, derived, and contrast-dependent colors.
- Style button hover and pressed states.
- Add avatar drop shadows.
- Apply reply colors for `todo`/`deadline`/`event`, `mark`/`unmark`, and `delete` inputs.

Background-image variations are not implemented. An unrecognized input resets the command category, so the previous reply's style does not carry over.

## Assets

The bundled avatars are the examples supplied by the tutorial:
[DaUser.png](https://se-education.org/guides/tutorials/images/javafx/DaUser.png) and
[DaDuke.png](https://se-education.org/guides/tutorials/images/javafx/DaDuke.png).
