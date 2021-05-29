import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.apache.jena.vocabulary.VCARD;

public class Application {
    public static void main(String[] args) {
        // some definitions
        final String personURI    = "http://somewhere/JohnSmith";
        final String fullName     = "John Smith";

        // create an empty Model
        Model model = ModelFactory.createDefaultModel();

        // create the resource
        Resource johnSmith = model.createResource(personURI);


        final String url = "http://upc/fib/open_data/p3/";

        new ReadCsv().getAll().forEach(e -> {
            final Resource auth = model.createResource(url + "/author/" + e.authorName());
            auth.addProperty(model.createProperty(url + "/write"), e.paperCode());
            johnSmith.addProperty(VCARD.FN, fullName);
        });

        System.out.println(model);


    }
}
