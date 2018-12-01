package god.util.waitUse;

import java.util.ArrayList;
import java.util.List;

public class TestTwoGroup {
    public static void main(String[] args) {
        List<Object[]> records = new ArrayList<Object[]>();
        String fields[] = new String[3];
        for (int i = 0; i < 3; i++) {
            fields[i] = String.valueOf(i);
        }
        records.add(fields);

        Object[][] results = new Object[1][];
        for (int i = 0; i < 1; i++) {
            results[i] = records.get(i);
        }

        for (int x = 0; x < results.length; x++) {
            for (int y = 0; y < results[x].length; y++) {
                System.out.print(results[x][y] + "、");
            }
        }
    }
}
