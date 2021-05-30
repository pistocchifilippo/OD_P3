import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class ReadCsv {

    private static final int AUTHOR_NAME = 3;
    private static final int CONFERENCE_NAME = 4;
    private static final int KEYWORD = 7;
    private static final int PARER_TITLE = 10;
    private static final int PAPER_CODE = 1;
    private static final int REVIEWERS_1 = 17;
    private static final int REVIEWERS_2 = 18;
    private static final int REVIEWERS_3 = 19;

    /***
     *
     * @param path The path of the file you want to read
     * @return A steam of Line
     */
    public static Stream<Line> getAll(final String path) throws IOException{
        List<Line> lines = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(",");
                Line l = new LineImplementation(
                        s[AUTHOR_NAME],
                        s[CONFERENCE_NAME],
                        s[KEYWORD],
                        s[PARER_TITLE],
                        s[PAPER_CODE],
                        s[REVIEWERS_1],
                        s[REVIEWERS_2],
                        s[REVIEWERS_3]
                );
                lines.add(l);
            }
        }
        return lines.stream();
    }


    // DATA PREPARATION
//    public static void main(String[] args) throws IOException, CsvException {
//
//        FileWriter out = new FileWriter("src/main/resources/dataset_journal.csv");
//
//        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/Connections_Journal.csv"))) {
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
//                    .map(s -> s.replace("[","").replace("]","").replace(", ",",").replace(" ","_"))
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
