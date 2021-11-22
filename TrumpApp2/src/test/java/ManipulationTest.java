import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManipulationTest {

    @Test
    public void getNameTest(){
        MineItemData temp = new MineItemData("TestName1", (float) 957312.125, "A-123-456-789");

        assertEquals("TestName1", temp.getName());
    }

    @Test
    public void setNameTest(){
        MineItemData temp = new MineItemData("TestName1", (float) 957312.125, "A-123-456-789");

        temp.setName("NewTestName2");

        assertEquals("NewTestName2", temp.getName());
    }

    @Test
    public void getValueTest(){
        MineItemData temp = new MineItemData("TestName1", (float) 957312.125, "A-123-456-789");

        assertEquals(957312.125, temp.getValue());
    }

    @Test
    public void setValueTest(){
        MineItemData temp = new MineItemData("TestName1", (float) 957312.125, "A-123-456-789");

        temp.setValue((float)100);

        assertEquals((float)100, temp.getValue());
    }

    @Test
    public void getSerialTest(){
        MineItemData temp = new MineItemData("TestName1", (float) 957312.125, "A-123-456-789");

        assertEquals("A-123-456-789", temp.getSerial());
    }

    @Test
    public void setSerialTest(){
        MineItemData temp = new MineItemData("TestName1", (float) 957312.125, "A-123-456-789");

        temp.setSerial("Z-987-654-321");

        assertEquals("Z-987-654-321", temp.getSerial());
    }



}
