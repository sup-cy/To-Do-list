package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

public interface Saveable {
    public void save(Map<Location,ArrayList<Item>> item) throws IOException;
}
