package application.model.request;

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

@XmlRootElement(name = "status")
@XmlAccessorType(XmlAccessType.FIELD)
public class Status {
    @XmlAttribute(required = true)
    private long id;

}
