package application.model.response;

import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class Response {

    @XmlAttribute
    private String server;

    @XmlElementRef
    private List<Result> responseBody;

}
