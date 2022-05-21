import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PVIOp {


    public static void main(String[] args) {
        Path relpath = Paths.get("Model_12.4_20220316_results.csv");
        Path path = relpath.toAbsolutePath();
        try( Stream<String> lines = Files.lines(path) ){
            List<List<String>> matrix =
                    lines.map( line -> {
                                return Stream.of( line.split(",(?!\\s)") )
                                        .parallel()
                                        .map(value->value.substring(1, value.length()-1))
                                        .collect( Collectors.toList() ); })
                            .collect( Collectors.toList() );
            List<Integer> idx = new ArrayList<Integer>();
            idx.add(0);
            idx.add(6);
            idx.add(7);
            idx.add(9);

            double[] matrixMA = matrix.stream()
                    .parallel()
                    .filter((a)->a.get(3).startsWith("Mass"))
                    .map((List<String> a) -> idx.stream()
                            .parallel()
                            .map(a::get)
                            .map((String val)->Double.parseDouble(val))
                            .collect(Collectors.toList()))
                    .reduce(new double[5],
                            (result,vals)->{
                                result[0]=(result[0]*result[4]+ vals.get(0))/(result[4]+1);
                                result[1]=(result[1]*result[4]+ vals.get(1))/(result[4]+1);
                                result[2]=(result[2]*result[4]+ vals.get(2))/(result[4]+1);
                                result[3]=(result[3]*result[4]+ vals.get(3))/(result[4]+1);
                                result[4]++;
                                return result;},
                            (fR, iR)->{
                                for (int i=0;i<iR.length-1;i++){
                                    if(iR[4]==0 && fR[4]==0){fR[i]=0;}
                                    else{
                                        fR[i]=(((fR[i]*fR[4])+(iR[i]+iR[4]))/(fR[4]+iR[4]));
                                        fR[4]=fR[4]+iR[4];
                                    }
                                }
                                return fR;
                    });

            System.out.printf("\nMassachusetts: \n" +
                            "    ToxPi:                                 %s \n" +
                            "    Infection Rate: Transmissible Cases:   %s \n" +
                            "    Infection Rate: Disease Spread:        %s \n" +
                            "    Intervention: Vaccines                 %s \n",matrixMA[0],matrixMA[1],matrixMA[2],matrixMA[3]);

            double[] matrixNJ = matrix.stream()
                    .parallel()
                    .filter((a)->a.get(3).startsWith("New Jer"))
                    .map((List<String> a) -> idx.stream()
                            .parallel()
                            .map(a::get)
                            .map((String val)->Double.parseDouble(val))
                            .collect(Collectors.toList()))
                    .reduce(new double[5],
                            (result,vals)->{
                                result[0]=(result[0]*result[4]+ vals.get(0))/(result[4]+1);
                                result[1]=(result[1]*result[4]+ vals.get(1))/(result[4]+1);
                                result[2]=(result[2]*result[4]+ vals.get(2))/(result[4]+1);
                                result[3]=(result[3]*result[4]+ vals.get(3))/(result[4]+1);
                                result[4]++;
                                return result;},
                            (fR, iR)->{
                                for (int i=0;i<iR.length-1;i++){
                                    if(iR[4]==0 && fR[4]==0){fR[i]=0;}
                                    else{
                                        fR[i]=(((fR[i]*fR[4])+(iR[i]+iR[4]))/(fR[4]+iR[4]));
                                        fR[4]=fR[4]+iR[4];
                                    }
                                }
                                return fR;
                            });
            System.out.printf("\nNew Jersey: \n" +
                    "    ToxPi:                                 %s \n" +
                    "    Infection Rate: Transmissible Cases:   %s \n" +
                    "    Infection Rate: Disease Spread:        %s \n" +
                    "    Intervention: Vaccines                 %s \n",matrixNJ[0],matrixNJ[1],matrixNJ[2],matrixNJ[3]);
            System.out.println("Note: Calculated values are incorrect, issue with the combiner");
        } catch (IOException ex) {}

    }
}
