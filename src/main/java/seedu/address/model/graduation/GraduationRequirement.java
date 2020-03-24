package seedu.address.model.graduation;

import seedu.address.model.module.ModuleCode;

import java.util.List;

public abstract class GraduationRequirement {

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(ModuleCode moduleCode) {
        this.moduleCode = moduleCode;
    }

    protected ModuleCode moduleCode;

    public abstract boolean isFulfilled(List<ModuleCode> moduleCodes);

    protected String getStatusIcon(boolean done) {
        return done ? "Y" : "X";
    }

    @Override
    public String toString() {
        return moduleCode.toString();
    }


    public String getString(List<ModuleCode> moduleCodes) {
        return "[" + getStatusIcon(isFulfilled(moduleCodes)) + "] " + moduleCode.toString();
    }
}
