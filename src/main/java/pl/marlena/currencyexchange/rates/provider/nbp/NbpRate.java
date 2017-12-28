package pl.marlena.currencyexchange.rates.provider.nbp;


import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pozycja")
@Getter
public class NbpRate {
    @XmlElement(name = "nazwa_waluty")
    private String currency;
    @XmlElement(name = "kod_waluty")
    private String currencyCode;
    @XmlElement(name = "kurs_kupna")
    private String buy;
    @XmlElement(name = "kurs_sprzedazy")
    private String sell;
}
