import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;

import java.io.FileWriter;
import java.io.IOException;

public class Application {

    private static final String CONFERENCES_FILE = "src/main/resources/dataset_conferences.csv";
    private static final String JOURNALS_FILE = "src/main/resources/dataset_journal.csv";
    private static final String ABOX_OUTPUT_FILE = "src/main/resources/ABOX.rdf";

    private static final String url = "http://upc/fib/open_data/p3/pistocchi_romani/conferences_db/";
    private static final String propUrl = "http://upc/fib/open_data/p3/pistocchi_romani/conferences_db#";

    private static final String AUTHOR_LOCATOR = "author#";
    private static final String PAPER_LOCATOR = "paper#";
    private static final String SUBMISSION_LOCATOR = "submission#";
    private static final String CONFERENCE_LOCATOR = "conference#";
    private static final String KA_LOCATOR = "knowledge_area#";
    private static final String JOURNAL_LOCATOR = "journal#";

    private static final int LIMIT = 100;

    private static final Model model = ModelFactory.createDefaultModel();

    public static void main(String[] args) throws IOException {

        // Conferences
        ReadCsv.getAll(CONFERENCES_FILE).limit(LIMIT).forEach(e -> {
            Resource auth = model.createResource(url + AUTHOR_LOCATOR + e.authorName());
            Resource submission = model.createResource(url + SUBMISSION_LOCATOR + e.submission());
            Resource paper = model.createResource(url + PAPER_LOCATOR + e.paperCode());
            Resource conference = model.createResource(url + CONFERENCE_LOCATOR + e.conferenceOrJournalName());
            Resource knowledgeArea = model.createResource(url + KA_LOCATOR + e.knowledgeArea());

            auth.addProperty(model.createProperty(propUrl + "writes"),paper);
            auth.addProperty(model.createProperty(propUrl + "submit"),submission);
            paper.addProperty(model.createProperty(propUrl + "submitted"),submission);
            paper.addProperty(model.createProperty(propUrl + "publishedInConference"),conference);
            paper.addLiteral(model.createProperty(propUrl + "title"),e.paperTitle());
            conference.addProperty(model.createProperty(propUrl + "paperKnowledgeArea"),knowledgeArea);
            conference.addProperty(model.createProperty(propUrl + "conferenceKnowledgeArea"),knowledgeArea);
            submission.addProperty(model.createProperty(propUrl + "forConference"),conference);
        });

        // Journals
        ReadCsv.getAll(JOURNALS_FILE).limit(LIMIT).forEach(e -> {
            Resource auth = model.createResource(url + AUTHOR_LOCATOR + e.authorName());
            Resource submission = model.createResource(url + SUBMISSION_LOCATOR + e.submission());
            Resource paper = model.createResource(url + PAPER_LOCATOR + e.paperCode());
            Resource journal = model.createResource(url + JOURNAL_LOCATOR + e.conferenceOrJournalName());
            Resource knowledgeArea = model.createResource(url + KA_LOCATOR + e.knowledgeArea());

            auth.addProperty(model.createProperty(propUrl + "writes"),paper);
            auth.addProperty(model.createProperty(propUrl + "submit"),submission);
            paper.addProperty(model.createProperty(propUrl + "submitted"),submission);
            paper.addProperty(model.createProperty(propUrl + "publishedInJournal"),journal);
            journal.addProperty(model.createProperty(propUrl + "paperKnowledgeArea"),knowledgeArea);
            journal.addProperty(model.createProperty(propUrl + "journalKnowledgeArea"),knowledgeArea);
            submission.addProperty(model.createProperty(propUrl + "forJournal"),journal);
        });

        System.out.println(model);
        model.write(new FileWriter(ABOX_OUTPUT_FILE),"RDF/XML");

    }
}
