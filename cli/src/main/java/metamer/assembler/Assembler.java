package metamer.assembler;

import metamer.fasta.Fasta;
import metamer.fasta.Record;
import metamer.graph.Graph;
import metamer.graph.GraphCycle;
import metamer.io.FileReader;
import metamer.io.FileWriter;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.stream.Stream;

public class Assembler {
    private final Path inputFile;
    private final Path outputFile;

    public Assembler(final Path inputFile, final Path outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public void assemble() {

        FileReader<Record> t = new FileReader<>(inputFile, Fasta.parser());
        final Stream<Record> records = t.read();

        Graph graph = new Graph(new HashMap<>(), new HashMap<>(), 3);
        graph.createFromStream(records.map(record -> record.sequence));

        final GraphCycle graphCycle = new GraphCycle(graph.optimizeGraph());
        String cycle = graphCycle.findCycle();

        final FileWriter<Record> r = new FileWriter<>(outputFile, Fasta.parser());
        r.write(Stream.of(new Record("cycle", "from file: " + inputFile, cycle)));
        
    }
}
