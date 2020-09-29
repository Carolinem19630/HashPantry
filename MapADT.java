// --== CS400 File Header Information ==--
// Name: Caroline Machart
// Email: machart@wisc.edu
// Team: HC Team
// TA: Na Li
// Lecturer: Florian Heimerl
import java.util.NoSuchElementException;

public interface MapADT<KeyType, ValueType> {

	public boolean put(KeyType key, ValueType value);
	public ValueType get(KeyType key) throws NoSuchElementException;
	public int size();
	public boolean containsKey(KeyType key);
	public ValueType remove(KeyType key);
	public void clear();
	
}
