import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement{
    public Directory(Directory parent, String name, int size, LocalDateTime creationTime){
        super(parent,name,size,creationTime);
    }


    private LinkedList<FSElement> children = new LinkedList<>();
    private LinkedList<Directory> subDirectories = new LinkedList<>();
    private LinkedList<File> files = new LinkedList<>();

    public LinkedList<FSElement> getChildren(){
        lock.lock();
        try{
            return children;
        }finally {
            lock.unlock();
        }

    }
    public void appendChild(FSElement child){
        lock.lock();
        try{
            children.add(child);
            child.setParent(this);
            if(child instanceof Directory){
                subDirectories.add((Directory) child);
            }
            if(child instanceof File){
                files.add((File)child);
            }
        }finally {
            lock.unlock();
        }

    }
    public int countChildren(){
        lock.lock();
        try{
            return children.size();
        }finally {
            lock.unlock();
        }

    }
    public LinkedList<Directory> getSubDirectories(){
        lock.lock();
        try{
            return subDirectories;
        }finally {
            lock.unlock();
        }

    }
    public LinkedList<File> getFiles(){
        lock.lock();
        try{
            return files;
        }finally{
            lock.unlock();
        }

    }
    public int getTotalSize(){
        int size = 0;
        LinkedList<FSElement> items = getChildren();
        for (FSElement obj : items) {
            size = size + obj.getTotalSize();
        }
        return size;
    }

    public boolean isDirectory(){return true;}
}
