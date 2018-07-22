package application.util;

import application.model.response.Response;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class RandomModelCreator {

    public static Response createModel(String xmlString){
        Response response=null;
        JAXBContext jaxbContext;
        try {
            StringReader sr = new StringReader(xmlString);
            jaxbContext = JAXBContext.newInstance(Response.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            response = (Response) unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return response;

    }
}
