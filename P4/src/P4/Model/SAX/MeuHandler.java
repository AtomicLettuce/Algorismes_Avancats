package P4.Model.SAX;

import P4.Main;
import P4.Model.Graf;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MeuHandler extends DefaultHandler {


    private Main main;
    private boolean enTipo;
    private boolean enGrafo;
    private boolean enNodo;
    private boolean enArista;
    private boolean enMapa;
    private Graf graf;
    public MeuHandler(Main main, Graf graf) {
        super();
        this.main=main;
        this.graf=graf;
        enMapa = enTipo = enGrafo = enNodo = enArista = false;
    }


    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("graf")) {
            enGrafo = true;
        } else if (qName.equalsIgnoreCase("node")) {
            enNodo = true;
        } else if (qName.equalsIgnoreCase("aresta")) {
            enArista = true;
        } else if (qName.equalsIgnoreCase("tipus")) {
            enTipo = true;
        }else if(qName.equalsIgnoreCase("mapa")){
            enMapa=true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("graf")) {
            enGrafo = false;
        } else if (qName.equalsIgnoreCase("node")) {
            enNodo = false;
        } else if (qName.equalsIgnoreCase("aresta")) {
            enArista = false;
        } else if (qName.equalsIgnoreCase("tipus")) {
            enTipo = false;
        }
        else if(qName.equalsIgnoreCase("mapa")){
            enMapa=false;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        String s[];
        String value = new String(ch, start, length).trim();
        if (enNodo) {
            s = value.split(":");
            graf.insertaNode(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]));
        } else if (enArista) {
            s = value.split(":");
            graf.insertaAresta(s[0], s[1], Double.parseDouble(s[2]));
            if(!graf.isEs_dirigit()){
                graf.insertaAresta(s[1],s[0],Double.parseDouble(s[2]));
            }
        } else if (enTipo) {
            if (value.equalsIgnoreCase("dirigido")) {
                graf.setEs_dirigit(true);
            } else if (value.equalsIgnoreCase("nodirigido")) {
                graf.setEs_dirigit(false);
            }
        }
        else if(enMapa){
            graf.defineixMapa(value);
        }
    }

}
