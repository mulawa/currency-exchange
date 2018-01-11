package pl.marlena.currencyexchange.rates.provider.nbp;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tabela_kursow")
@Getter
public class NbpXmlRates {
    @XmlElement(name = "numer_tabeli")
    private String number;
    @XmlElement(name = "data_notowania")
    private String effectiveDate;
    @XmlElement(name = "data_publikacji")
    private String publishedDate;
    @XmlElement(name = "pozycja")
    private List<NbpXmlRate> rates;
}
