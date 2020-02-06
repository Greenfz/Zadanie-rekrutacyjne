package toXmlParsing;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.AccountListWrapper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomDeclarationParser implements ToXmlParser {

    private final String declaration = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> \n";
    private XmlMapper xmlMapper = new XmlMapper();


    @Override
    public void writeToXmlFile(AccountListWrapper collection, String filePath) throws IOException {

        if (collection.isNotEmpty()) {
            PrintWriter printWriter = new PrintWriter(new FileWriter(filePath));
            try {
                configureXmlMapper();
                String xmlBody = xmlMapper.writeValueAsString(collection);
                String content = prepareXmlContent(declaration, xmlBody);
                printWriter.print(content);

            }  finally {
                printWriter.close();
            }
        }
    }

    private void configureXmlMapper() {
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    private String prepareXmlContent(String declaration, String body) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(declaration);
        stringBuilder.append(body);
        return stringBuilder.toString();
    }
}
