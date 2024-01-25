package hexlet.code.abstracts.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IFormatter {
    String makeOutputString(List<Map<String, Object>> data) throws IOException;
}
