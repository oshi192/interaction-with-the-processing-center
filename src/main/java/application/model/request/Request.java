package application.model.request;


import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class Request {

    @XmlAttribute(required = true)
    private int point;

    @XmlElementRef
    private List<? extends AbstractRequestBody> requestBody;

}
