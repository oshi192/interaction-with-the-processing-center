package application.model;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@XmlRootElement(name = "attribute")
@XmlAccessorType(XmlAccessType.FIELD)
public class Attribute {

    @XmlAttribute (required = true)
    private String name;

    @XmlAttribute (required = true)
    private String value;

}
