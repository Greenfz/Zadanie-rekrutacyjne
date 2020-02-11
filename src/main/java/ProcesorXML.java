


import accountsProcessing.AccountsProcessor;
import fromXmlParser.FromXmlParserFacade;
import model.Account;
import model.AccountListWrapper;
import toXmlParsing.JacksonDeclarationParser;
import toXmlParsing.ToXmlParser;

import java.io.IOException;
import java.util.List;
import java.util.Set;


public class ProcesorXML {


    public static void main(String[] args) {
        String inputFilePath = "src/main/resources/input.xml";
        String outputFilePath = "src/main/resources/output.xml";
        FromXmlParserFacade fromXmlParserFacade;
        AccountsProcessor accountsProcessor;
        ToXmlParser xmlParser;

        fromXmlParserFacade = FromXmlParserFacade.createDefaultFacade();
        accountsProcessor = new AccountsProcessor();
        //  Tutaj wybieramy parser w zaleznosci od wymaganej deklaracji w XML
        xmlParser = new JacksonDeclarationParser();
//        xmlParser = new CustomDeclarationParser();

        try {
            inputFilePath = "input.xml";
            outputFilePath = "output.xml";
            List<Account> accountsFromFile = fromXmlParserFacade.getAccountsListFromXmlFile(inputFilePath);
            Set<Account> validSortedAccounts = accountsProcessor.validateAndSortAccounts(accountsFromFile);
            AccountListWrapper accountListWrapper = new AccountListWrapper(validSortedAccounts);
            xmlParser.writeToXmlFile(accountListWrapper, outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}