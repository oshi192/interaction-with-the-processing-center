package application.model.request;

import application.model.Attribute;
import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@XmlRootElement(name = "payment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Payment extends AbstractRequestBody {

    @XmlAttribute(required = true)
    private long id;

    @XmlAttribute(required = true)
    private int sum;

    @XmlAttribute(required = true)
    private int check;

    @XmlAttribute(required = true)
    private int service;

    @XmlAttribute(required = true)
    private String account;

    @XmlAttribute(required = true)
    private String date;

    @XmlElement
    private List<Attribute> attributes;
}
