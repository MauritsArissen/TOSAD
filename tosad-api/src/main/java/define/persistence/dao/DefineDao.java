package define.persistence.dao;

import java.util.ArrayList;
import java.util.HashMap;

public interface DefineDao {
	public HashMap<String, HashMap<String, HashMap<String, HashMap<String, ArrayList<String>>>>> getAvailableInput();
}
