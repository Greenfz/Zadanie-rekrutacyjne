package fromXmlParser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;
import lombok.Setter;
import model.Account;

import java.util.List;

@Setter
@AllArgsConstructor
public class AccountsListMapper {

    private XmlMapper xmlMapper;

    public List<Account> mapToList(String fileContent) throws JsonProcessingException {
            return xmlMapper.readValue(fileContent, new TypeReference<List<Account>>() {});
    }

    public AccountsListMapper() {
        setXmlMapper(new XmlMapper());
    }
}
