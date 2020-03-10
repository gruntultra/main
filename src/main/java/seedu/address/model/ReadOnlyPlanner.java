package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.model.student.Student;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyPlanner {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */

    Student getActiveStudent();

    ObservableList<Student> getStudentList();

    ObservableList<Module> getModuleList();


    UniqueModuleList getModules();

    ObservableList<ModuleCode> getEnrolledModulesList();


}
