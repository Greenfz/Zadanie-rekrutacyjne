package toXmlParsing;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import model.AccountListWrapper;

import java.io.File;
import java.io.IOException;

public class JacksonDeclarationParser implements ToXmlParser {

    private XmlMapper xmlMapper = new XmlMapper();


    @Override
    public void writeToXmlFile(AccountListWrapper collection, String filePath) throws IOException {
        if (collection.isNotEmpty()){
            configureXmlMapper();
            xmlMapper.writeValue(new File(filePath), collection);
        }
    }

    private void configureXmlMapper() {
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
    }


}
