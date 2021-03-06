package seedu.planner.logic.parser.module;

import static seedu.planner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.planner.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.planner.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.planner.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.planner.logic.commands.module.ModuleRemoveCommand;
import seedu.planner.model.module.ModuleCode;

//@@author thetruevincentchow
class ModuleRemoveCommandParserTest {
    private ModuleRemoveCommandParser parser = new ModuleRemoveCommandParser();

    @Test
    public void parse_validArgs_returnsRemoveCommand() {
        assertParseSuccess(parser, "CS2040", new ModuleRemoveCommand(new ModuleCode("CS2040")));

        // Whitespace (including tabs and newlines) is tolerated between module codes
        assertParseSuccess(parser, "\n\t  CS2040  \t \n ", new ModuleRemoveCommand(new ModuleCode("CS2040")));

        // The module code doesn't have to correspond to a valid module
        // (also note that ModuleCode converts the code to uppercase)
        assertParseSuccess(parser, "ab1234", new ModuleRemoveCommand(new ModuleCode("ab1234")));

        // Multiple module codes are allowed
        assertParseSuccess(parser, "\n\tCS2040  \n\t CS2030\n\t",
                new ModuleRemoveCommand(Arrays.asList(new ModuleCode("CS2040"), new ModuleCode("CS2030"))));

        // Repeated module codes are allowed
        assertParseSuccess(parser, "A A\tB",
                new ModuleRemoveCommand(Arrays.asList(new ModuleCode("A"), new ModuleCode("A"), new ModuleCode("B"))));

        // The order of module codes should be reflected in the {@link ModuleRemoveCommand}
        assertParseSuccess(parser, "\n\tCS2030  \n\t CS2040\n\t",
                new ModuleRemoveCommand(Arrays.asList(new ModuleCode("CS2030"), new ModuleCode("CS2040"))));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // empty
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ModuleRemoveCommand.MESSAGE_USAGE));

        // whitespace only
        assertParseFailure(parser, PREAMBLE_WHITESPACE, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ModuleRemoveCommand.MESSAGE_USAGE));

        // invalid module code format
        assertParseFailure(parser, "!CS2103T", ModuleCode.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, "-4asdf++!", ModuleCode.MESSAGE_CONSTRAINTS);

        // all module codes must be valid (with space sepapration)
        assertParseFailure(parser, "CS2103T AA! AB1234\n\t\t", ModuleCode.MESSAGE_CONSTRAINTS);
    }
}
//@@author
