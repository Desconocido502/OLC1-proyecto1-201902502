package Main;

/**
 *
 * @author CarlosSoto
 */
public class NodeType {
    public static enum Types{
        ROOT,
        LEAF,
        CONCATENATION,
        DISJUNCTION,
        KLEENE_LOCK,
        POSITIVE_LOCK,
        BOOLEAN_LOCK,
        NULL
    };
}
