import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Directory extends FSElement{
    public Directory(Directory parent, String name, int size, LocalDateTime creationTime){
        super(parent,name,size,creationTime);
    }


    private ConcurrentLinkedQueue<FSElement> children = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Directory> subDirectories = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<File> files = new ConcurrentLinkedQueue<>();

    public ConcurrentLinkedQueue<FSElement> getChildren(){
            return children;
    }
    public void appendChild(FSElement child){
        children.add(child);
        child.setParent(this);
        if(child instanceof Directory){
            subDirectories.add((Directory) child);
        }
        if(child instanceof File){
            files.add((File)child);
        }
    }
    public int countChildren(){
        return children.size();
    }
    public ConcurrentLinkedQueue<Directory> getSubDirectories(){
        return subDirectories;
    }
    public ConcurrentLinkedQueue<File> getFiles(){
        return files;
    }
    public int getTotalSize(){
        int size = 0;
        ConcurrentLinkedQueue<FSElement> items = getChildren();
        for (FSElement obj : items) {
            size = size + obj.getTotalSize();
        }
        return size;
    }

    public boolean isDirectory(){return true;}
}
