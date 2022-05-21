import java.time.LocalDateTime;

public class DirectoryTest implements Runnable {
    static Directory folderA = new Directory(null,"folderA",12, LocalDateTime.now());
    static Directory folderB = new Directory(folderA,"folderB",10,LocalDateTime.now());
    static File fileA = new File(folderA,"fileA",100,LocalDateTime.now());
    static File fileB = new File(folderB,"fileB",125,LocalDateTime.now());

    public void run() {
            folderB.appendChild(new File(folderB,"Test",111,LocalDateTime.now()));
            System.out.println(folderA.getChildren());
            System.out.println(folderA.getFiles());
            System.out.println(folderB.getChildren());
            System.out.println(folderB.countChildren());
    }

    public static void main(String[] args) {
        folderA.appendChild(folderB);
        folderA.appendChild(fileA);
        folderB.appendChild(fileB);

        for(int i=0;i<100;i++){
            DirectoryTest dTest = new DirectoryTest();
            Thread t = new Thread(dTest);
            t.start();
        }


    }
}
