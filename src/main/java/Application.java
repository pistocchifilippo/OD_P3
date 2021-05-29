import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;

public class Application {
    public static void main(String[] args) {
        // some definitions
        final String personURI    = "http://somewhere/JohnSmith";
        final String fullName     = "John Smith";

        new ReadCsv().getAll().forEach(e -> {
            // here I'm looping on each Line
            //e.authorName();
            System.out.println(e);
        });

// create an empty Model
        Model model = ModelFactory.createDefaultModel();

// create the resource
        Resource johnSmith = model.createResource(personURI);

// add the property
        johnSmith.addProperty(VCARD.FN, fullName);

        System.out.println(model);
    }
}
