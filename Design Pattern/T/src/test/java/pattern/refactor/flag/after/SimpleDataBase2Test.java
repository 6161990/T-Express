package pattern.refactor.flag.after;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

class SimpleDataBase2Test {

    @Test
    void after() throws IOException {
        SimpleDataBase2 simpleDataBase = new SimpleDataBase2(new FileReader("/Users/6161990src/Documents/GitHub/T-Express/Design Pattern/T/src/test/java/pattern/refactor/flag/test.txt"));

        Iterator<String> ir = simpleDataBase.iterator();

        while (ir.hasNext()){
            String key = ir.next();
            System.out.println("key:" + key);
            System.out.println("value:" + simpleDataBase.getValue(key));
        }
    }
}