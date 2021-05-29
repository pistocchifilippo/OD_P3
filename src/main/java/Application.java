import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;

import java.util.Random;

public class Application {

    private static final String CONFERENCES = "src/main/resources/dataset_conferences.csv";
    private static final String JOURNALS = "src/main/resources/dataset_journal.csv";

    private static final Random rand = new Random(1);
    private static final String url = "http://upc/fib/open_data/p3/pistocchi_romani/conferences_db/";
    private static final String AUTHOR_LOCATOR = "author/";
    private static final String SUBMISSION_LOCATOR = "submission/";

    private static final Model model = ModelFactory.createDefaultModel();

    public static void main(String[] args) {


        new ReadCsv().getAll(CONFERENCES).limit(2).forEach(e -> {
            Resource auth = model.createResource(url + AUTHOR_LOCATOR + e.authorName());
            Resource submission = model.createResource(url + SUBMISSION_LOCATOR + e.submission());


            auth.addProperty(model.createProperty(url + "submit"),submission);
        });

        System.out.println(model);


    }
}
