package application.model.request;

import application.model.Attribute;
import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "verify")
@XmlAccessorType(XmlAccessType.FIELD)
public class Verify extends AbstractRequestBody {

    @XmlAttribute(required = true)
    private int service;

    @XmlAttribute(required = true)
    private String account;

    @XmlElement
    private List<Attribute> attributes;


}
