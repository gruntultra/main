package seedu.planner.model.graduation;

import java.util.List;

import seedu.planner.model.module.ModuleCode;
import seedu.planner.model.programmes.specialisations.GenericSpecialisation;
import seedu.planner.model.programmes.specialisations.cs.GenericCsSpecialisation;

/**
 * Class that implements {@code GraduationRequirement}, specific for CS Focus Areas. Accounts for each Focus Area's
 * primaries
 */

public class FocusAreaGraduationRequirement extends GraduationRequirement {

    /**
     * {@code GenericSpecialisation} object to store the current {@code Specialisation} to be used for fulfillment
     * calculation
     */
    private GenericSpecialisation specialisation = null;

    /**
     * Default constructor for {@code FocusAreaGraduationRequirement}
     *
     * @param specialisation Specialisation to be used for fulfillment calculation
     */
    public FocusAreaGraduationRequirement(GenericSpecialisation specialisation) {
        this.specialisation = specialisation;
    }

    /**
     * Sets the current specialisation to be used for fulfillment calculation
     *
     * @param specialisation Specialisation to be used for fulfillment calculation
     */
    public void setSpecialisation(GenericSpecialisation specialisation) {
        this.specialisation = specialisation;
    }

    /**
     * Returns a boolean representing if the {@code FocusAreaGraduationRequirement} is fulfilled, given a list of
     * {@code ModuleCode}
     *
     * @param moduleCodes List of {@code ModuleCode}
     * @return True if fulfilled. False otherwise.
     */
    public boolean isFulfilled(List<ModuleCode> moduleCodes) {
        if (specialisation == null) {
            return false;
        }
        return specialisation.isFulfilled(moduleCodes);
    }

    /**
     * Gets the current specialisation used for fulfillment calculation
     *
     * @return Specialisation to be used for fulfillment calculation
     */
    public GenericSpecialisation getSpecialisation() {
        return specialisation;
    }

    /**
     * Returns a String representation of the {@code FocusGraduationRequirement} object, along with it's child
     * {@code GraduationRequirement}s
     *
     * @param moduleCodes List of {@code ModuleCode}
     * @return A String representation of the {@code FocusGraduationRequirement} object, along with it's child
     */
    public String getString(List<ModuleCode> moduleCodes) {
        try {
            StringBuilder sb = new StringBuilder()
                    .append("[")
                    .append(getStatusIcon(specialisation.isFulfilled(moduleCodes)))
                    .append("] " + "Focus Area: ")
                    .append(specialisation.getName())
                    .append("\n    [")
                    .append(
                            getStatusIcon(((GenericCsSpecialisation) specialisation).arePrimariesFulfilled(moduleCodes))
                    )
                    .append("] Primaries");
            for (ModuleCode primaries : ((GenericCsSpecialisation) getSpecialisation()).getPrimaries()) {
                sb.append("\n        ").append(new SingleGraduationRequirement(primaries).getString(moduleCodes));
            }
            sb.append("\n    [")
                    .append(getStatusIcon(((GenericCsSpecialisation) specialisation)
                            .areElectivesFulfilled(moduleCodes)))
                    .append("] Electives");
            for (ModuleCode electives : ((GenericCsSpecialisation) getSpecialisation()).getElectives()) {
                sb.append("\n        ").append(new SingleGraduationRequirement(electives).getString(moduleCodes));
            }
            return sb.toString();
        } catch (Exception e) {
            return "[X] Focus Area: Unknown (Please set your specialisation first!)";
        }
    }

    /**
     * Method to override the default {@code toString} function of {@code FocusAreaGraduationRequirement}
     *
     * @return String representation of {@code FocusAreaGraduationRequirement}
     */
    @Override
    public String toString() {
        return "[?] " + "Focus Area: " + specialisation.getName();
    }
}