package fromXmlParser;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Setter
@AllArgsConstructor
public class FromXmlParserFacade {

    private XmlFileReader xmlFileReader;
    private AccountsListMapper accountsListMapper;

    public List<Account> getAccountsListFromXmlFile(String filePath){

        try {
            String xmlFileContent = xmlFileReader.getContent(filePath);
            return accountsListMapper.mapToList(xmlFileContent);

        } catch (IOException ex){
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static FromXmlParserFacade createDefaultFacade() {
        return new FromXmlParserFacade(new XmlFileReader(), new AccountsListMapper());
    }
}
