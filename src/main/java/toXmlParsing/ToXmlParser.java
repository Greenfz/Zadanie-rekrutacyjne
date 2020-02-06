package toXmlParsing;

import model.AccountListWrapper;

import java.io.IOException;

public interface ToXmlParser {

    void writeToXmlFile(AccountListWrapper collection, String filePath) throws IOException;
}
