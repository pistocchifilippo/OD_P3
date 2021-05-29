import java.util.Set;

public interface Line {

    // Default properties
    String authorName();
    String conferenceOrJournalName();
    String knowledgeArea();
    String paperTitle();
    String paperCode();
    Set<String> reviewers();

    // Generated
    String submission();
    String reviewCommission();
    String reviewDetail();

}
