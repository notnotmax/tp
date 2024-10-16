package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.IsStudentOfCoursePredicate;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (args.trim().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_COURSE);


        ArrayList<Predicate<Student>> predicateList = new ArrayList<>();
        addPredicatesIntoList(predicateList, argMultimap, PREFIX_NAME);
        addPredicatesIntoList(predicateList, argMultimap, PREFIX_COURSE);

        return new FindCommand(predicateList);
    }

    /**
     * Creates predicates corresponding to the specified prefix type and adds into the predicate list.
     *
     * @param predicateList The predicate list.
     * @param argMultimap The ArgumentMultimap mapping prefixes to arguments.
     * @param prefix The prefix.
     */
    private void addPredicatesIntoList(List<Predicate<Student>> predicateList,
                                       ArgumentMultimap argMultimap, Prefix prefix) {
        if (argMultimap.getValue(prefix).isPresent()) {
            List<String> args = argMultimap.getAllValues(prefix);
            // switch case unavailable for Prefix
            if (prefix.equals(PREFIX_NAME)) {
                args.forEach(x -> predicateList.add(
                        new NameContainsKeywordsPredicate(List.of(x.split(";")))));
            } else if (prefix.equals(PREFIX_COURSE)) {
                args.forEach(x -> predicateList.add(
                        new IsStudentOfCoursePredicate(List.of(x.split(";")))));
            }
        }
    }
}
