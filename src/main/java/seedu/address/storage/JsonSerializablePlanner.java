package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Planner;
import seedu.address.model.ReadOnlyPlanner;
import seedu.address.model.student.Student;

/**
 * An Immutable Planner that is serializable to JSON format.
 */
@JsonRootName(value = "planner")
class JsonSerializablePlanner {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Student list contains duplicate student(s).";

    private final JsonAdaptedStudent activeStudent;
    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedModuleCode> enrolledModules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePlanner} with the given persons.
     */
    @JsonCreator
    public JsonSerializablePlanner(@JsonProperty("activeStudent") JsonAdaptedStudent activeStudent,
                                   @JsonProperty("students") List<JsonAdaptedStudent> students,
                                   @JsonProperty("enrolledModules") List<JsonAdaptedModuleCode> enrolledModules) {
        this.activeStudent = activeStudent;
        this.students.addAll(students);
        this.enrolledModules.addAll(enrolledModules);
    }

    /**
     * Converts a given {@code ReadOnlyPlanner} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePlanner}.
     */
    public JsonSerializablePlanner(ReadOnlyPlanner source) {
        if (source.getActiveStudent() == null) {
            activeStudent = null;
        } else {
            activeStudent = new JsonAdaptedStudent(source.getActiveStudent());
        }
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        //enrolledModules.addAll(source.getEnrolledModulesList().stream().map(JsonAdaptedModuleCode::new)
        //        .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code Planner} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Planner toModelType() throws IllegalValueException {
        Planner planner = new Planner();

        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (planner.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            planner.addStudent(student);
        }
        if (activeStudent != null) {
            planner.setActiveStudent(activeStudent.toModelType());
        }
        return planner;
    }

}
