import java.util.HashSet;
import java.util.Set;

public class LineImplementation implements Line {

    String authorName;
    String conferenceName;
    String keyword;
    String paperTitle;
    String paperCode;
    Set<String> reviewers;

    public LineImplementation(String authorName,
                              String conferenceName,
                              String keyword,
                              String paperTitle,
                              String paperCode,
                              String reviewer1,
                              String reviewer2,
                              String reviewer3) {
        this.authorName = authorName;
        this.conferenceName = conferenceName;
        this.keyword = keyword;
        this.paperTitle = paperTitle;
        this.paperCode = paperCode;

        this.reviewers = new HashSet<>();
        this.reviewers.add(reviewer1);
        this.reviewers.add(reviewer2);
        this.reviewers.add(reviewer3);
    }

    @Override
    public String authorName() {
        return authorName;
    }

    @Override
    public String conferenceName() {
        return conferenceName;
    }

    @Override
    public String keyword() {
        return keyword;
    }

    @Override
    public String paperTitle() {
        return paperTitle;
    }

    @Override
    public String paperCode() {
        return paperCode;
    }

    @Override
    public Set<String> reviewers() {
        return reviewers;
    }

    @Override
    public String submission() {
        return "SUB:" + paperCode + "_" + conferenceName + "_" + authorName.replaceAll(" ","");
    }

    @Override
    public String reviewCommission() {
        return "COM:" + reviewers + "_" + submission();
    }

    @Override
    public String reviewDetail() {
        return "DET:" + "_" + reviewCommission();
    }

    @Override
    public String toString() {
        return "LineImplementation{" +
                "authorName='" + authorName + '\'' +
                ", conferenceName='" + conferenceName + '\'' +
                ", keyword='" + keyword + '\'' +
                ", paperTitle='" + paperTitle + '\'' +
                ", paperCode='" + paperCode + '\'' +
                ", reviewers=" + reviewers +
                ", submission='" + submission() + '\'' +
                ", reviewCommission='" + reviewCommission() + '\'' +
                ", reviewDetail='" + reviewDetail() + '\'' +
                '}';
    }
}
