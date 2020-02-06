package fromXmlParser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import model.Account;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AccountsListMapperTest {

    AccountsListMapper accountsListMapper = new AccountsListMapper();


    @Test
    public void validXml_shouldReturnListOfTwo() throws JsonProcessingException {
        //  GIVEN
        String xmlContent = prepareValidXml();

        //  WHEN
        List<Account> accounts = accountsListMapper.mapToList(xmlContent);
        int listSize = accounts.size();

        //  THEN
        assertEquals(2, listSize);
    }

    @Test(expected = UnrecognizedPropertyException.class)
    public void emptyList() throws JsonProcessingException {
        //  GIVEN
        String xmlContent = prepareEmptyList();

        //  WHEN
        List<Account> accounts = accountsListMapper.mapToList(xmlContent);

        //  THEN Exception is thrown
    }

    @Test(expected = JsonProcessingException.class)
    public void emptyXml_shouldThrowException() throws JsonProcessingException {
        //  GIVEN
        String xmlContent = "";

        //  WHEN
        List<Account> accounts = accountsListMapper.mapToList(xmlContent);

        //  THEN Exception is thrown
    }

    @Test(expected = UnrecognizedPropertyException.class)
    public void invalidXmlTag_shouldThrowException() throws JsonProcessingException {
        //  GIVEN
        String xmlContent = prepareInvalidTagXml();

        //  WHEN
        List<Account> accounts = accountsListMapper.mapToList(xmlContent);

        //  THEN Exception is thrown
    }

    @Test(expected = InvalidFormatException.class)
    public void invalidXmlAttribute_shouldThrowException() throws JsonProcessingException {
        //  GIVEN
        String xmlContent = prepareInvalidAttributeXml();

        //  WHEN
        List<Account> accounts = accountsListMapper.mapToList(xmlContent);

        //  THEN Exception is thrown
    }

    private String prepareEmptyList(){
        return "<?xml version = \"1.0\"?>" +
                "<accounts></accounts>";
    }
    private String prepareValidXml() {
        return "<?xml version = \"1.0\"?>\n" +
                "<accounts>\n" +
                "    <account iban=\"PL61109010140000071219812870\">\n" +
                "        <name>name4</name>\n" +
                "        <currency>PLN</currency>\n" +
                "        <balance>0</balance>\n" +
                "        <closingDate>2029-10-11</closingDate>\n" +
                "    </account>\n" +
                "\n" +
                "    <account iban=\"PL61109010140000071219812875\">\n" +
                "        <name>name1</name>\n" +
                "        <currency>PLN</currency>\n" +
                "        <balance>123.45</balance>\n" +
                "        <closingDate>2031-06-10</closingDate>\n" +
                "    </account>" +
                "</accounts>";
    }

    private String prepareInvalidTagXml() {
        return "<?xml version = \"1.0\"?>\n" +
                "<accounts>\n" +
                "    <account iban=\"PL61109010140000071219812870\">\n" +
                //  TAG NAMED INSTEAD OF NAME
                "        <named>name4</named>\n" +
                "        <currency>PLN</currency>\n" +
                "        <balance>0</balance>\n" +
                "        <closingDate>2029-10-11</closingDate>\n" +
                "    </account>" +
                "</accounts>";
    }

    private String prepareInvalidAttributeXml() {
        return "<?xml version = \"1.0\"?>\n" +
                "<accounts>\n" +
                "    <account iban=\"PL61109010140000071219812870\">\n" +
                "        <name>name4</name>\n" +
                "        <currency>PLN</currency>\n" +
                "        <balance>xxxxxxxxxx</balance>\n" +
                "        <closingDate>2029-10-11</closingDate>\n" +
                "    </account>" +
                "</accounts>";
    }
}