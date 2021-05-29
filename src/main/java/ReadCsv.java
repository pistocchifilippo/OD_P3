import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ReadCsv {

    private final int AUTHOR_NAME = 3;
    private final int CONFERENCE_NAME = 4;
    private final int KEYWORD = 7;
    private final int PARER_TITLE = 10;
    private final int PAPER_CODE = 1;
    private final int REVIEWERS_1 = 17;
    private final int REVIEWERS_2 = 18;
    private final int REVIEWERS_3 = 19;

    Stream<Line> getAll() {
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/Connections_Conf.csv"))) {
            List<String[]> r = reader.readAll();
            return r.stream()
                    .map(Arrays::toString)
                    .map(e -> {
                        String[] s = e.split(",");
                        return new LineImplementation(
                                s[AUTHOR_NAME],
                                s[CONFERENCE_NAME],
                                s[KEYWORD],
                                s[PARER_TITLE],
                                s[PAPER_CODE],
                                s[REVIEWERS_1],
                                s[REVIEWERS_2],
                                s[REVIEWERS_3]
                        );
                    });
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static void main(String[] args) throws IOException, CsvException {
//
//        FileWriter out = new FileWriter("src/main/resources/dataset.csv");
//
//        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/Connections_Conf.csv"))) {
//            Set<String> paperCode = new HashSet<>();
//            List<String[]> r = reader.readAll();
////            r.forEach(x -> System.out.println(Arrays.toString(x)));
//            r.stream()
//                    .map(x -> Arrays.toString(x))
//                    .filter(e -> {
//                        String code = e.split(",")[1];
//                        boolean pass = !paperCode.contains(code);
//                        paperCode.add(code);
//                        return pass;
//                    })
//                    .map(s -> s.replace("[","").replace("]",""))
////                    .forEach(e -> System.out.println(e));
//                    .forEach(e -> {
//                        try {
//                            out.write(e);
//                            out.write("\n");
//                        } catch (IOException ioException) {
//                            ioException.printStackTrace();
//                        }
//                    });
//        }
//    }
}
