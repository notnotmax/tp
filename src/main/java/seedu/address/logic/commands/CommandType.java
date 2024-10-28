package seedu.address.logic.commands;

/**
 * Enum representing the types of commmand
 */
public enum CommandType {
    // Command Type for General Use
    CLEAR,
    HELP,
    EXIT,

    // Command Type for Students
    LISTSTUDENT,
    ADDSTUDENT,
    EDITSTUDENT,
    FINDSTUDENT,
    DELETESTUDENT,
    EXPORTSTUDENT,
    IMPORTSTUDENT,

    // Command Type for Consultations TODO
    ADDCONSULT,
    DELETECONSULT,
    REMOVEFROMCONSULT,
    ADDTOCONSULT,
    LISTCONSULT,
    IMPORTCONSULT,

    // Command Type for Lesson
    ADDLESSON,
    DELETELESSON,
    LISTLESSONS,
    ADDTOLESSON,
    REMOVEFROMLESSON
}
