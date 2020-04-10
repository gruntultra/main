package seedu.planner.logic.commands.timetable;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.planner.commons.core.Messages;
import seedu.planner.logic.commands.CommandResult;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.model.Model;
import seedu.planner.model.student.Student;
import seedu.planner.model.time.StudentSemester;


//@@author thetruevincentchow
/**
 * Lists all timetables in the currently selected student's timetable list.
 */
public class TimeTableListCommand extends TimeTableCommand {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = getQualifiedCommand(COMMAND_WORD)
        + ": List the timetables of the active student.\n"
        + "Example: " + getQualifiedCommand(COMMAND_WORD);

    public static final String MESSAGE_SUCCESS = "Listed semesters for the selected student (%1$s):\n%2$s";

    /**
     * Generates a command execution success message for listing the timetables of
     * the currently selected student.
     */
    private String generateSuccessMessage(Student activeStudent, List<StudentSemester> semesters) {
        return String.format(MESSAGE_SUCCESS, activeStudent, semesters);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Student activeStudent = model.getActiveStudent();
        if (activeStudent == null) {
            throw new CommandException(Messages.MESSAGE_NO_STUDENT_ACTIVE);
        }

        return new CommandResult(generateSuccessMessage(activeStudent, activeStudent.getStudentSemesters()));
    }
}
//@@author
