package P4.Model.SAX;

import P4.Main;
import P4.Model.Graf;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.InputStream;

public class MeuSax {
    private String fic;
    private Main main;
    Graf graf;

    public MeuSax(String f, Main main, Graf graf) {
        fic = f;
        this.main = main;
        this.graf = graf;
    }

    public void llegir() {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            InputStream xmlInput
                    = new FileInputStream(fic);
            SAXParser saxParser = factory.newSAXParser();
            MeuHandler handler = new MeuHandler(main, graf);
            saxParser.parse(xmlInput, handler);
            System.out.println("pinga");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
