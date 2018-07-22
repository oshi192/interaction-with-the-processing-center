package application.model.request;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({Verify.class, Payment.class, Status.class})
public abstract class AbstractRequestBody {
}
