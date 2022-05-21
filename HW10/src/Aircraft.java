import java.util.concurrent.atomic.AtomicReference;

public class Aircraft {
    private AtomicReference<Position> position; // Shared (non-final) variable
    public Aircraft(Position pos){
        position=new AtomicReference<Position>(pos);
    }
    public void setPosition(Position pos){
        position.set(pos);
        }
    public Position getPosition(){
        return position.get();
        }
}
