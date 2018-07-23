import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import org.junit.Test;
public class ModelObjectsTest {
    public static <T> T convertToObject(String xmlString) {
        T object = null;
        JAXBContext jaxbContext;
        try {
            StringReader sr = new StringReader(xmlString);
            jaxbContext = JAXBContext.newInstance(object.getClass());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            object = (T) unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return object;
    }
@Test
    void checkRequest(){
        assert(true);
    }
}
