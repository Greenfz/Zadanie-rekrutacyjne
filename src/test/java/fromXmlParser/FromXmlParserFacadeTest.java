package fromXmlParser;

import accountValidation.AccountValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import model.Account;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class FromXmlParserFacadeTest {

    XmlFileReader xmlFileReader = mock(XmlFileReader.class);
    AccountsListMapper accountsListMapper = mock(AccountsListMapper.class);
    @InjectMocks
    FromXmlParserFacade fromXmlParserFacade = FromXmlParserFacade.createDefaultFacade();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void thrownIOException_shouldReturnEmptyList() throws IOException {
        //  WHEN
        when(xmlFileReader.getContent(anyString())).thenThrow(IOException.class);
        List<Account> resultList = fromXmlParserFacade.getAccountsListFromXmlFile("");

        //  THEN
        assertTrue(resultList.isEmpty());
    }

    @Test
    public void thrownJsonProcessingException_shouldReturnEmptyList() throws JsonProcessingException {
        //  WHEN
        when(accountsListMapper.mapToList(anyString())).thenThrow(JsonProcessingException.class);
        List<Account> resultList = fromXmlParserFacade.getAccountsListFromXmlFile("");

        //  THEN
        assertTrue(resultList.isEmpty());
    }
}