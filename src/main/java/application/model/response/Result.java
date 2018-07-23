package application.model.response;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.FIELD)
public class Result {

    @XmlAttribute
    private long id;

    @XmlAttribute
    private short state;

    @XmlAttribute
    private short substate;

    @XmlAttribute
    private int trans;

    @XmlAttribute
    private int service;

    @XmlAttribute
    private int sum;

    @XmlAttribute
    private int commission;

    @XmlAttribute(required = true)
    private short code;

    @XmlAttribute(name = "final")
    private short finalStatus;

    @XmlAttribute(name = "sum_prov")
    private int sumProv;

    @XmlAttribute(name = "server_time")
    private Date serverTime;


}
