package seedu.planner.model.programmes.specialisations.cs;

import java.util.ArrayList;
import java.util.Objects;

import seedu.planner.model.module.ModuleCode;

/**
 * Class to represent the Artificial Intelligence Specialisation for Computer Science Degree Programme, specifying the
 * primaries and electives which are associated with the specialisation.
 */
public class ArtificialIntelligenceSpecialisation extends GenericCsSpecialisation {

    public static final String NAME = "Artificial Intelligence";

    /**
     * Default constructor for {@code ArtificialIntelligenceSpecialisation} specifying the {@code ModuleCode} for
     * primaries and electives.
     */
    public ArtificialIntelligenceSpecialisation() {
        primaries = new ArrayList<>();
        electives = new ArrayList<>();

        primaries.add(new ModuleCode("CS3243"));
        primaries.add(new ModuleCode("CS3244"));
        primaries.add(new ModuleCode("CS4243"));
        primaries.add(new ModuleCode("CS4244"));
        primaries.add(new ModuleCode("CS4246"));
        primaries.add(new ModuleCode("CS4248"));

        electives.add(new ModuleCode("CS4220"));
        electives.add(new ModuleCode("CS4261"));
        electives.add(new ModuleCode("CS4269"));
        electives.add(new ModuleCode("CS4277"));
        electives.add(new ModuleCode("CS4278"));
        electives.add(new ModuleCode("CS5215"));
        electives.add(new ModuleCode("CS5228"));
        electives.add(new ModuleCode("CS5242"));
        electives.add(new ModuleCode("CS5260"));
        electives.add(new ModuleCode("CS5340"));
        electives.add(new ModuleCode("CS5339"));
    }

    /**
     * Returns the Name of the specialisation.
     *
     * @return The Name of the specialisation.
     */
    public String getName() {
        return NAME;
    }

    /**
     * Returns the Name of the specialisation.
     *
     * @return The Name of the specialisation.
     */
    public String toString() {
        return getName();
    }

    /**
     * Returns the hash of the current Specialisation.
     *
     * @return Hash of the current Specialisation.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }

    /**
     * Checks if a given object is the same as the current object.
     *
     * @param obj Object to inspected.
     * @return boolean True if same, False if different.
     */
    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == this.getClass();
    }

}
